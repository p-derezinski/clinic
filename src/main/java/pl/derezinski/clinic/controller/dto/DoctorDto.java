package pl.derezinski.clinic.controller.dto;

import lombok.Data;
import pl.derezinski.clinic.model.MedicalSpecialization;

@Data
public class DoctorDto {

    private String firstName;
    private String lastName;
    private MedicalSpecialization medicalSpecialization;

}
