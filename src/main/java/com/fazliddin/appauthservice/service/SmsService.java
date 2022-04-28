package com.fazliddin.appauthservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author Fazliddin Xamdamov
 * @since 21.12.2021
 */
@Component
public class SmsService {

    @Value("${twilio.account_sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth_token}")
    private String AUTH_TOKEN;

    @Value("${twilio.phone_number}")
    private String myPhoneNumber;

    public void sendMessage(String phoneNumber, String verificationCode) {
        System.out.println(verificationCode);
//        try {
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//            Message.creator(
//                    new PhoneNumber(phoneNumber),
//                    new PhoneNumber(myPhoneNumber),
//                    "Sizning tasdiqlash kodingiz: " + verificationCode + "\nBuni hech kimga bermang!!!"
//            ).create();
//        } catch (Exception e) {
//            throw RestException.serverError();
//        }
    }
}
