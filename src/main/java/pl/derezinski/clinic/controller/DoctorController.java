package pl.derezinski.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.derezinski.clinic.controller.dto.DoctorDto;
import pl.derezinski.clinic.model.Doctor;
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

    // obsługa wysłanego formularza (i utworzenia nowego doktora)
    @PostMapping("/doctor")
    public String createDoctor(@ModelAttribute("doctor") @Valid DoctorDto doctorDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createDoctor";
        }
        doctorService.saveDoctor(doctorDto);
        return "redirect:/";
    }

    // obsługa wyświetlania doktora
    @GetMapping("/doctor/{id}")
    public String showDoctor(@PathVariable("id") Long id, Model model) {
        Doctor doctorToView = doctorService.getFirstById(id);
        model.addAttribute("doctorToView", doctorToView);
        return "doctor";
    }

    // obsługa usuwania doktora
    @DeleteMapping("/doctor/{id}")
    public String deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteById(id);
        return "redirect:/";
    }
}
