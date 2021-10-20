package za.ac.cput.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.Entity.Doctor;
import za.ac.cput.Factory.DoctorFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
class DoctorServiceTest  {

    @Autowired
    private static DoctorService service;
    private static Doctor doctor = DoctorFactory.createDoctor("Sizwe", "Qwabe", 50000.00);

    @Test
    void a_create() {
        Doctor createdDoctor = service.create(doctor);
//        assertEquals(createdDoctor.getDoctorID(), doctor.getDoctorID());
//        System.out.println("Created Doctor" + createdDoctor);
        assertNotNull(doctor);
        System.out.println("Created doctor " + doctor);
    }

    @Test
    void b_read() {
        //Doctor readableDoctor = service.read(doctor.getDoctorID());
        //assertNotNull(readableDoctor);
        //assertEquals(readableDoctor.getDoctorID(), doctor.getDoctorID());
        //System.out.println("Readable Doctor: " + readableDoctor);
        Doctor read = service.read(doctor.getDoctorID());
        assertEquals("Sizwe", read.getFirstName());
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
//        Doctor newDoctor = doctor;
//        newDoctor.setLastName("Mkhize");
//        service.update(newDoctor);
//        assertEquals("Mkhize", service.read(doctor.getDoctorID()).getLastName());
//        System.out.println("Updated " + service.read(doctor.getDoctorID()));
        //----------------------------------------------
        Doctor editedDoctor = new Doctor.Builder().copy(doctor).setFirstName("Peace").build();
        service.update(editedDoctor);
        assertEquals("Peace", this.service.read(doctor.getDoctorID()).getFirstName());
        System.out.println("Edited doctor " + service.read(doctor.getDoctorID()));

    }

    @Test
    void d_delete() {
        boolean deleted = service.delete(doctor.getDoctorID());
        assertTrue(deleted);
        System.out.println("Successfully deleted");
    }

    @Test
    void e_getAll() {
        assertEquals(0, service.getAll().size());
        System.out.println(service.getAll());
    }

}