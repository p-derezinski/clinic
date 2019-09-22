package pl.derezinski.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.derezinski.clinic.controller.dto.DoctorDto;
import pl.derezinski.clinic.model.Doctor;
import pl.derezinski.clinic.repository.DoctorRepository;

import java.util.ArrayList;
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

    public List<Long> getAllIdNumbers() {
        List<Doctor> listOfDoctors = getAll();
        List<Long> listOfIdNumbers = new ArrayList<>();
        for (Doctor doctor : listOfDoctors) {
            listOfIdNumbers.add(doctor.getId());
        }
        return listOfIdNumbers;
    }

    public void update(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }
}
