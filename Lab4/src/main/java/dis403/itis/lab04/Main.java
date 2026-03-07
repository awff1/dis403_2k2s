package dis403.itis.lab04;

import dis403.itis.lab04.model.Phone;
import dis403.itis.lab04.repository.PhoneRepository;
import dis403.itis.lab04.service.PhoneService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import dis403.itis.lab04.config.Config;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        Phone phone = new Phone();
        phone.setNumber("2313");
        phone.setId(1L);

        PhoneRepository repository = context.getBean(PhoneRepository.class);

        PhoneService phoneService = context.getBean(PhoneService.class);

        phoneService.save(phone);

        List<Phone> phoneList = phoneService.findAll();

        phoneList.forEach(p -> System.out.println(p.getNumber()));



    }
}