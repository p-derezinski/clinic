package pl.derezinski.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.derezinski.clinic.controller.dto.PatientDto;
import pl.derezinski.clinic.model.Patient;
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

    // obsługa wysłanego formularza (i utworzenia nowego pacjenta)
    @PostMapping("/patient")
    public String createPatient(@ModelAttribute("patient") @Valid PatientDto patientDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createPatient";
        }
        patientService.savePatient(patientDto);
        return "redirect:/";
    }

    // obsługa wyświetlania pacjenta
    @GetMapping("/patient/{id}")
    public String showPatient(@PathVariable("id") Long id, Model model) {
        Patient patientToView = patientService.getFirstById(id);
        model.addAttribute("patientToView", patientToView);
        return "patient";
    }

    // obsługa aktualizacji danych pacjenta
    @PutMapping("/patient/{id}")
    public String updatePatient(@PathVariable("id") Long id, @ModelAttribute("patientToView") @Valid Patient patient) {
        patientService.update(patient);
//        Patient patientToUpdate = patientService.getFirstById(id);
//        patientToUpdate.setFirstName(patient.getFirstName());
//        patientToUpdate.setLastName(patient.getLastName());
//        patientToUpdate.setStreet(patient.getStreet());
//        patientToUpdate.setZipCode(patient.getZipCode());
//        patientToUpdate.setCity(patient.getCity());
//        patientService.update(patientToUpdate);
        return "redirect:/patient/{id}";
    }

    // obsługa usuwania pacjenta
    @DeleteMapping("/patient/{id}")
    public String deletePatient(@PathVariable("id") Long id) {
        patientService.deleteById(id);
        return "redirect:/";
    }
}
