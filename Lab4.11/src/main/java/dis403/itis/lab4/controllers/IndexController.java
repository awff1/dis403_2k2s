package dis403.itis.lab4.controllers;


import dis403.itis.lab4.model.Person;
import dis403.itis.lab4.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import dis403.itis.lab4.model.Phone;
import dis403.itis.lab4.service.PhoneService;

import java.util.List;

@Controller
public class IndexController {

    private final PhoneService phoneService;
    private final PersonService personService;

    public IndexController(PhoneService phoneService, PersonService personService) {
        this.phoneService = phoneService;
        this.personService = personService;
    }

    @GetMapping("/")
    public String index(Model model) {

//        List<Phone> phones = phoneService.findAll();
//
//        model.addAttribute("phones", phones);


        List<Person> persons = personService.findAll();

        model.addAttribute("persons", persons);


        return "index";
    }

}