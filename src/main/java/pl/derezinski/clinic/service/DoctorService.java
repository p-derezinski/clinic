package pl.derezinski.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.derezinski.clinic.controller.dto.DoctorDto;
import pl.derezinski.clinic.model.Doctor;
import pl.derezinski.clinic.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void saveDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor(doctorDto.getFirstName(), doctorDto.getLastName(), doctorDto.getMedicalSpecialization());
        doctorRepository.save(doctor);
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public Doctor getFirstById(Long id) {
        return doctorRepository.findFirstById(id);
    }
}
