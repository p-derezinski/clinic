package pl.derezinski.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.derezinski.clinic.model.Doctor;
import pl.derezinski.clinic.model.Patient;
import pl.derezinski.clinic.service.DoctorService;
import pl.derezinski.clinic.service.PatientService;

import java.util.List;

@Controller
public class MainController {

    PatientService patientService;
    DoctorService doctorService;

    @Autowired
    public MainController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("clinicName", "Health First");
        List<Patient> listOfPatients = patientService.getAll();
        List<Doctor> listOfDoctors = doctorService.getAll();
        model.addAttribute("listOfPatients", listOfPatients);
        model.addAttribute("listOfDoctors", listOfDoctors);
        return "index";
    }

}