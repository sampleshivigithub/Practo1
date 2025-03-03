package com.shivi.service;

import com.shivi.entity.*;
import com.shivi.exception.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface DoctorService {

    List<Doctor> getAllDoctorsRegisterFromDatabase() throws DoctorException;

    Doctor getDoctorByUuid(String uuid) throws PatientException;

    CurrentSession getCurrentUserByUuid(String uuid) throws LoginException;

    List<LocalDateTime> getDoctorAvailableTimingForBooking(String key, Doctor doctor) throws IOException, TimeDateException, DoctorException;

    List<Appointment> getUpcommingDoctorAppointment(Doctor doctor) throws AppointmentException;

    List<Appointment> getPastDoctorAppointment(Doctor doctor) throws AppointmentException;

    List<Appointment> getAllAppointments(Doctor registerDoctor) throws DoctorException;

    Doctor getDoctorDetails(String key) throws PatientException;

    Review getReviewOfParticularAppointment(String key, Appointment appointment) throws PatientException, ReviewException;

    List<Patient> getListOfPatient();

}

