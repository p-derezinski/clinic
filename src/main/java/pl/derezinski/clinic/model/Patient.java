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
public class Patient {

    @Id                                               // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.AUTO)   // AUTO_INCREMENT
    private Long id;
    private String firstName;
    private String lastName;
    private String street;
    private String zipCode;
    private String city;

    public Patient(String firstName, String lastName, String street, String zipCode, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }
}
