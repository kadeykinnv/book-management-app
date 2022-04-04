package kadeykin.example.bookmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class Author {

  @Id
  @Column(name = "author_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(max = 36)
  private String name;

  @NotNull
  @Size(max = 36)
  private String surname;

  @Temporal(TemporalType.DATE)
  private Date birthday;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
  private Set<Book> books = new HashSet<>();

  @Column(insertable = true, updatable = false)
  private LocalDateTime created;

  private LocalDateTime modified;


  @PrePersist
  void onCreate() {
    this.setCreated(LocalDateTime.now());
    this.setModified(LocalDateTime.now());
  }

  @PreUpdate
  void onUpdate() {
    this.setModified(LocalDateTime.now());
  }
}
