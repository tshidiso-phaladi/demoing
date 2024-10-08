package za.co.study.casetshidiso.service;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
public class AppStateService implements Serializable {

    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}