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

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
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
}
