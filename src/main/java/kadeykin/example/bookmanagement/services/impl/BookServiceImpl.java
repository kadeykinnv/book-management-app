package kadeykin.example.bookmanagement.services.impl;

import kadeykin.example.bookmanagement.dtos.mapper.AuthorMapper;
import kadeykin.example.bookmanagement.dtos.mapper.BookMapper;
import kadeykin.example.bookmanagement.dtos.transferObjects.BookDto;
import kadeykin.example.bookmanagement.entities.Author;
import kadeykin.example.bookmanagement.entities.Book;
import kadeykin.example.bookmanagement.exceptions.ResourceNotFoundException;
import kadeykin.example.bookmanagement.repositories.AuthorRepository;
import kadeykin.example.bookmanagement.repositories.BookRepository;
import kadeykin.example.bookmanagement.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("book_service")
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final AuthorMapper authorMapper;
  private final BookMapper bookMapper;

  @Override
  public BookDto getBookById(Long bookId) {
    return bookMapper.bookToDto(findBookById(bookId));
  }

  @Override
  public List<BookDto> getBooks() {
    List<Book> books = bookRepository.findAll();
    if (books.isEmpty()) {
      throw new ResourceNotFoundException("book's not found");
    }
    return books.stream().map(bookMapper::bookToDto).collect(Collectors.toList());

  }

  @Override
  public BookDto saveBook(BookDto bookDto) {
    return bookMapper.bookToDto(bookRepository.save(bookMapper.bookDtoToModel(bookDto)));
  }

  @Override
  public BookDto setAuthorForBook(Long bookId, Long authorId) {
    Book book = findBookById(bookId);
    Author author = findAuthorById(authorId);
    book.getAuthors().add(author);
    author.getBooks().add(book);
    authorRepository.save(author);
    Book response = findBookById(bookId);
    return bookMapper.bookToDto(response);
  }

  @Override
  public BookDto updateBook(BookDto bookDto, Long bookId) {
    Book book = findBookById(bookId);
    book.setName(bookDto.getName());
    book.setPrice(bookDto.getPrice());
    Book response = bookRepository.save(book);
    return bookMapper.bookToDto(response);
  }

  @Override
  public void deleteBookById(Long bookId) {
    bookRepository.deleteById(bookId);
  }

  private Book findBookById(Long id) {
    return  bookRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book with ID: " + id + " not found"));
  }
  private Author findAuthorById(Long id) {
    return  authorRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Author with ID: " + id + " not found"));
  }
}
