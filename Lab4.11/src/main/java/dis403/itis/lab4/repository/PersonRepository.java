package dis403.itis.lab4.repository;

import dis403.itis.lab4.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
