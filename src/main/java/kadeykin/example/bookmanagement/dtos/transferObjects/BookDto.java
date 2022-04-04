package kadeykin.example.bookmanagement.dtos.transferObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;


@Data
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope = BookDto.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

  @JsonProperty("id")
  private Long id;

  @NotNull
  @Size(max = 36)
  @JsonProperty("name")
  private String name;

  @JsonProperty("authors")
  private Set<AuthorSlimDto> authors;

  @Positive
  @JsonProperty("price")
  private BigDecimal price;
}
