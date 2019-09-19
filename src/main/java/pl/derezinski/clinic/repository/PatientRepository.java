package pl.derezinski.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.derezinski.clinic.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findFirstById(Long id);

}
