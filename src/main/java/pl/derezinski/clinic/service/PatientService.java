package pl.derezinski.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.derezinski.clinic.controller.dto.PatientDto;
import pl.derezinski.clinic.model.Patient;
import pl.derezinski.clinic.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {

    PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void savePatient(PatientDto patientDto) {
        Patient patient = new Patient(patientDto.getFirstName(), patientDto.getLastName(),
                patientDto.getStreet(), patientDto.getZipCode(), patientDto.getCity());
        patientRepository.save(patient);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient getFirstById(Long id) {
        return patientRepository.findFirstById(id);
    }

    public void update(Patient patient) {
        patientRepository.save(patient);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
}
