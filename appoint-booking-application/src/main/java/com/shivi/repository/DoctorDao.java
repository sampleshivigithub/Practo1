package com.shivi.repository;

import com.shivi.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {

    public Doctor findByMobileNo(String mobileNo);
}
