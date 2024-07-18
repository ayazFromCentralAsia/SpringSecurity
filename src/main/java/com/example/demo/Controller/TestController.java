package com.example.demo.Controller;


import com.example.demo.Dao.DaoJpa;
import com.example.demo.Entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;

@Controller
public class TestController {

    final
    DaoJpa daoJpa;

    public TestController(DaoJpa daoJpa) {
        this.daoJpa = daoJpa;
    }


    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setPassword(encoder.encode(user.getPassword()));
        daoJpa.save(user);
        return "redirect:/all";
    }

    @GetMapping("/all")
    public String all(Model model) {
        List<User> users = daoJpa.findAll();
        model.addAttribute("users", users);
        return "all";
    }
}
