package pl.derezinski.clinic.controller.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.derezinski.clinic.model.Location;

import java.time.LocalDate;

@Data
public class AppointmentDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    private String appointmentTime;
    private Location location;
    private Long doctorId;

}
