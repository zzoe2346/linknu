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
    private String destination;//
    private String meetingPoint;//
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;//
    @DateTimeFormat(pattern = "HH:mm")
    private Time departureTime;//
    private int numberOfParticipants;//
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recruitmentDeadline;//

}
