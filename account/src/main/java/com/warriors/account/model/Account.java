package com.warriors.account.model;



import com.warriors.account.warrior.Warrior;

import javax.persistence.*;


/**
 * A generic account model entity to be used as a base of account
 */
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer warriorId;

    public void setWarriorId(Integer warriorId) {
        if (warriorId != null) {
            this.warriorId = warriorId;
        }

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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", warriorId=" + warriorId +
                '}';
    }
}
