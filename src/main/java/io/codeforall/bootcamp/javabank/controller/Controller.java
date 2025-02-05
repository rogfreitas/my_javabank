package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {

    private CustomerService customerService;
    public Controller(CustomerService customerService){
        this.customerService=customerService;
    }


    // Map the URL/Verb to the method
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String control(Model model) {

// Add an attribute to the request
        model.addAttribute("customers",customerService.getCustomers());

// Return the view name
        return "thyme";

    }
}