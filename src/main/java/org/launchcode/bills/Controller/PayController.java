package org.launchcode.bills.Controller;

import org.launchcode.bills.Models.Bills;
import org.launchcode.bills.Models.Data.*;
import org.launchcode.bills.Models.MonthlyBill;
import org.launchcode.bills.Models.MonthlyRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "pay")
public class PayController {

    @Autowired
    private BillsDao billsDao;

    @Autowired
    private MonthlyBillDao monthlyBillDao;

    @Autowired
    private MonthlyRecordDao monthlyRecordDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String MonthYearFormAdd(Model model) {

        //monthlyBillDao.deleteAll();
        BillsCreationDTO bto = new BillsCreationDTO();
        for (Bills bill : billsDao.findAll()) {
            String a = bill.getDtype();
            if (a == null) {
                bto.addBills(bill);
            }


        }

        model.addAttribute("title", "Bills for Month");
        model.addAttribute("bto", bto);
        return "monthly bill/pay";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processAddMonthlyBillForm(@ModelAttribute BillsCreationDTO bto,
                                            //Errors errors,
                                            Model model) {
        monthlyBillDao.deleteAll();
        for (Bills bill : billsDao.findAll()) {
            String a = bill.getDtype();
            if (a == null) {
                bto.addBills(bill);
            }}

        for (MonthlyBill bill2 : bto.getBills()) {
            bill2.setMonth(bto.getMonth());
            bill2.setDtype("MonthlyBill");
            bill2.setYear(bto.getYear());
            monthlyBillDao.save(bill2);
        }






        return "redirect:/pay/month";

    }
    @RequestMapping(value = "/month", method = RequestMethod.GET)
    public String monthlyBillFormAdd(Model model) {
        List<MonthlyBill> monthly_bills = new ArrayList<>();
        for (MonthlyBill bill: monthlyBillDao.findAll()) {
                monthly_bills.add(bill);
            }

        model.addAttribute("title", "Add Monthly Bill");
        model.addAttribute("monthly_bills", monthly_bills);
        return "monthly bill/month";
    }

    @RequestMapping(value = "/month", method = RequestMethod.POST)
    public String processAddBillForm(@RequestParam Float[] bill_amounts, @RequestParam Integer [] billIDs) {
        MonthlyRecordDTO rto = new MonthlyRecordDTO();
        List<Float> amounts = new ArrayList<>();
        for (Float bill_amount : bill_amounts) {
            amounts.add(bill_amount);
        }

        int i = 0;
        while (i < amounts.size()){
        for(MonthlyBill bill: monthlyBillDao.findAll()){

            bill.setAmount(amounts.get(i));

            String type = bill.getType();
            String a = "Personal";
            if(type.equals(a)){
                bill.setPay_amount(amounts.get(i));
                monthlyBillDao.save(bill);
                rto.addRecords(bill);
            }
            else if (bill.getRoommates()==0){
                bill.setPay_amount(amounts.get(i));
                monthlyBillDao.save(bill);
                rto.addRecords(bill);
            }
            else{
                bill.setPay_amount((amounts.get(i)/bill.getRoommates()));
                monthlyBillDao.save(bill);
                rto.addRecords(bill);
            }
            i = i+1;

            }


        }

        for (MonthlyRecord record: rto.getRecords()){
            monthlyRecordDao.save(record);
        }


//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Bill");
//            return "monthly bill/month";
//        }
//        for (MonthlyBill newBill2: monthlyBillDao.findAll()){
//            monthlyBillDao.save(newBill2);
//        }

        return "redirect:/records";
    }

}
