package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerController(CustomerService customerService){
        this.customerService=customerService;
    }


    // Map the URL/Verb to the method
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String showCustomer(Model model) {

// Add an attribute to the request
        model.addAttribute("customers",customerService.getCustomers());

// Return the view name
        return "thyme";

    }
}