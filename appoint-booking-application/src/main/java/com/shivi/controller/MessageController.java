package com.shivi.controller;

import com.shivi.entity.CurrentSession;
import com.shivi.entity.Doctor;
import com.shivi.entity.Message;
import com.shivi.entity.Patient;
import com.shivi.exception.DoctorException;
import com.shivi.exception.LoginException;
import com.shivi.exception.PatientException;
import com.shivi.service.DoctorLoginService;
import com.shivi.service.DoctorService;
import com.shivi.service.MessageService;
import com.shivi.service.PatientAndAdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    PatientAndAdminLoginService loginService;

    @Autowired
    DoctorLoginService doctorLoginService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    MessageService messageService;

    @PostMapping("/patientToDoctor")
    @CrossOrigin
    public ResponseEntity<Message> sendMessageFromPatientToDoctor(@RequestParam String key, @RequestBody Message message)
            throws LoginException, PatientException, DoctorException{

        if(loginService.checkUserLoginOrNot(key)) {

            Message sendMessage = messageService.sendMessageFromPatientToDoctor(key, message);

            return new ResponseEntity<Message>(sendMessage, HttpStatus.OK);


        }else {

            throw new LoginException("Invalid key or please login first");

        }


    }

    @PostMapping("/patientMessage")
    @CrossOrigin
    public ResponseEntity<List<Message>> getMessageOfPatientParticularDoctor
            (@RequestParam String key, @RequestBody Doctor doctor) throws LoginException, DoctorException, PatientException{

        if(loginService.checkUserLoginOrNot(key)) {

            List<Message> listOfMessage = messageService.getMessageOfPatientParticularDoctor(key, doctor);

            return new ResponseEntity<List<Message>>(listOfMessage, HttpStatus.OK);


        }else {

            throw new LoginException("Invalid key or please login first");

        }
    }

    @PostMapping("/doctorToPatient")
    @CrossOrigin
    public ResponseEntity<Message> sendMessageFromDoctorToPatient(@RequestParam String key, @RequestBody Message message) throws LoginException, PatientException, DoctorException{

        if(doctorLoginService.checkUserLoginOrNot(key)) {

            CurrentSession currentUserSession = doctorService.getCurrentUserByUuid(key);

            Doctor registerDoctor = doctorService.getDoctorByUuid(key);

            if(!currentUserSession.getUserType().equals("doctor")) {

                throw new LoginException("Please login as doctor");

            }

            if(registerDoctor != null) {

                Message sendMessage = messageService.sendMessageFromDoctorToPatient(key, message);

                return new ResponseEntity<Message>(sendMessage, HttpStatus.OK);

            }else {

                throw new DoctorException("Please enter valid doctor details");

            }

        }else {

            throw new LoginException("Please enter valid key");

        }


    }

    @PostMapping("/doctorMessage")
    @CrossOrigin
    public ResponseEntity<List<Message>> getMessageOfDoctorParticularPatient
            (@RequestParam String key, @RequestBody Patient patient)
            throws LoginException, DoctorException, PatientException {

        if(doctorLoginService.checkUserLoginOrNot(key)) {

            CurrentSession currentUserSession = doctorService.getCurrentUserByUuid(key);

            Doctor registerDoctor = doctorService.getDoctorByUuid(key);

            if(!currentUserSession.getUserType().equals("doctor")) {

                throw new LoginException("Please login as doctor");

            }

            if(registerDoctor != null) {

                List<Message> listOfMessage = messageService.getMessageOfDoctorParticularPatient(key, patient);

                return new ResponseEntity<List<Message>>(listOfMessage, HttpStatus.OK);

            }else {

                throw new DoctorException("Please enter valid doctor details");

            }

        }else {

            throw new LoginException("Please enter valid key");

        }

    }

}

