package org.launchcode.bills.Controller;

import org.launchcode.bills.Models.Bills;
import org.launchcode.bills.Models.Data.*;
import org.launchcode.bills.Models.MonthlyBill;
import org.launchcode.bills.Models.MonthlyRecord;
import org.launchcode.bills.Models.RecordFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "records")
public class RecordsController {

    @Autowired
    private BillsDao billsDao;

    @Autowired
    private MonthlyBillDao monthlyBillDao;

    @Autowired
    private MonthlyRecordDao monthlyRecordDao;

    @Autowired
    private RecordFilterDao recordFilterDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String monthlyRecord(Model model) {


        MonthlyRecordDTO rto = new MonthlyRecordDTO();

        float personal = 0;
        float shared = 0;

        for (MonthlyBill bill : monthlyBillDao.findAll()) {
                rto.addRecords(bill);
                String a = bill.getType();
                if (a.equals("Personal")){
                    personal = personal + bill.getPay_amount();
                }
                else {
                    shared = shared + bill.getPay_amount();
                }
                rto.setPersonal_amount(personal + shared);
                rto.setShared_amount(shared);
            }


        model.addAttribute("title", "This Month's Bills");
        model.addAttribute("rto", rto);
        //////////////////////////////////////////////////////////////////////
        List<MonthlyRecord> records = new ArrayList<>();

        RecordFilter filter = new RecordFilter();

        for(RecordFilter filter1: recordFilterDao.findAll()){
            filter = filter1;
        }

        for (MonthlyRecord record1: monthlyRecordDao.findAll()) {
            if (filter.getType() == null && filter.getName() == null && filter.getBusiness() == null
                    && filter.getMonth() == null && filter.getYear() == null) {
                records.add(record1);
            } else {
                String type = record1.getType();
                String name = record1.getName();
                String business = record1.getBusiness();
                String month = record1.getMonth();
                Integer year = record1.getYear();

                String ftype = filter.getType();
                String fname = filter.getName();
                String fbusiness = filter.getBusiness();
                String fmonth = filter.getMonth();
                Integer fyear = filter.getYear();

                if (ftype == null) {
                    ftype = type;
                }

                if (fname == null) {
                    fname = name;
                }

                if (fbusiness == null) {
                    fbusiness = business;
                }

                if (fmonth == null) {
                    fmonth = month;
                }

                if (fyear == null) {
                    fyear = year;
                }

                if (type.equals(ftype)) {
                    if (name.equals(fname)) {
                        if (business.equals(fbusiness)) {
                            if (month.equals(fmonth)) {
                                if (year.equals(fyear)) {
                                    records.add(record1);
                                }
                            }
                        }
                    }

                }
            }
        }






        model.addAttribute("records", records);

        //////////////////////////////////////////////////////////////////////

        List<String> types = new ArrayList<>();
        types.add("Type");
        List<String> names = new ArrayList<>();
        names.add("Name");
        List<String> businesses = new ArrayList<>();
        businesses.add("Business");
        List<String> months = new ArrayList<>();
        months.add("Month");
        List<Integer> years = new ArrayList<>();

        for (MonthlyRecord record : monthlyRecordDao.findAll()){
            String type = record.getType();
            if(!types.contains(type)){
                types.add(type);}

            String name = record.getName();
            if(!names.contains(name)){
                names.add(name);}

            String business = record.getBusiness();
            if(!businesses.contains(business)){
                businesses.add(business);}

            String month = record.getMonth();
            if(!months.contains(month)){
                months.add(month);}

            Integer year = record.getYear();
            if(!years.contains(year)){
                years.add(year);}
        }
        model.addAttribute("types", types);
        model.addAttribute("names", names);
        model.addAttribute("businesses", businesses);
        model.addAttribute("months", months);
        model.addAttribute("years", years);


        return "records/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processAddMonthlyBillForm(@RequestParam String[] type, @RequestParam String[] name, @RequestParam String[] business, @RequestParam String[] month, @RequestParam String[] year){

        recordFilterDao.deleteAll();

        RecordFilter record = new RecordFilter();
        for(String t: type) {
            System.out.println(t);
            if (!t.equals("Type")){
                record.setType(t);
            }
        }

        for(String n: name) {
            System.out.println(n);
            if (!n.equals("Name")){
                record.setName(n);
            }
        }

        for(String b: business) {
            System.out.println(b);
            if (!b.equals("Business")){
                record.setBusiness(b);
            }
        }

        for(String m: month) {
            System.out.println(m);
            if (!m.equals("Month")) {
                record.setMonth(m);
            }
        }

        for(String y: year) {
            System.out.println(y);
            if (!y.equals("Year")){
                Integer result = Integer.parseInt(y);
                record.setYear(result);
            }
        }

        recordFilterDao.save(record);





        return "redirect:/records";

    }
}