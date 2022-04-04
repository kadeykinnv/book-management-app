package kadeykin.example.bookmanagement.dtos.transferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookSlimDto {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("price")
  private BigDecimal price;

}

