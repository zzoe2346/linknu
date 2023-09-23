package com.example.linknu.dto;

import com.example.linknu.Entity.TaxiParty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;

@Data
public class TaxiPartyDto {
    private TaxiParty taxiParty;
    private Long id=taxiParty.getId();
    private String title=taxiParty.getTitle();//
    private String content= taxiParty.getContent();//
    private String partyLeaderEmail= taxiParty.getPartyLeaderEmail();
    private String destination= taxiParty.getDestination();//중요
    private String meetingPoint=taxiParty.getMeetingPoint();//중요
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate=taxiParty.getDepartureDate();//
    @DateTimeFormat(pattern = "HH:mm")
    private Time departureTime=taxiParty.getDepartureTime();//중요
    private int numberOfParticipants=taxiParty.getNumberOfParticipants();//중요 모집인원
    //현재 인원 현황 조인써야하나
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recruitmentDeadline=taxiParty.getRecruitmentDeadline();
    private Long numberOfEnrolled;
}
