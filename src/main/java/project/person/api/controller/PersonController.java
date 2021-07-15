package project.person.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.person.api.dto.request.PersonDTO;
import project.person.api.dto.response.MessageResponseDTO;
import project.person.api.exception.PersonNotFoundException;
import project.person.api.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    //Método para criar usuário (Pessoa)
    @CrossOrigin("*")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    //Método para listar todos os usuários (pessoas)
    @CrossOrigin("*")
    @GetMapping("/listAll")
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    //Método para buscar usuário(pessoa) por id
    @CrossOrigin("*")
    @GetMapping("/findById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO FindById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    //Método para atualizar usuário(pessoa) através do Id
    @CrossOrigin("*")
    @PutMapping("/updateById/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    //Método para deletar usuário(pessoa) por Id
    @CrossOrigin("*")
    @DeleteMapping("deleteById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletPerson(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
