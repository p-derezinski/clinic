package pl.derezinski.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.derezinski.clinic.model.Appointment;
import pl.derezinski.clinic.model.Patient;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    Appointment findFirstById(Long id);

    List<Appointment> findAllByPatient(Patient patient);

}
