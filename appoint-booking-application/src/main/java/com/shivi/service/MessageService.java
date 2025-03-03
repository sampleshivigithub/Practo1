package com.shivi.service;

import com.shivi.entity.Doctor;
import com.shivi.entity.Message;
import com.shivi.entity.Patient;
import com.shivi.exception.DoctorException;
import com.shivi.exception.PatientException;

import java.util.List;

public interface MessageService {

    Message sendMessageFromPatientToDoctor(String key, Message message)
            throws PatientException, DoctorException;

    Message sendMessageFromDoctorToPatient(String key, Message message)
            throws PatientException, DoctorException;

    List<Message> getMessageOfPatientParticularDoctor(String key, Doctor doctor)
            throws DoctorException, PatientException;

    List<Message> getMessageOfDoctorParticularPatient(String key, Patient patient)
            throws DoctorException, PatientException;

}
