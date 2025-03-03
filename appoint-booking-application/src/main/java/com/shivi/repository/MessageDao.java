package com.shivi.repository;

import com.shivi.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message, Integer> {
}
