package kadeykin.example.bookmanagement.dtos.transferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AuthorSlimDto {
  @JsonProperty("id")
  private Long id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("surname")
  private String surname;
  @JsonProperty("birthDate")
  private Date birthday;
}
