package kadeykin.example.bookmanagement.repositories;

import kadeykin.example.bookmanagement.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  Book findByName(String name);
}
