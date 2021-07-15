package project.person.api.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.person.api.dto.request.PersonDTO;
import project.person.api.dto.response.MessageResponseDTO;
import project.person.api.entities.Person;
import project.person.api.exception.PersonNotFoundException;
import project.person.api.mapper.PersonMapper;
import project.person.api.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    //Método para criar usuário (Pessoa)
    public MessageResponseDTO createPerson(PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);
        Person savePerson = personRepository.save(personToSave);

        return createMessageResponse(savePerson.getId(), "Created person with ID ");
    }

    //Método para listar todos os usuários (pessoas)
    public List<PersonDTO> listAll() {
        List<Person> allPeople =  personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Método para buscar usuário(pessoa) por ID
    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyPersonExist(id);
        return personMapper.toDTO(person);
    }

    //Método para deletar usuário(pessoa) por ID
    public void delete(Long id) throws PersonNotFoundException {
        verifyPersonExist(id);
        personRepository.deleteById(id);
    }

    //Método para atualizar usuário(pessoa) por ID
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyPersonExist(id);

        Person personToUpdate = personMapper.toModel(personDTO);
        Person personUpdate = personRepository.save(personToUpdate);

        return createMessageResponse(personUpdate.getId(), "Update person with ID ");
    }

    //Método auxiliar para verificar se existe usuário(pessoa) pelo ID
    private Person verifyPersonExist(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    //Método auxiliar que retorna messageResponseDTO
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
