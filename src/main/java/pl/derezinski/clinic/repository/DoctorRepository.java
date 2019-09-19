package pl.derezinski.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.derezinski.clinic.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Doctor findFirstById(Long id);

}
