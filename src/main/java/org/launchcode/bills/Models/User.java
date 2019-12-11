package org.launchcode.bills.Models;

import org.apache.naming.factory.SendMailFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;


@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String username;

    @NotNull
    @Size(min=3, max=15)
    private String password;

    @NotNull
    @Size(min=3, max=15, message = "You must verify your password.")
    private String verify;

    @NotNull
    private int roommates;

    @Email
    private String rm_email;

    @Email
    private String rm_email2;

    @Email
    private String rm_email3;

    @Email
    private String rm_email4;

    public User(){};

    public User (String username, String password, String verify, int roommates, String rm_email, String rm_email2, String rm_email3, String rm_email4){
        this.username = username;
        this.password = password;
        this.verify = verify;
        this.roommates = roommates;
        this.rm_email = rm_email;
        this.rm_email2 = rm_email2;
        this.rm_email3 = rm_email3;
        this.rm_email4 = rm_email4;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoommates() {
        return roommates;
    }

    public void setRoommates(int roommates) {
        this.roommates = roommates;
    }

    public String getRm_email() {
        return rm_email;
    }

    public void setRm_email(String rm_email) {
        this.rm_email = rm_email;
    }

    public String getRm_email2() {
        return rm_email2;
    }

    public void setRm_email2(String rm_email2) {
        this.rm_email2 = rm_email2;
    }

    public String getRm_email3() {
        return rm_email3;
    }

    public void setRm_email3(String rm_email3) {
        this.rm_email3 = rm_email3;
    }

    public String getRm_email4() {
        return rm_email4;
    }

    public void setRm_email4(String rm_email4) {
        this.rm_email4 = rm_email4;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }
}