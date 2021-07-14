package project.person.api.service;

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
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //Método para criar usuário (Pessoa)

    public MessageResponseDTO createPerson(PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);
        Person savePerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savePerson.getId())
                .build();
    }

    //Método para listar todos os usuários (pessoas)
    public List<PersonDTO> listAll() {
        List<Person> allPeople =  personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Método para buscar usuário(pessoa) por id
    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyPersonExist(id);
        return personMapper.toDTO(person);
    }

    //Método para deletar usuário(pessoa) pelo ID
    public void delete(Long id) throws PersonNotFoundException {
        verifyPersonExist(id);
        personRepository.deleteById(id);
    }

    //Método auxiliar para verificar se existe usuário(pessoa) pelo ID
    public Person verifyPersonExist(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}
