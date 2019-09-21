package pl.derezinski.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.derezinski.clinic.controller.dto.AppointmentDto;
import pl.derezinski.clinic.model.Patient;
import pl.derezinski.clinic.service.AppointmentService;
import pl.derezinski.clinic.service.PatientService;

import javax.validation.Valid;

@Controller
public class AppointmentController {

    AppointmentService appointmentService;
    PatientService patientService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    // wejście na stronę umożliwiającą umówienie wizyty
    @GetMapping("/make-appointment/{id}")
    public String makeAppointment(@PathVariable("id") Long id, Model model) {
        model.addAttribute("appointment", new AppointmentDto());
        Patient patientToView = patientService.getFirstById(id);
        model.addAttribute("patientToView", patientToView);
        return "makeAppointment";
    }

    // obsługa wysłanego formularza (i umówienia nowej wizyty)
    @PostMapping("/appointment")
    public String makeAppointment(@ModelAttribute("appointment") @Valid AppointmentDto appointmentDto,
                                  @RequestParam(name = "patientId") Long patientId,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "makeAppointment";
        }
        appointmentService.saveAppointment(appointmentDto, patientId);
        return "redirect:/";
    }
}
