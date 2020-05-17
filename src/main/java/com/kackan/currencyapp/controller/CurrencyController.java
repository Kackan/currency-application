package com.kackan.currencyapp.controller;

import com.kackan.currencyapp.model.DetailsForm;
import com.kackan.currencyapp.service.CantorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CurrencyController {

    private final CantorService cantorService;

    @Autowired
    public CurrencyController(CantorService cantorService) {
        this.cantorService = cantorService;
    }

    @GetMapping("/currency")
    public String getCurrency(@RequestParam String base, Model model)
    {
        Map<String, Double> currencies = cantorService.getCurrency(base);
        model.addAttribute("currencies",currencies);
        model.addAttribute("base",base);
        return "cantor";
    }

    @GetMapping("/form")
    public String detailsForm(Model model, @RequestParam String symbol, @RequestParam String base)
    {
        model.addAttribute("detailsForm",new DetailsForm());
        return "details-form";
    }

    @PostMapping("/details")
    public String testMethod(Model model, @ModelAttribute DetailsForm form)
    {
        final Map<String, String> currencyMap = cantorService.worthOfCurrencyBasedOnDate(form);
        model.addAttribute("currency",currencyMap);
        return "several-results-of-currency";
    }

    @GetMapping("/customForm")
    public String customForm(Model model)
    {
        model.addAttribute("form", new DetailsForm());
        return "custom-form";
    }

}
