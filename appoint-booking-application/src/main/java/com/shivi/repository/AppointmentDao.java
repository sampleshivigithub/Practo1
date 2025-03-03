package com.shivi.repository;

import com.shivi.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {
}
