package com.shivi.service;

import com.shivi.entity.*;
import com.shivi.exception.*;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface PatientService {


    Patient createPatient(Patient customer) throws PatientException;

    Patient updatePatient(Patient patient, String key) throws PatientException;

    Patient getPatientByUuid(String uuid) throws PatientException;

    CurrentSession getcurrentUserByUuid(String uuid) throws PatientException;

    Appointment bookAppointment(String key, Appointment appointment) throws AppointmentException,
            LoginException, DoctorException, IOException, TimeDateException, MessagingException;

    List<Appointment> getAllAppointmentPatientWise(String key)
        throws AppointmentException, PatientException;


    List<Appointment> getAllAppointmenPatientWise(String key) throws AppointmentException, PatientException;

    Appointment updateAppointment(String key, Appointment newAppointment) throws AppointmentException, PatientException, DoctorException, IOException, TimeDateException;

    List<Doctor> getAllDoctors() throws DoctorException;

    Appointment deleteAppointment(Appointment appointment) throws AppointmentException,
            DoctorException, Exception;

    Review makeReviewToDoctorAppointment(String key, Review review) throws AppointmentException,
            DoctorException, ReviewException;

    Review updateReview(String key, Review review) throws ReviewException;

    Float getDoctorRating(String key, Doctor doctor) throws DoctorException, ReviewException;

    Patient getPatientDetails(String key) throws PatientException;

    Review getReviewOfDoctorByPatient(String key,Review review) throws ReviewException,
            PatientException, DoctorException, AppointmentException;

    Patient forgetPassword(String key, ForgetPassword forgetPassword) throws PasswordException;

    Review deleteReview(String key, Review review) throws ReviewException;


    CurrentSession getCurrentUserByUuid(String key) throws LoginException;
}
