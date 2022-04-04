package com.example.accountRegistryservice.validations;
/**
 *Class all fields validations
 */
public enum ValidationsProperties {
    PASSWORD_CHARS_NUMBER(6),
    USERNAME_CHARS_NUMBER(5);

    public int value;
     ValidationsProperties(int value){
         this.value=value;
     }
}
