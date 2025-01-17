package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Entity.Doctor;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, String>
{

}
