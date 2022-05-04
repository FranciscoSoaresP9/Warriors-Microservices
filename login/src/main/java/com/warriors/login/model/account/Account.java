package com.warriors.login.model.account;


import com.warriors.login.model.warrior.Warrior;


/**
 * A model of Account
 */

public class Account {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer warriorId;


    public void setWarriorId(Integer warriorId) {
        this.warriorId = warriorId;
    }

    public Integer getWarriorId() {
        return warriorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
