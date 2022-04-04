package kadeykin.example.bookmanagement.dtos.mapper;

import kadeykin.example.bookmanagement.dtos.transferObjects.AuthorDto;
import kadeykin.example.bookmanagement.entities.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface AuthorMapper {
  AuthorDto authorToDto(Author model);
  Author authorDtoToModel(AuthorDto dto);



}
