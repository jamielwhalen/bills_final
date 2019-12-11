package org.launchcode.bills.Controller;

import org.launchcode.bills.Models.Data.UserDao;
import org.launchcode.bills.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "sign_up")
public class CreateUserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayCreateUserForm(Model model) {
        model.addAttribute("title", "Create User");
        model.addAttribute(new User());
        return "create_user/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processAddBillForm(@ModelAttribute @Valid User newUser,
                                     Errors errors, Model model) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Create User");
            return "create_user/index";
        }

        userDao.save(newUser);
        return "redirect:/bills";
    }

}
