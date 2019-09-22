package pl.derezinski.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.derezinski.clinic.controller.dto.AppointmentDto;
import pl.derezinski.clinic.model.Appointment;
import pl.derezinski.clinic.model.Doctor;
import pl.derezinski.clinic.model.Patient;
import pl.derezinski.clinic.repository.AppointmentRepository;
import pl.derezinski.clinic.repository.DoctorRepository;
import pl.derezinski.clinic.repository.PatientRepository;

import java.util.List;

@Service
public class AppointmentService {

    AppointmentRepository appointmentRepository;
    PatientRepository patientRepository;
    DoctorRepository doctorRepository;

    PatientService patientService;
    DoctorService doctorService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository,
                              PatientService patientService, DoctorService doctorService) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public void saveAppointment(AppointmentDto appointmentDto, Long patientId) {
        Patient patient = patientRepository.findFirstById(patientId);
        Doctor doctor = doctorRepository.findFirstById(appointmentDto.getDoctorId());
        Appointment appointment = new Appointment(appointmentDto.getAppointmentDate(), appointmentDto.getAppointmentTime(),
                appointmentDto.getLocation(), doctor, patient);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAllForExistingPatientsAndDoctors() {
        List<Appointment> listOfAppointments = appointmentRepository.findAll();
        List<Appointment> listOfAppointmentsToModify = appointmentRepository.findAll();
        List<Long> listOfPatientIdNumbers = patientService.getAllIdNumbers();
        List<Long> listOfDoctorIdNumbers = doctorService.getAllIdNumbers();
        for (Appointment appointment : listOfAppointments) {
            if (!listOfPatientIdNumbers.contains(appointment.getPatient().getId())
                || !listOfDoctorIdNumbers.contains(appointment.getDoctor().getId())) {
                listOfAppointmentsToModify.remove(appointment);
            }
        }
        return listOfAppointmentsToModify;
    }
}
