package org.launchcode.bills.Controller;

import org.launchcode.bills.Models.Bills;
import org.launchcode.bills.Models.Data.BillsCreationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.launchcode.bills.Models.Data.BillsDao;
import org.launchcode.bills.Models.Data.MonthlyBillDao;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = {"home",""})
public class HomeController {

    @Autowired
    private BillsDao billsDao;

    @Autowired
    private MonthlyBillDao monthlyBillDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String monthlyBillFormAdd(Model model) {


        BillsCreationDTO bto = new BillsCreationDTO();
        for (Bills bill : billsDao.findAll()) {
            String a = bill.getDtype();
            if (a == null) {
                int b = bill.getId();
                bto.addBills(bill);
                for (Bills bill2: bto.getBills()){
                    bill2.setId(b);
                }
                bto.setRoommates(bill.getRoommates());

           }


        }

        model.addAttribute("title", "Bills");
        model.addAttribute("bto", bto);
        return "bills/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processAddMonthlyBillForm(@RequestParam Integer[] roommates) {

        for (Integer roommate: roommates) {

            int a = roommate;

            for (Bills bill : billsDao.findAll()) {
                String b = bill.getDtype();
                if (b == null) {
                    bill.setRoommates(a);
                    billsDao.save(bill);
                }

            }
        }

            return "redirect:/home";

    }
}






