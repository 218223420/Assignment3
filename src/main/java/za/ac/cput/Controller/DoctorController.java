package za.ac.cput.Controller;
/*  Doctor.java
    Entity for the Doctor
    Author: Bheka Gumede (218223420)
    Date: 6 June 2021
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Entity.Doctor;
import za.ac.cput.Factory.DoctorFactory;
import za.ac.cput.Service.DoctorService;

import java.util.Set;

@RestController
@RequestMapping("/doctor")
public class DoctorController
{
    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Doctor create (@RequestBody Doctor doctor)
    {
        Doctor newDoctor = DoctorFactory.createDoctor(doctor.getFirstName(), doctor.getLastName(), doctor.getSalary());
        return doctorService.create(newDoctor);

    }

    @GetMapping("/getall")
    public Set<Doctor> getAll()
    {
        return doctorService.getAll();
    }

    @GetMapping("/read/{doctorid}")
    public Doctor read(@RequestBody Doctor doctor)
    {
        return doctorService.read(doctor.getDoctorID());
    }

    @PostMapping("/update")
    public Doctor update(@RequestBody Doctor doctor)
    {
        return doctorService.update(doctor);
    }

    @PostMapping("/delete/{doctorid}")
    public String delete(@RequestBody Doctor doctor)
    {
        if(doctorService.delete(doctor.getDoctorID()))
        return "Successfully deleted";
        else
            return "Not deleted";

    }

    @GetMapping("/getallwithb")
    public Set<Doctor> getallwithb()
    {
        return doctorService.getAllDoctorStartWithB();
    }

}

//    @GetMapping("/read")
//    public Doctor read(@RequestBody Doctor doctor)
//    {
//        return doctorService.read(doctor.getDoctorID());
//    }getDoctorID
//
//    @PostMapping("/delete")
//    public String delete(@RequestBody Doctor doctor)
//    {
//        if(doctorService.delete(doctor.getDoctorID()));
//
//            return "Successfully deleted";
//
//    }

//        public Doctor create (Doctor doctor)
//        {
//            Doctor newDoctor = DoctorFactory.createDoctor(doctor.getFirstName(), doctor.getLastName(), doctor.getSalary());
//
//            return doctorService.create(newDoctor);
//        }

//    @Autowired
//    private DoctorService doctorService;
//
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    //@PostMapping("/create") ---one and the same things with the first one
//    public Doctor create(@RequestBody Doctor doctor)
//    {
//        Doctor newDoctor = DoctorFactory.createDoctor(doctor.getFirstName(), doctor.getLastName(),  doctor.getSalary());
//        return doctorService.create(newDoctor);
//    }
//
//    @GetMapping("/read/{doctorID}")
//    //public Doctor read(@RequestBody Doctor doctorID)
//    public Doctor read(@PathVariable String doctorID)
//    {
//       // return doctorService.read(doctorID.getDoctorID());
//        return doctorService.read(doctorID);
//    }
//
//    @GetMapping("/update")
//    public Doctor update(@RequestBody Doctor doctor)
//    //public Doctor update(@PathVariable String firstName)
//    {
//       return doctorService.update(doctor);
//       // return doctorService.update(firstName);
//    }
//
//    @PostMapping("/delete/{doctorID}")
//    //public String delete(@RequestBody Doctor doctor)
//     public boolean delete(@PathVariable String doctorID)
//    {
//        //if(doctorService.delete(doctor.getDoctorID()))
//          //  return "Successfully deleted";
//       // else
//            //return"Could not delete doctor";
//
//        return doctorService.delete(doctorID);
//    }
//
//
//    @GetMapping("/getall")
//    public Set<Doctor> getAll()
//    {
//        return doctorService.getAll();
//    }
//
//    @GetMapping("/getallwithb")
//    public Set<Doctor> getallwithb()
//    {
//        return doctorService.getAllDoctorStartWithB();
//    }
//
//}
