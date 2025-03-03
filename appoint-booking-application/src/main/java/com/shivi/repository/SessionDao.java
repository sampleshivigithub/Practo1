package com.shivi.repository;

import com.shivi.entity.CurrentSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionDao extends JpaRepository<CurrentSession, Integer> {

    public CurrentSession findbyUuid(String uuid);

     void findByUuid( );

    CurrentSession findByUuid(String key);
}
