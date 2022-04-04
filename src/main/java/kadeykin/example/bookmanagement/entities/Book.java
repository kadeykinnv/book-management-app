package kadeykin.example.bookmanagement.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
public class Book {

  @Id
  @Column(name = "book_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotNull
  @Size(max = 36)
  private String name;

  @Temporal(TemporalType.DATE)
  private Date ReleaseDate;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
  })
  @JoinTable(name = "books_authors",
          joinColumns = @JoinColumn(name = "book_id"),
          inverseJoinColumns = @JoinColumn(name = "author_id"))
  Set<Author> authors = new HashSet<>();

  @Positive
  private BigDecimal price;
}
