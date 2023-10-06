package com.example.linknu.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UtilService {
    public String createRandomCode() {

        Random r = new Random();


        char[] codeTable = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', '!', '@', '$'
        };
        // 필요한 문자들 배열로 미리 저장해줘야 함

        String code = "";
        int codeLength = 10; // 코드 길이
        for(int i = 1; i <= codeLength; i++) {
            int index = r.nextInt(codeTable.length);
            // 코드 테이블의 길이만큼 난수 생성하는 메서드 인덱스에 저장
            code += codeTable[index];
            // 생성된 난수를 배열 인덱스로 사용하여 코드 가져와서 문자열에 저장하기
        }
        return code;
    }


}
