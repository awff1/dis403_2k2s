package dis403.itis.lab4.controllers;

import dis403.itis.lab4.model.Person;
import dis403.itis.lab4.model.Phone;
import dis403.itis.lab4.service.PersonService;
import dis403.itis.lab4.service.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    private PhoneService phoneService;
    private PersonService personService;


    public PageController(PhoneService phoneService, PersonService personService) {
        this.phoneService = phoneService;
        this.personService = personService;
    }

    @GetMapping("/add")
    public String addPerson() {
        return "enter";
    }

    @PostMapping("/enter")
    public String enter(@RequestParam String name, @RequestParam String phone_number) {
        Phone phone = new Phone(phone_number);

        Person person = new Person();
        person.setName(name);
        person.setPhone(phone);

        phoneService.save(phone);
        personService.save(person);

        return "redirect:/";
    }


}
