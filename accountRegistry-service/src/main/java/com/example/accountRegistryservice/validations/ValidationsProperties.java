package com.example.accountRegistryservice.validations;
/**
 *Class with the requisites of fields
 */
public enum ValidationsProperties {
    PASSWORD_CHARS_NUMBER(6),
    USERNAME_CHARS_NUMBER(5);

    public int value;
     ValidationsProperties(int value){
         this.value=value;
     }
}
