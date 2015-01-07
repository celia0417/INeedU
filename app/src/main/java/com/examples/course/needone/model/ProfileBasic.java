package com.examples.course.needone.model;

import com.examples.course.needone.Profile;

/**
 * Created by Jialu on 12/20/14.
 */
public class ProfileBasic {
    private String email = "", dob = "", credit = "";
    //private Image image;

    public ProfileBasic() {

    }

    public ProfileBasic(String email) {
        this.email = email;
    }

    public ProfileBasic(String email, String dob, String credit) {
        this.email = email;
        this.dob = dob;
        this.credit = credit;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getDob() { return dob; }

    public void setDob(String dob) { this.dob = dob; }

    public String getCredit() { return credit; }

    public void setCredit(String credit) {this.credit = credit; }
}
