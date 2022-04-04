package kadeykin.example.bookmanagement.dtos.mapper;

import kadeykin.example.bookmanagement.dtos.transferObjects.BookDto;
import kadeykin.example.bookmanagement.entities.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface BookMapper {

  BookDto bookToDto(Book model);
  Book bookDtoToModel(BookDto dto);
}
