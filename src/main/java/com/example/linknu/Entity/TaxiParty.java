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
    private Integer numberOfParticipants;//중요 모집인원
    private Integer numberOfEnrolled;
    //인원이 다 안차도 진행할거에요 or 인원이 미달이면 진행 안할 거에요
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Integer partyPolicy;

}
