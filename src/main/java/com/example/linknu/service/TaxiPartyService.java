package com.example.linknu.service;

import com.example.linknu.Entity.TaxiParty;
import com.example.linknu.dto.LoginInfo;
import com.example.linknu.dto.User;
import com.example.linknu.repository.TaxiPartyRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Service
public class TaxiPartyService {
    @Autowired
    TaxiPartyRepository taxiPartyRepository;
    @Autowired
    HttpSession httpSession;
    public void createParty(String title, String content, String destination, String meetingPoint, Date departureDate, String departureTime, int numberOfParticipants, Date recruitmentDeadline) {

        LoginInfo loginInfo = (LoginInfo)httpSession.getAttribute("loginInfo");
        User user = loginInfo.getUser();
        TaxiParty taxiParty;
        LocalTime localTime = LocalTime.parse(departureTime); // 문자열을 LocalTime으로 파싱

        // LocalTime을 java.sql.Time으로 변환
        Time sqlTime = Time.valueOf(localTime);
        taxiPartyRepository.save(new TaxiParty(null, title, content, user.getEmail(), destination, meetingPoint, departureDate, sqlTime, numberOfParticipants, recruitmentDeadline));



    }

    public List<TaxiParty> getTaxiPartyBoards() {
        return taxiPartyRepository.findAll();
    }
}
