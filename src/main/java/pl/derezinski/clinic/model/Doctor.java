package pl.derezinski.clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor {

    @Id                                               // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.AUTO)   // AUTO_INCREMENT
    private Long id;
    private String firstName;
    private String lastName;
    private MedicalSpecialization medicalSpecialization;

    public Doctor(String firstName, String lastName, MedicalSpecialization medicalSpecialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.medicalSpecialization = medicalSpecialization;
    }
}
