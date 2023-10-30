package com.example.linknu.controller;

import com.example.linknu.Entity.TaxiParty;
import com.example.linknu.dto.LoginInfo;
import com.example.linknu.dto.TaxiPartyDto;
import com.example.linknu.service.EmailService;
import com.example.linknu.service.TaxiPartyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class TaxiPartyController {
    @Autowired
    TaxiPartyService taxiPartyService;
    @Autowired
    EmailService emailService;

    @GetMapping("taxiPartyList")
    public String taxiPartyList(
            HttpSession httpSession,
            Model model
                                ){
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        if (loginInfo ==null){
            return "redirect:/";
        }
//boardId를 가지고 오고 그 id를 taxi_party_user 카운트 세면됨. 그걸 전달
        List<TaxiParty> taxiPartyBoards = taxiPartyService.getTaxiPartyBoards();

        for (TaxiParty taxiPartyBoard : taxiPartyBoards) {
            System.out.println("taxiPartyBoard = " + taxiPartyBoard.getNumberOfParticipants());
        }


        model.addAttribute("taxiPartyBoards",taxiPartyBoards);

        return "taxi/taxiPartyList";
    }

    @GetMapping("taxiPartyRegistration")
    public String taxiPartyRegistration(HttpSession httpSession) {
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        if (loginInfo ==null){
            return "redirect:/";
        }




        return "taxi/taxiPartyRegistrationForm";
    }

    @PostMapping("taxiPartyRegistration")
    public String taxiPartyRegistration(
            HttpSession httpSession,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("destination") String destination,
            @RequestParam("meetingPoint") String meetingPoint,
            @RequestParam("departureDate")  Date departureDate,

            @RequestParam("departureTime")  String departureTime,
            @RequestParam("numberOfParticipants") int numberOfParticipants,
            @RequestParam("partyPolicy") int partyPolicy //여기 까지 했다(확인)10.04


            ) {
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        if (loginInfo ==null){
            return "redirect:/";
        }
        System.out.println(partyPolicy);

        TaxiParty party = taxiPartyService.createParty(title, content, destination, meetingPoint, departureDate, departureTime, numberOfParticipants,partyPolicy);
        taxiPartyService.addUserToPartyUserTable(loginInfo.getUser().getEmail(),party.getId(),null);

        return "redirect:/";
    }

    @GetMapping("taxiPartyBoard")
    public String taxiPartyBoard(
            Model model,
            @RequestParam("boardId") long boardId
    ) {

        TaxiParty taxiPartyBoard = taxiPartyService.getTaxiPartyBoard(boardId);

        model.addAttribute("taxiPartyBoard",taxiPartyBoard);


        return "taxi/taxiPartyBoard";
    }

    @GetMapping("taxiPartyEnroll")
    public String showTaxiPartyEnroll(
            @RequestParam("boardId") Long boardId,
            Model model
    ){

        model.addAttribute("boardId", boardId);
        return "taxi/taxiPartyEnrollForm";
    }
    @PostMapping("taxiPartyEnroll")
    public String processTaxiPartyEnroll(
            @RequestParam("boardId") Long boardId,
            @RequestParam("phoneNumber") String phoneNumber,
            HttpSession httpSession
    ){
        //등록서비스
        //taxiPartyService.

        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");

        TaxiParty taxiPartyBoard = taxiPartyService.getTaxiPartyBoard(boardId);
        int numberOfParticipants = taxiPartyBoard.getNumberOfParticipants();

        if (taxiPartyService.checkNumberOfParticipants(boardId,numberOfParticipants)){
            //성공.
            System.out.println("boardId = " + boardId + ", phoneNumber = " + phoneNumber);
            taxiPartyService.addUserToPartyUserTable(loginInfo.getUser().getEmail(),boardId,phoneNumber);
            //여기에 인원체크하면? 예) isFullParty-> true or false

            //full party일때
            if(taxiPartyService.isFullParty(boardId)){
                //메일 고고
                emailService.sendSuccessMail(boardId);

            }
            //아닐때 걍 패스하면됨



            return "redirect:/";
        }
        else {
            return "notice/fullParty";
        }

    }

    @GetMapping("deleteTaxiParty")
    public String deleteTaxiPartyBoard(
            @RequestParam("boardId") Long boardId,
            HttpSession httpSession
    ){

        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        String userEmail = loginInfo.getUser().getEmail();
        //boardId이용해서 party_leader_email과 같으면 삭제하면댐.
        boolean isWriter = taxiPartyService.isWriter(boardId, userEmail);

        if(isWriter){
            taxiPartyService.delete(boardId);
            return "notice/deleteSuccessful";
        }
        else {

            return "notice/deleteFail";

        }




    }

    @GetMapping("updateTaxiParty")
    public String updateTaxiParty(
            @RequestParam("boardId") Long boardId,
            HttpSession httpSession,
            Model model
    ){
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        String userEmail = loginInfo.getUser().getEmail();
        //boardId이용해서 party_leader_email과 같으면 삭제하면댐.
        boolean isWriter = taxiPartyService.isWriter(boardId, userEmail);

        if(isWriter){
            TaxiParty taxiPartyBoard = taxiPartyService.getTaxiPartyBoard(boardId);
            model.addAttribute("taxiPartyBoard", taxiPartyBoard);

            return "taxi/updateForm";
        }
        else {

            return "notice/deleteFail";

        }
    }
}
