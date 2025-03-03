package com.shivi.service;

import com.shivi.entity.Doctor;
import com.shivi.exception.DoctorException;

import java.util.List;

public interface AdminDoctorService {

    Doctor registerDoctor(Doctor doctor) throws DoctorException;

    Doctor revokePermissionOfDoctor(Doctor doctor) throws DoctorException;

    Doctor grantPermissionOfDoctor(Doctor doctor) throws DoctorException;

    List<Doctor> getAllValidInValidDoctors(String key) throws DoctorException;
}
