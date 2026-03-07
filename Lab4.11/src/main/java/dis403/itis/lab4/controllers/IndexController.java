package dis403.itis.lab4.controllers;


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

    public IndexController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<Phone> phones = phoneService.findAll();

        model.addAttribute("phones", phones);

        return "index";
    }

}