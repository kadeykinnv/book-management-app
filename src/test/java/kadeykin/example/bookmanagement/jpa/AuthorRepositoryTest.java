package kadeykin.example.bookmanagement.jpa;

import kadeykin.example.bookmanagement.entities.Author;
import kadeykin.example.bookmanagement.entities.Book;
import kadeykin.example.bookmanagement.repositories.AuthorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class AuthorRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  private AuthorRepository authorRepository;

  @BeforeEach
  void setAuthorRepository() {
    Set<Book> books = new HashSet<>();
    Book book = new Book();
    book.setName("Harry Potter");
    books.add(book);
    Author author = new Author();
    author.setName("bob");
    author.setSurname("fisher");
    author.setBirthday(new Date());
    entityManager.persist(author);
  }

  @Test
  void injectedComponentsAreNotNull(){
    Author author = authorRepository.findByName("bob");
    assertThat(authorRepository).isNotNull();
    assertThat(entityManager).isNotNull();
  }
  @Test
  @DisplayName("Testing findByName method")
  public void tryGetAuthorByName() {
    String given = "bob";
    String expected = "bob";
    Author author = authorRepository.findByName(given);
    assertThat(author.getName()).isEqualTo(expected);
  }
  @ParameterizedTest
  @ValueSource(strings = "37Char37Char37Char37Char37Char37Char3")
  @DisplayName("Test of Constraint of Author's name")
  public void trySaveAuthorWithNotValidName(String given) {
    Author author = new Author();
    author.setName(given);
    author.setSurname("Fish");
    assertThrows(ConstraintViolationException.class,
            () -> authorRepository.save(author));
  }
  @ParameterizedTest
  @ValueSource(strings = "37Char37Char37Char37Char37Char37Char3")
  @DisplayName("Test of Constraint of Author's surname")
  public void trySaveAuthorWithNotValidSurname(String given) {
    Author author = new Author();
    author.setName("bob");
    author.setSurname(given);
    assertThrows(ConstraintViolationException.class,
            () -> authorRepository.save(author));
  }

  @Test
  public void tryDeleteAuthorById() {
    //given
    Author author = authorRepository.findByName("bob");
    //when
    authorRepository.deleteById(author.getId());
    //then
    assertThat(authorRepository.findByName("bob")).isNull();
  }












}
