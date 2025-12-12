package br.com.erudio.mapper;

import br.com.erudio.dto.PersonDTO;
import br.com.erudio.model.Person;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO toDTO(Person person);

    Person toEntity(PersonDTO dto);

    List<PersonDTO> toDTOList(List<Person> persons);

    List<Person> toEntityList(List<PersonDTO> dtos);
}
