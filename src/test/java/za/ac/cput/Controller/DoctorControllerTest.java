package za.ac.cput.Controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.Entity.Doctor;
import za.ac.cput.Factory.DoctorFactory;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

 class DoctorControllerTest {
    private static Doctor doctor = DoctorFactory.createDoctor("Peace", "Smith", 48000.00);


    public static  String user_Name ="root";
    public static String password  = "password";


    @Autowired
    private TestRestTemplate restTemplate;
    private final String baseURL = "http://localhost:8080/doctor";



    @Test
    void a_create()
    {
        String url = baseURL + "/create";
        ResponseEntity<Doctor> postResponse = restTemplate.postForEntity(url, doctor, Doctor.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        doctor = postResponse.getBody();
        System.out.println("Saved data: " + doctor);
        assertEquals(doctor.getDoctorID(), postResponse.getBody().getDoctorID());
    }

    @Test
    void b_read() {
        String  url = baseURL + "/read/" + doctor.getDoctorID();
        System.out.println("URL: " + url);
        ResponseEntity<Doctor> response = restTemplate.getForEntity(url, Doctor.class);
        assertEquals(doctor.getDoctorID(), Objects.requireNonNull(response.getBody()).getDoctorID());

        //System.out.println("Read: " + read);
    }

    @Test
    void c_update()
    {
        Doctor updated = new Doctor.Builder().copy(doctor).setFirstName("Lucky").build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<Doctor> response = restTemplate.postForEntity(url, updated, Doctor.class);
        assertNotNull(response.getBody());
    }

    @Test
    void d_delete()
    {
        String url = baseURL + "/delete" + doctor.getDoctorID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
    }


    @Test
    void e_getAll()
    {
        String url = baseURL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth(user_Name,password)
                .exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show all:");
        System.out.println(response);
        System.out.println(response.getBody());


    }


}