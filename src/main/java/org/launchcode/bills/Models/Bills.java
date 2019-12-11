package org.launchcode.bills.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Bills {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name ="id")
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    private String business;

    private String website;

    private String dtype;

    private float pay_amount;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    private float amount;

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    @NotNull(message = "Must select a value")
    @NotEmpty
    private String type;

    private int roommates;

    public Bills(){};

    public Bills (String name, String business, String website, String type, float amount, float pay_amount, int roommates){
        this.name = name;
        this.business = business;
        this.website = website;
        this.type = type;
        this.amount = amount;
        this.pay_amount= pay_amount;
        this.roommates = roommates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getRoommates() {
        return roommates;
    }

    public void setRoommates(int roommates) {
        this.roommates = roommates;
    }

    public float getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(float pay_amount) {
        this.pay_amount = pay_amount;
    }

    public void setId(int id) {
        this.id = id;
    }
}
