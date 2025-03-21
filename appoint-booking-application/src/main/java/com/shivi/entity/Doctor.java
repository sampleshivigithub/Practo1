package com.shivi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_table"
    )

    @SequenceGenerator(
            name = "id_table",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    private Integer doctorId;


    @Pattern(regexp = "^[0-9]{10}$", message = "Please enter valid mobile number")
    private String mobileNo;


    private String password;

    private String name;

    private String specialty;

    private String location;

    //	@Column(name = "insurance")
    private Boolean insuranceAcceptance;

    private String education;

    private String experience;

    //	@OneToMany(cascade = CascadeType.ALL,mappedBy = "appointmentId")
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Appointment> listOfAppointments = new ArrayList<>();

    // put time only 24 hours formate

    //	@Column(name = "from")
    private Integer appointmentFromTime;

    // put time only 24 hours formate

    //	@Column(name = "to")
    private Integer appointmentToTime;

    // for checking this is doctor or patient

    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> listOfReviews = new ArrayList<>();

    private String doctorImg;

    private Boolean validDoctor = true;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Message> listOfMessage = new ArrayList<>();

}
