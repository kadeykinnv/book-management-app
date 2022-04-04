package kadeykin.example.bookmanagement.services.impl;

import kadeykin.example.bookmanagement.dtos.mapper.AuthorMapper;
import kadeykin.example.bookmanagement.dtos.transferObjects.AuthorDto;
import kadeykin.example.bookmanagement.entities.Author;
import kadeykin.example.bookmanagement.entities.Book;
import kadeykin.example.bookmanagement.exceptions.ResourceNotFoundException;
import kadeykin.example.bookmanagement.repositories.AuthorRepository;
import kadeykin.example.bookmanagement.repositories.BookRepository;
import kadeykin.example.bookmanagement.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("author_service")
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final AuthorMapper authorMapper;
  @Override
  public AuthorDto getAuthorById(Long id) {
    return authorRepository.findById(id)
            .map(authorMapper::authorToDto)
            .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
  }

  @Override
  public List<AuthorDto> getAuthors() {
    List<Author> authors = authorRepository.findAll();
    if (authors.isEmpty()) {
      throw new ResourceNotFoundException("author's not found");
    }
    return authors.stream().map(authorMapper::authorToDto).collect(Collectors.toList());//authorMapper.authorToDto(authors);
  }

  @Override
  public AuthorDto addAuthor(AuthorDto authorDto) {
    Author author = authorRepository.save(authorMapper.authorDtoToModel(authorDto));
    return authorMapper.authorToDto(author);
  }

  @Override
  public AuthorDto updateAuthor(AuthorDto authorDto, Long id) {
    Author author = authorRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
    author.setName(authorDto.getName());
    author.setSurname(authorDto.getSurname());
    author.setBirthday(authorDto.getBirthday());
    Author response = authorRepository.save(author);
    return authorMapper.authorToDto(response);
  }

  @Override
  public void deleteAuthor(Long id) {
    Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
    if(author.getBooks().isEmpty()) {
      authorRepository.deleteById(id);
    } else  {
      List<Long> ids = new ArrayList<>();
      author.getBooks().forEach(book -> ids.add(book.getId()));
      List<Book> books = bookRepository.findAllById(ids);
      books.forEach(book -> book.getAuthors().remove(author));
      bookRepository.saveAll(books);
      authorRepository.deleteById(id);
    }

  }

}
