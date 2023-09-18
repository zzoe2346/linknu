package com.example.linknu.controller;

import com.example.linknu.Entity.TaxiParty;
import com.example.linknu.dto.LoginInfo;
import com.example.linknu.service.TaxiPartyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    @GetMapping("taxiPartyList")
    public String taxiPartyList(
            HttpSession httpSession,
            Model model
                                ){
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        if (loginInfo ==null){
            return "redirect:/";
        }

        List<TaxiParty> taxiPartyBoards = taxiPartyService.getTaxiPartyBoards();
        model.addAttribute("taxiPartyBoards",taxiPartyBoards);

        return "taxiPartyList";
    }

    @GetMapping("taxiPartyRegistration")
    public String taxiPartyRegistration(HttpSession httpSession) {
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        if (loginInfo ==null){
            return "redirect:/";
        }




        return "taxiPartyRegistrationForm";
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
            @RequestParam("recruitmentDeadline")  Date recruitmentDeadline


            ) {
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        if (loginInfo ==null){
            return "redirect:/";
        }

        taxiPartyService.createParty(title, content, destination, meetingPoint, departureDate, departureTime, numberOfParticipants, recruitmentDeadline);

        return "redirect:/";
    }
}
