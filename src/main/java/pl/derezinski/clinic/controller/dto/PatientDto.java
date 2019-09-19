package pl.derezinski.clinic.controller.dto;

import lombok.Data;

@Data
public class PatientDto {

    private String firstName;
    private String lastName;
    private String street;
    private String zipCode;
    private String city;

}
