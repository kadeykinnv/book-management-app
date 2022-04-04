package kadeykin.example.bookmanagement.rest;


import io.swagger.annotations.ApiOperation;
import kadeykin.example.bookmanagement.dtos.transferObjects.AuthorDto;
import kadeykin.example.bookmanagement.services.AuthorService;
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
@RequestMapping(value = "api/v1/authors")
public class AuthorControllerV1 {
  private final AuthorService authorService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
    AuthorDto authorDto = authorService.getAuthorById(id);
    return ResponseEntity.ok(authorDto);
  }

  @GetMapping
  public ResponseEntity<List<AuthorDto>> getAllAuthors() {
    List<AuthorDto> authors = authorService.getAuthors();
    return ResponseEntity.ok(authors);
  }

  @PostMapping
  public ResponseEntity<AuthorDto> addAuthor(@RequestBody @Valid AuthorDto authorDto) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/authors").toUriString());
    return ResponseEntity.created(uri).body(authorService.addAuthor(authorDto));
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<AuthorDto> updateBook(@RequestBody @Valid AuthorDto authorDto,
                                              @PathVariable Long id) {
    AuthorDto response = authorService.updateAuthor(authorDto, id);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAuthor(@PathVariable Long id) {
    authorService.deleteAuthor(id);
  }
}
