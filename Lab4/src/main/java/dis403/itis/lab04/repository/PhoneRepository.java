package dis403.itis.lab04.repository;

import dis403.itis.lab04.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    @Query("select p from Phone p where p.number like :num")
    List<Phone> getPhoneLike(@Param("num") String num);
}
