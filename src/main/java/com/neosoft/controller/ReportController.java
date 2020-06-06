package com.neosoft.controller;

import com.neosoft.model.Invoice;
import com.neosoft.repo.ReportRepo;
import com.neosoft.services.ReportServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/ReportController")
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportRepo.class);
    private final ReportServices reportServices;

    @Autowired
    public ReportController(ReportServices reportServices) {
        this.reportServices = reportServices;
    }

    @GetMapping(value = "/home")
    public ModelAndView index(){
        LOGGER.info("Enter Inventory System.");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/reportPage")
    public ModelAndView reportPage(){
        LOGGER.info("Admin at report generation page.");
        return new ModelAndView("reportManagement");
    }

    @PostMapping("/onDate")
    public ModelAndView onDate(@RequestParam Date onDate , ModelMap modelMap){
        LOGGER.info("Generating report on date");
        LOGGER.debug("Date is "+onDate);
        modelMap.addAttribute("onDate" , onDate);
        List<Invoice> invoices = reportServices.onDate(onDate);
        invoices.forEach(e-> LOGGER.debug(String.valueOf(e)));
        return new ModelAndView("viewReportOnSpecificDate" , "invoiceList" , invoices );
    }

    @PostMapping("/btwDate")
    public ModelAndView btwDate(@RequestParam Date startDate , Date endDate , int productId , ModelMap modelMap){
        LOGGER.info("Generation report between date on specific product ");
        LOGGER.debug("Date is "+startDate+" to "+endDate+" on product id "+productId);
        modelMap.addAttribute("startDate" , startDate);
        modelMap.addAttribute("endDate" , endDate);
        modelMap.addAttribute("productId" , productId);
        Invoice invoice = reportServices.productBtwDate(startDate,endDate,productId);
        LOGGER.debug("Generated report is "+invoice);
        return new ModelAndView("viewReportOnProduct" , "invoice" , invoice );
    }
}
