package org.launchcode.bills.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "monthly_record")
public class MonthlyRecord extends Bills {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name ="id")
    private int id;

    //private Float amount;

    //@NotNull
    private String month;

    private int year;

    public MonthlyRecord(){};

    public MonthlyRecord (Float amount, String month, int year){
        this.month = month;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}