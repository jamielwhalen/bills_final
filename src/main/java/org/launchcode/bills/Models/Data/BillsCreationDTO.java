package org.launchcode.bills.Models.Data;

import org.launchcode.bills.Models.Bills;
import org.launchcode.bills.Models.MonthlyBill;

import java.util.ArrayList;
import java.util.List;

public class BillsCreationDTO {
    private List<MonthlyBill> bills = new ArrayList<>();

    public BillsCreationDTO(List<MonthlyBill> bills) {
        this.bills = bills;
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.roommates = roommates;

    }
    public String month;
    public int year;
    public float amount;
    public int roommates;


    public BillsCreationDTO() {
    }



    public void addBills(Bills bill) {
        MonthlyBill newBill = new MonthlyBill();
        newBill.setId(bill.getId());
        newBill.setType(bill.getType());
        newBill.setName(bill.getName());
        newBill.setWebsite(bill.getWebsite());
        newBill.setBusiness(bill.getBusiness());
        newBill.setRoommates(bill.getRoommates());
        bills.add(newBill);
        }
    public List<MonthlyBill> getBills() {
        return bills;
    }


    public void setBills(List<MonthlyBill> bills) {
        this.bills = bills;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getRoommates() {
        return roommates;
    }

    public void setRoommates(int roommates) {
        this.roommates = roommates;
    }
}
