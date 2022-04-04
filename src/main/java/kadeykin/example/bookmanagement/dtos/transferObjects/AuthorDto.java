package kadeykin.example.bookmanagement.dtos.transferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope = AuthorDto.class)
public class AuthorDto {


  @JsonProperty("id")
  private Long id;
  @Size(max = 36)
  @NotNull
  @JsonProperty("name")
  private String name;
  @Size(max = 36)
  @NotNull
  @JsonProperty("surname")
  private String surname;
  @JsonProperty("birthDate")
  private Date birthday;
  @JsonProperty("books")
  private Set<BookSlimDto> books;
}
