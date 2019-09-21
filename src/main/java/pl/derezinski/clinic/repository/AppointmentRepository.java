package pl.derezinski.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.derezinski.clinic.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    Appointment findFirstById(Long id);

}
