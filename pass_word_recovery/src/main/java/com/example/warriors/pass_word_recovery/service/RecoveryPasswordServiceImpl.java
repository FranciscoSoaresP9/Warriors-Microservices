package com.example.warriors.pass_word_recovery.service;

import com.example.warriors.pass_word_recovery.model.AccountChangePasswordDto;
import com.example.warriors.pass_word_recovery.requestsender.RequestSender;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class RecoveryPasswordServiceImpl implements RecoveryPasswordService {
    private final EmailService emailService;
    private final RequestSender requestSender;

    @Autowired
    public RecoveryPasswordServiceImpl(EmailService emailService, RequestSender requestSender) {
        this.emailService = emailService;
        this.requestSender = requestSender;
    }


    @Override
    public String recovery(String username) throws IOException {
        String code = codeGenerator();
        String email = requestSender.getEmailByUserName(username);
        emailService.sendEmail(email, code);
        return code;
    }

    @Override
    public void changePassword(AccountChangePasswordDto account) throws JSONException, IOException {
        requestSender.changePassword(account);
    }

    private String codeGenerator() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}
