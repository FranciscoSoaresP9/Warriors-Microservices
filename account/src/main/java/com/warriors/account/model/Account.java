package com.warriors.account.model;



import com.warriors.account.warrior.Warrior;

import javax.persistence.*;


/**
 * A generic account model entity to be used as a base for concrete types of accounts
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
    @OneToOne
    private Warrior warrior;


    /**
     * Create Warrior if the Player don't have
     *
     * @param warrior
     * @see Warrior
     */
    public void creatWarrior(Warrior warrior) {
        if (warrior != null) {
            this.warrior = warrior;
        }

    }

    public Warrior getWarrior() {
        return warrior;
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
                ", warrior=" + warrior +
                '}';
    }
}
