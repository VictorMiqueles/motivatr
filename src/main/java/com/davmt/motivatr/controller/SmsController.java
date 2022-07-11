package com.davmt.motivatr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;


@Controller
public class SmsController {

        

        @GetMapping(value = "/sendSMS")
        public ResponseEntity<String> sendSMS() {

                Dotenv dotenv = Dotenv.load();
                Twilio.init(dotenv.get("TWILIO_ACCOUNT_SID"), dotenv.get("TWILIO_AUTH_TOKEN"));
                // Twilio.init("AC444947d3a9b16b556fa8023312410c22", "4c2ab8ce8af6271ce2b9a6b551c834c1");

                Message.creator(new PhoneNumber(dotenv.get("VICTOR_PHONE")),
                                new PhoneNumber(dotenv.get("TWILIO_NUM")), "Hello from Twilio 📞").create();

                return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
        }
}