package org.launchcode.bills.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class RecordFilter {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name ="id")
    private int id;

    private String name;

    private String business;

    private String type;

    private String month;

    private Integer year;



    public RecordFilter(){};

    public RecordFilter (String name, String business, String website, String type, String month, Integer year){
        this.name = name;
        this.business = business;
        this.type = type;
        this.month = month;
        this.year = year;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
