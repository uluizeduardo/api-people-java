package project.person.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.person.api.dto.response.MessageResponseDTO;
import project.person.api.entities.Person;
import project.person.api.repository.PersonRepository;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository personRepository;

    //Injeção de dependência
    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //Método para criar usuário (Pessoa)
    @PostMapping
    public MessageResponseDTO getPerson(@RequestBody Person person){
        Person savePerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savePerson.getId())
                .build();
    }
}
