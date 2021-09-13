package com.hk.assignment1_constraintlayout.models;

import android.util.Patterns;

public class LoginUser {
    private String strEmailAddress;
    private String strPassword;

    public LoginUser(String strEmailAddress, String strPassword) {
        this.strEmailAddress = strEmailAddress;
        this.strPassword = strPassword;
    }

    public String getStrEmailAddress() {
        return strEmailAddress;
    }

    public void setStrEmailAddress(String strEmailAddress) {
        this.strEmailAddress = strEmailAddress;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches();
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 5;
    }
}
