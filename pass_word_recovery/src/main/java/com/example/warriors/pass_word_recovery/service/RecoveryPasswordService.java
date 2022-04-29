package com.example.warriors.pass_word_recovery.service;

import com.example.warriors.pass_word_recovery.model.AccountChangePasswordDto;
import org.json.JSONException;

import java.io.IOException;

public interface RecoveryPasswordService {

    String recovery(String username) throws IOException;

    void changePassword(AccountChangePasswordDto account) throws JSONException, IOException;
}
