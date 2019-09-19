package pl.derezinski.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.derezinski.clinic.controller.dto.DoctorDto;
import pl.derezinski.clinic.service.DoctorService;

import javax.validation.Valid;

@Controller
public class DoctorController {

    DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // wejście na stronę umożliwiającą utworzenie doktora
    @GetMapping("/create-doctor")
    public String createDoctor(Model model) {
        model.addAttribute("doctor", new DoctorDto());
        return "createDoctor";
    }

    // obsługa wysłanego formularza
    @PostMapping("/doctor")
    public String createDoctor(@ModelAttribute("doctor") @Valid DoctorDto doctorDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createDoctor";
        }
        doctorService.saveDoctor(doctorDto);
        return "redirect:/";
    }
}
