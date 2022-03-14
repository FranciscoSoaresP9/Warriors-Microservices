package com.warriors.points.messages;
/**
 *Class with all user messages
 */
public enum Messages {
    USERNAME_TAKEN("Username already taken"),
    EMAIL_TAKEN("Email already tanked"),
    ACCOUNT_CREATED("Account created"),
    LOGIN_SUCCESSFULlY("Login successfully"),
    FILL_THE_FIELDS("Fields doesn't filled"),
    NUMBER_OF_PASSWORD_CHARS("The password need have at least 6 chars"),
    NUMBER_OF_USERNAME_CHARS("The username need have at least 5 chars"),
    GENERIC_ERROR("Server error, pls try again later "),
    WARRIOR_NAME_TAKEN("Warrior already taken"),
    WARRIOR_CREATED("Warrior created")
    ;

    public String message;

    Messages(String message) {
        this.message = message;
    }
}
