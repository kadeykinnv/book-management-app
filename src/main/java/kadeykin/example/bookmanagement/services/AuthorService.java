package kadeykin.example.bookmanagement.services;

import kadeykin.example.bookmanagement.dtos.transferObjects.AuthorDto;

import java.util.List;

public interface AuthorService {
  AuthorDto getAuthorById(Long id);
  List<AuthorDto> getAuthors();
  AuthorDto addAuthor(AuthorDto authorDto);
  AuthorDto updateAuthor(AuthorDto authorDto, Long id);
  void deleteAuthor(Long id);

}
