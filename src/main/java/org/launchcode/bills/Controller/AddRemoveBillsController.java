package org.launchcode.bills.Controller;

import org.launchcode.bills.Models.Bills;
import org.launchcode.bills.Models.Data.BillsCreationDTO;
import org.launchcode.bills.Models.Data.BillsDao;
import org.launchcode.bills.Models.Data.MonthlyBillDao;
import org.launchcode.bills.Models.MonthlyBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "bills")
public class AddRemoveBillsController {

        @Autowired
        private BillsDao billsDao;

        @Autowired
        private MonthlyBillDao monthlyBillDao;

        @RequestMapping(value = "add", method = RequestMethod.GET)
        public String displayAddBillForm(Model model) {
                model.addAttribute("title", "Add Bill");
                model.addAttribute(new Bills());
                return "bills/add";
        }

        @RequestMapping(value = "add", method = RequestMethod.POST)
        public String processAddBillForm(@ModelAttribute @Valid Bills newBill,
                                         Errors errors, Model model) {


                if (errors.hasErrors()) {
                        model.addAttribute("title", "Add Bill");
                        return "bills/add";
                }

                for (Bills bill: billsDao.findAll()){
                        String a = bill.getDtype();
                        int b = bill.getRoommates();
                        if (a == null) {
                                newBill.setRoommates(b);
                        }

                }

                billsDao.save(newBill);
                return "redirect:/home";
        }

        @RequestMapping(value = "remove", method = RequestMethod.GET)
        public String displayRemoveBillForm(Model model) {

                BillsCreationDTO bto = new BillsCreationDTO();
                List<Integer> ids = new ArrayList<>();
                for (Bills bill: billsDao.findAll()) {
                        String a = bill.getDtype();
                        if ( a == null) {
                                int id = bill.getId();
                                ids.add(id);
                        }
                }


                model.addAttribute("bills", billsDao.findAllById(ids));
                model.addAttribute("title", "Remove Bill");
                return "bills/remove";
        }

        @RequestMapping(value = "remove", method = RequestMethod.POST)
        public String processRemoveBillForm(@RequestParam Integer[] billsIds) {

                for (Integer billId : billsIds) {
                        System.out.println(billId);
                        billsDao.deleteById(billId);
                }


                return "redirect:/home";
        }

}
