package project.person.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import project.person.api.dto.request.PersonDTO;
import project.person.api.entities.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    //Constante que retorna o método Mappers.getMapper
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    //Instrução para o MapStract receber data no formato (dd-MM-yyyy)
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
