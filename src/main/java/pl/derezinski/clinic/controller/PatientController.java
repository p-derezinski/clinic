package pl.derezinski.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.derezinski.clinic.controller.dto.PatientDto;
import pl.derezinski.clinic.service.PatientService;

import javax.validation.Valid;

@Controller
public class PatientController {

    PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // wejście na stronę umożliwiającą utworzenie pacjenta
    @GetMapping("/create-patient")
    public String createPatient(Model model) {
        model.addAttribute("patient", new PatientDto());
        return "createPatient";
    }

    // obsługa wysłanego formularza
    @PostMapping("/patient")
    public String createPatient(@ModelAttribute("patient") @Valid PatientDto patientDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createPatient";
        }
        patientService.savePatient(patientDto);
        return "redirect:/";
    }
}