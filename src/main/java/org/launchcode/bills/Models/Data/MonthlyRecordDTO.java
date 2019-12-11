package org.launchcode.bills.Models.Data;

import org.launchcode.bills.Models.Bills;
import org.launchcode.bills.Models.MonthlyBill;
import org.launchcode.bills.Models.MonthlyRecord;

import java.util.ArrayList;
import java.util.List;

public class MonthlyRecordDTO {
    private List<MonthlyRecord> records = new ArrayList<>();

    public float personal_amount;

    public float shared_amount;

    public MonthlyRecordDTO(List<MonthlyRecord> records) {
        this.records = records;
        this.personal_amount = personal_amount;
        this.shared_amount = shared_amount;
    }


    public MonthlyRecordDTO() {
    }



    public void addRecords(MonthlyBill bill) {
        MonthlyRecord newBill = new MonthlyRecord();
        newBill.setType(bill.getType());
        newBill.setName(bill.getName());
        newBill.setWebsite(bill.getWebsite());
        newBill.setBusiness(bill.getBusiness());
        newBill.setAmount(bill.getAmount());
        newBill.setPay_amount(bill.getPay_amount());
        newBill.setMonth(bill.getMonth());
        newBill.setYear(bill.getYear());
        newBill.setDtype("MonthlyRecord");
        newBill.setRoommates(bill.getRoommates());
        records.add(newBill);
    }

    public void addRecords2(MonthlyRecord record) {
        MonthlyRecord newBill = new MonthlyRecord();
        newBill.setType(record.getType());
        newBill.setName(record.getName());
        newBill.setWebsite(record.getWebsite());
        newBill.setBusiness(record.getBusiness());
        newBill.setAmount(record.getAmount());
        newBill.setPay_amount(record.getPay_amount());
        newBill.setMonth(record.getMonth());
        newBill.setYear(record.getYear());
        newBill.setDtype("MonthlyRecord");
        newBill.setRoommates(record.getRoommates());
        records.add(newBill);
    }

    public List<MonthlyRecord> getRecords() {
        return records;
    }

    public void setRecords(List<MonthlyRecord> records) {
        this.records = records;
    }

    public float getPersonal_amount() {
        return personal_amount;
    }

    public void setPersonal_amount(float personal_amount) {
        this.personal_amount = personal_amount;
    }

    public float getShared_amount() {
        return shared_amount;
    }

    public void setShared_amount(float shared_amount) {
        this.shared_amount = shared_amount;
    }
}
