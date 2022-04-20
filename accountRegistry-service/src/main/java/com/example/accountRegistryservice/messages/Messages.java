package com.example.accountRegistryservice.messages;
/**
 *Class with all user messages
 */
public enum Messages {
    USERNAME_TAKEN("Username already taken"),
    EMAIL_TAKEN("Email already tanked"),
    ACCOUNT_CREATED("Account created"),
    FILL_THE_FIELDS("Fields doesn't filled"),
    NUMBER_OF_PASSWORD_CHARS("The password need have at least 6 chars"),
    NUMBER_OF_USERNAME_CHARS("The username need have at least 5 chars"),
    GENERIC_ERROR("Server error, pls try again later ");


    public String message;


    Messages(String message) {
        this.message = message;
    }
}
