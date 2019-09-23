package pl.derezinski.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.derezinski.clinic.controller.dto.AppointmentDto;
import pl.derezinski.clinic.model.Appointment;
import pl.derezinski.clinic.model.Doctor;
import pl.derezinski.clinic.model.Patient;
import pl.derezinski.clinic.service.AppointmentService;
import pl.derezinski.clinic.service.DoctorService;
import pl.derezinski.clinic.service.PatientService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AppointmentController {

    AppointmentService appointmentService;
    PatientService patientService;
    DoctorService doctorService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, PatientService patientService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    // wejście na stronę umożliwiającą umówienie wizyty
    @GetMapping("/make-appointment/{id}")
    public String makeAppointment(@PathVariable("id") Long id, Model model) {
        return addAttributesToTheModelAndReturnMakeAppointment(id, model);
    }

    // obsługa wysłanego formularza (i umówienia nowej wizyty)
    @PostMapping("/appointment")
    public String makeAppointment(@ModelAttribute("appointment") @Valid AppointmentDto appointmentDto,
                                  @RequestParam(name = "patientId") Long patientId,
                                  Model model,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "makeAppointment";
        }
        // obsługa sytuacji, gdy w formularzu wybrano nieistniejące ID doktora
        if (!doctorService.getAllIdNumbers().contains(appointmentDto.getDoctorId())) {
            String errorMessage = "There is no doctor with the chosen ID. Please insert data again.";
            model.addAttribute("errorMessage", errorMessage);
            return addAttributesToTheModelAndReturnMakeAppointment(patientId, model);
        }
        appointmentService.saveAppointment(appointmentDto, patientId);
        return "redirect:/";
    }

    // obsługa wyświetlania wizyty
    @GetMapping("/appointment/{id}")
    public String showAppointment(@PathVariable("id") Long id, Model model) {
        Appointment appointmentToView = appointmentService.getFirstById(id);
        model.addAttribute("appointmentToView", appointmentToView);
        return "appointment";
    }

    // obsługa aktualizacji danych wizyty
    @PutMapping("/appointment/{id}")
    public String updateAppointment(@PathVariable("id") Long id, @RequestParam(name = "newAppointmentTime") String newAppointmentTime) {
        Appointment appointmentToUpdate = appointmentService.getFirstById(id);
        appointmentToUpdate.setAppointmentTime(newAppointmentTime);
        appointmentService.update(appointmentToUpdate);
        return "redirect:/appointment/{id}";
    }

    // obsługa usuwania wizyty
    @DeleteMapping("/appointment/{id}")
    public String deleteAppointment(@PathVariable("id") Long id) {
        appointmentService.deleteById(id);
        return "redirect:/";
    }

    private String addAttributesToTheModelAndReturnMakeAppointment(Long id, Model model) {
        model.addAttribute("appointment", new AppointmentDto());
        Patient patientToView = patientService.getFirstById(id);
        model.addAttribute("patientToView", patientToView);
        List<Doctor> listOfDoctors = doctorService.getAll();
        model.addAttribute("listOfDoctors", listOfDoctors);
        return "makeAppointment";
    }
}
