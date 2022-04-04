package kadeykin.example.bookmanagement.rest;

import io.swagger.annotations.ApiOperation;
import kadeykin.example.bookmanagement.dtos.transferObjects.BookDto;
import kadeykin.example.bookmanagement.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/books")
public class BookControllerV1 {

  private final BookService bookService;

  @ApiOperation(value = "Get all info about book", notes = "Get all info about book by id from URL")
  @GetMapping(value = "/{id}")
  public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
    BookDto bookDto = bookService.getBookById(id);
    return ResponseEntity.ok(bookDto);
  }

  @GetMapping
  public ResponseEntity<List<BookDto>> getAllBooks() {
    List<BookDto> books = bookService.getBooks();
    return ResponseEntity.ok(books);
  }
  @PostMapping
  public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/books").toUriString());
    return ResponseEntity.created(uri).body(bookService.saveBook(bookDto));
  }
  @PutMapping(value = "/{id}")
  public ResponseEntity<BookDto> updateBook(@RequestBody @Valid BookDto bookDto,
                                            @PathVariable Long id) {
    BookDto response = bookService.updateBook(bookDto, id);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBook(@PathVariable Long id) {
    bookService.deleteBookById(id);
  }
  @PutMapping(value = "/{book_id}/authors/{author_id}")
  public ResponseEntity<HttpStatus> addAuthorForBook(@PathVariable(value = "book_id") Long bookId,
                                                     @PathVariable(value = "author_id") Long authorId) {
     bookService.setAuthorForBook(bookId, authorId);
    return ResponseEntity.ok(HttpStatus.OK);
  }


}
