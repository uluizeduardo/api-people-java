package project.person.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.person.api.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
