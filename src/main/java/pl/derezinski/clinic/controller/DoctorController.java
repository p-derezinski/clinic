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

    // entering the page that allows to create a new doctor
    @GetMapping("/create-doctor")
    public String createDoctor(Model model) {
        model.addAttribute("doctor", new DoctorDto());
        return "createDoctor";
    }

    // handling the creating of a new doctor
    @PostMapping("/doctor")
    public String createDoctor(@ModelAttribute("doctor") @Valid DoctorDto doctorDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createDoctor";
        }
        doctorService.saveDoctor(doctorDto);
        return "redirect:/";
    }

    // handling the displaying of a doctor
    @GetMapping("/doctor/{id}")
    public String showDoctor(@PathVariable("id") Long id, Model model) {
        Doctor doctorToView = doctorService.getFirstById(id);
        model.addAttribute("doctorToView", doctorToView);
        return "doctor";
    }

    // handling the updating of doctor data
    @PutMapping("/doctor/{id}")
    public String updateDoctor(@PathVariable("id") Long id, @ModelAttribute("doctorToView") @Valid Doctor doctor) {
        doctorService.update(doctor);
        return "redirect:/doctor/{id}";
    }

    // handling the deleting of a doctor
    @DeleteMapping("/doctor/{id}")
    public String deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteById(id);
        return "redirect:/";
    }
}
