package pl.derezinski.clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
    private Patient patient;

    public Appointment(LocalDate appointmentDate, String appointmentTime, Location location, Doctor doctor, Patient patient) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.location = location;
        this.doctor = doctor;
        this.patient = patient;
    }
}
