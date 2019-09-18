package pl.derezinski.clinic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello {

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("clinicName", "Health First");
        return "index";
    }

}
