package itis.dis.lab5.controller;

import itis.dis.lab5.security.UserDetailImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/index")
    public String index(Model model) {
        UserDetailImpl userDetail =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(System.out::println);
        model.addAttribute("user", userDetail.getUsername());

        return "adminindex";
    }
}
