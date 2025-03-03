package com.shivi.controller;

import com.shivi.entity.CurrentSession;
import com.shivi.entity.Doctor;
import com.shivi.exception.DoctorException;
import com.shivi.exception.LoginException;
import com.shivi.service.AdminDoctorService;
import com.shivi.service.PatientAndAdminLoginService;
import com.shivi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminDoctorService adminDoctorService;

    @Autowired
    PatientAndAdminLoginService patientAndAdminLoginService;

    @Autowired
    PatientService patientService;


    @PostMapping("/registerDoctor")
    @CrossOrigin
    public ResponseEntity<Doctor> registerDocotor(@RequestParam String key, @RequestBody Doctor doctor)
            throws DoctorException, LoginException {

        if(patientAndAdminLoginService.checkUserLoginOrNot(key)) {

            CurrentSession currentUserSession = patientService.getCurrentUserByUuid(key);

            if(!currentUserSession.getUserType().equals("admin")) {

                throw new LoginException("Please login as admin");

            }

            if(doctor != null) {

                Doctor registerDoctor = adminDoctorService.registerDoctor(doctor);

                return new ResponseEntity<Doctor>(registerDoctor, HttpStatus.CREATED);

            }else {

                throw new DoctorException("Please enter valid doctor details");
            }

        }else {

            throw new LoginException("Please enter valid key.");
        }


    }

    @DeleteMapping("/revokePermission")
    @CrossOrigin
    public ResponseEntity<Doctor> revokePermissionOfDoctor
            (@RequestParam String key, @RequestBody Doctor doctor) throws LoginException, DoctorException{

        if(patientAndAdminLoginService.checkUserLoginOrNot(key)) {

            CurrentSession currentUserSession = patientService.getCurrentUserByUuid(key);

            if(!currentUserSession.getUserType().equals("admin")) {

                throw new LoginException("Please login as admin");

            }

            Doctor deletedDoctor = adminDoctorService.revokePermissionOfDoctor(doctor);

            return new ResponseEntity<Doctor>(deletedDoctor, HttpStatus.CREATED);



        }else {

            throw new LoginException("Please enter valid key.");
        }
    }

    @PostMapping("/grantPermission")
    @CrossOrigin
    public ResponseEntity<Doctor> grantPermissionOfDoctor
            (@RequestParam String key, @RequestBody Doctor doctor) throws LoginException, DoctorException{

        if(patientAndAdminLoginService.checkUserLoginOrNot(key)) {

            CurrentSession currentUserSession = patientService.getCurrentUserByUuid(key);

            if(!currentUserSession.getUserType().equals("admin")) {

                throw new LoginException("Please login as admin");

            }

            Doctor deletedDoctor = adminDoctorService.grantPermissionOfDoctor(doctor);

            return new ResponseEntity<Doctor>(deletedDoctor, HttpStatus.CREATED);

        }else {

            throw new LoginException("Please enter valid key.");
        }
    }

    @GetMapping("/getValidInValidDoctors")
    @CrossOrigin
    public ResponseEntity<List<Doctor>> getAllValidInValidDoctors
            (@RequestParam String key) throws LoginException, DoctorException{

        if(patientAndAdminLoginService.checkUserLoginOrNot(key)) {

            CurrentSession currentUserSession = patientService.getCurrentUserByUuid(key);

            if(!currentUserSession.getUserType().equals("admin")) {

                throw new LoginException("Please login as admin");

            }

            List<Doctor> listOfValidInValidDoctors = adminDoctorService.getAllValidInValidDoctors(key);

            return new ResponseEntity<List<Doctor>>(listOfValidInValidDoctors, HttpStatus.CREATED);

        }else {

            throw new LoginException("Please enter valid key.");
        }

    }

}

