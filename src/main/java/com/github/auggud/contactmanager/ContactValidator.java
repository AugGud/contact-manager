package com.github.auggud.contactmanager;

public class ContactValidator {
    // email validation
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".com");
    }

    // phone validation
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\+?[0-9]+");
    }

    // name validation
    public static boolean isValidName(String name) {
        return  name != null && !name.isBlank();
    }
}
