package com.shivi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reviewId;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private Appointment appointment;

    private String reviewContent;

    private float rating;

    @Override
    public int hashCode() {
        return Objects.hash(reviewId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        return Objects.equals(reviewId, other.reviewId);
    }



}
