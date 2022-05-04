package com.pluralsight.conference.controller;

import com.pluralsight.conference.entity.Credit;
import com.pluralsight.conference.entity.Currency;
import com.pluralsight.conference.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

@Controller
public class CalculatorController {

    private final CalculatorService currencyService;

    public CalculatorController(CalculatorService calculatorService) {
        this.currencyService = calculatorService;
    }


    @GetMapping(value = "/currencyCalculator")
    public String showCalculator(Model model) {

        HashMap<String, Float> map = currencyService.getCurrencyMap();
        model.addAttribute("map", map);
        model.addAttribute("info", new Currency());
        return "currencyCalculator";
    }

    @PostMapping(value = "/currencyCalculator")
    public RedirectView calculate(@ModelAttribute("info") Currency cost, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/currencyCalculator", true);
        int newPrice = currencyService.getNewCost(cost);
        redirectAttributes.addFlashAttribute("newPrice", newPrice);
        return redirectView;
    }

    @GetMapping(value = "/creditCalculator")
    public String showCreditCalculator(Model model) {
        model.addAttribute("credit", new Credit());
        return "creditCalculator";
    }

    @PostMapping(value = "/creditCalculator")
    public RedirectView calculateCredit(@ModelAttribute("credit") Credit credit, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/creditCalculator", true);
        currencyService.getCreditCost(credit);
        int newPrice = credit.getNewPrice();
        int monthPrice = currencyService.getMonthCost(newPrice, credit.getYear());
        redirectAttributes.addFlashAttribute("newPrice", newPrice);
        redirectAttributes.addFlashAttribute("monthPrice", monthPrice);
        return redirectView;
    }
}
