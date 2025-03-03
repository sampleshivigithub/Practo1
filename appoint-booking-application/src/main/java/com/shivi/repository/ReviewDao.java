package com.shivi.repository;

import com.shivi.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<Review, Integer> {
}
