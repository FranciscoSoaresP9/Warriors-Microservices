package com.warriors.login.model.warrior;

public class WarriorDTO {
    private String name;
    private String warriorType;
    private int accountId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWarriorType() {
        return warriorType;
    }

    public void setWarriorType(String warriorType) {
        this.warriorType = warriorType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public WarriorDTO(String name, String warriorType, int accountId) {
        this.name = name;
        this.warriorType = warriorType;
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "CreateWarriorDTO{" +
                "accountID=" + accountId +
                ", warriorName='" + name + '\'' +
                ", warriorType='" + warriorType + '\'' +
                '}';
    }
}
