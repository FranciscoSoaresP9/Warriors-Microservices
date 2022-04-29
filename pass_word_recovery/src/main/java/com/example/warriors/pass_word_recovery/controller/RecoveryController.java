package com.example.warriors.pass_word_recovery.controller;

import com.example.warriors.pass_word_recovery.model.Account;
import com.example.warriors.pass_word_recovery.model.AccountChangePasswordDto;
import com.example.warriors.pass_word_recovery.service.RecoveryPasswordService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/recoveryPassword")
public class RecoveryController {
    private final RecoveryPasswordService recoveryPasswordService;

    @Autowired
    public RecoveryController(RecoveryPasswordService recoveryPasswordService) {
        this.recoveryPasswordService = recoveryPasswordService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> recoveryPassword(@PathVariable String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(recoveryPasswordService.recovery(username));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody AccountChangePasswordDto account){
        try {
            recoveryPasswordService.changePassword(account);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Password Changed");
    }
}
