package itis.dis.lab5.controller;

import itis.dis.lab5.security.UserDetailImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping("/")
    public  String index(Model model) {
        UserDetailImpl userDetail =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", userDetail.getUsername());
        return "index";
    }
}
