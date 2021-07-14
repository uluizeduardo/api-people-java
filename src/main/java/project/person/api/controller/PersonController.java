package project.person.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.person.api.dto.request.PersonDTO;
import project.person.api.dto.response.MessageResponseDTO;
import project.person.api.entities.Person;
import project.person.api.exception.PersonNotFoundException;
import project.person.api.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {


    private PersonService personService;

    @Autowired//Injeção de dependência dentro do construtor
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //Método para criar usuário (Pessoa)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    //Método para listar todos os usuários (pessoas)
    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    //Método para buscar usuário(pessoa) por id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO FindById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    //Método para atualizar usuário(pessoa) através do id
    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletPerson(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
