package kadeykin.example.bookmanagement.services;

import kadeykin.example.bookmanagement.dtos.transferObjects.BookDto;

import java.util.List;

public interface BookService {
  BookDto getBookById(Long bookId);
  List<BookDto> getBooks();
  BookDto saveBook(BookDto bookDto);
  BookDto updateBook(BookDto bookDto, Long bookId);
  void deleteBookById(Long bookId);
  BookDto setAuthorForBook(Long bookId, Long authorId);
}
