package com.example.linknu.service;

import com.example.linknu.Entity.TaxiPartyUser;
import com.example.linknu.repository.TaxiPartyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    TaxiPartyUserRepository taxiPartyUserRepository;

    public boolean knuCheck(String mail) {
        for (String s : mail.split("@")) {
            System.out.println(s);

        }



        if (mail.split("@")[1].equals("knu.ac.kr")) {
            return true;
        } else {
            return false;
        }
    }

    public void sendAuthMail(String code,String userEmail) {
        //단순 문자 메일 보낼수 있는 객체 생성
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("LinKnu 회원가입 인증메일입니다");
        message.setText("인증코드 입니다. "+ code);
        message.setTo(userEmail);

        javaMailSender.send(message);
    }

    public void sendSuccessMail(Long boardId){
        List<TaxiPartyUser> taxiPartyUsers = taxiPartyUserRepository.findByBoardId(boardId);

        StringBuilder numbers = new StringBuilder();
        //번호 리스트
        for(int i=0;i<taxiPartyUsers.size();i++){
            if (taxiPartyUsers.get(i).getPhoneNumber()!=null){
                numbers.append(taxiPartyUsers.get(i).getPhoneNumber());
                numbers.append("\n");
            }
        }

        for(int i=0;i<taxiPartyUsers.size();i++){

            if(taxiPartyUsers.get(i).getPhoneNumber() == null){
                SimpleMailMessage message = new SimpleMailMessage();
                message.setSubject("택시파티에 성공하였습니다! 파티원들에게 연락하세요!");
                message.setText("파티원 연락처 입니다. \n"+ numbers.toString());
                message.setTo(taxiPartyUsers.get(i).getEmail());

                javaMailSender.send(message);
            }
            else{
                SimpleMailMessage message = new SimpleMailMessage();
                message.setSubject("택시파티에 성공하였습니다! 파티장의 연락을 기다려주세요.");
                message.setText("파티가 성공적으로 구성되었습니다. 파티장의 연락을 기다려주세요.");
                message.setTo(taxiPartyUsers.get(i).getEmail());

                javaMailSender.send(message);

            }



        }
    }

    public void sendHtmlEmail() {

    }


}
