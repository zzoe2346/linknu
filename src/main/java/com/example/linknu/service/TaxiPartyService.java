package com.example.linknu.service;

import com.example.linknu.Entity.TaxiParty;
import com.example.linknu.Entity.TaxiPartyUser;
import com.example.linknu.dto.LoginInfo;
import com.example.linknu.dto.TaxiPartyDto;
import com.example.linknu.dto.User;
import com.example.linknu.repository.TaxiPartyRepository;
import com.example.linknu.repository.TaxiPartyUserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaxiPartyService {
    @Autowired
    TaxiPartyRepository taxiPartyRepository;
    @Autowired
    TaxiPartyUserRepository taxiPartyUserRepository;
    @Autowired
    HttpSession httpSession;
    public TaxiParty createParty(String title, String content, String destination, String meetingPoint, Date departureDate, String departureTime, int numberOfParticipants, Date recruitmentDeadline) {

        LoginInfo loginInfo = (LoginInfo)httpSession.getAttribute("loginInfo");
        User user = loginInfo.getUser();
        TaxiParty taxiParty;
        LocalTime localTime = LocalTime.parse(departureTime); // 문자열을 LocalTime으로 파싱

        // LocalTime을 java.sql.Time으로 변환
        Time sqlTime = Time.valueOf(localTime);

         TaxiParty saved = taxiPartyRepository.save(new TaxiParty(null, title, content, user.getEmail(), destination, meetingPoint, departureDate, sqlTime, numberOfParticipants,0, recruitmentDeadline));
         return saved;



    }

    public List<TaxiParty> getTaxiPartyBoards() {
        return taxiPartyRepository.findAll();
        //return taxiPartyRepository.findJoin();
    }

    public TaxiParty getTaxiPartyBoard(long boardId) {
        Optional<TaxiParty> byId = taxiPartyRepository.findById(boardId);
        if (byId.isPresent()){
            return byId.get();
        }//null이면 어쩌지 그런데 null이 나올수가 있는가?
        else return null;
    }

    // 참여인원 체크. 만약에 인원수 다 찼으면 등록 불가
    public boolean checkNumberOfParticipants(Long boardId,int numberOfParticipants){
        Long countByBoardId = taxiPartyUserRepository.countByBoardId(boardId);
        if(countByBoardId>=numberOfParticipants) return false;//더 하면 안댐
        else return true;
    }
    public void addUserToPartyUserTable(String email, Long boardId, String phoneNumber) {
        //인원수 다 찼나? 여부
        //레포지토리 생성후 세이브한다.
        Optional<TaxiParty> byId = taxiPartyRepository.findById(boardId);
        int currentNumberOfEnrolled = byId.get().getNumberOfEnrolled();
        byId.get().setNumberOfEnrolled(currentNumberOfEnrolled+1);
        taxiPartyRepository.save(byId.get());
        taxiPartyUserRepository.save(new TaxiPartyUser(null, boardId, email,phoneNumber));

    }
}
