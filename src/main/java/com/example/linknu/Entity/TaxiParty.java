package com.example.linknu.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaxiParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;//
    private String content;//
    private String partyLeaderEmail;
    private String destination;//중요
    private String meetingPoint;//중요
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;//
    @DateTimeFormat(pattern = "HH:mm")
    private Time departureTime;//중요
    private int numberOfParticipants;//중요
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recruitmentDeadline;//

}
