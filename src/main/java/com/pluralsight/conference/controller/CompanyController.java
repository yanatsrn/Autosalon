package com.pluralsight.conference.controller;

import com.pluralsight.conference.entity.Car;
import com.pluralsight.conference.entity.Company;
import com.pluralsight.conference.entity.Person;
import com.pluralsight.conference.service.CarService;
import com.pluralsight.conference.service.CompanyService;
import com.pluralsight.conference.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class CompanyController {


    private final CompanyService companyService;
    private final CarService carService;

    public CompanyController(CompanyService companyService, CarService carService) {
        this.companyService = companyService;
        this.carService = carService;
    }

    @GetMapping(value = "/showCompanies")
    public String showCompanies(Model model) {
        List<Company> companies = companyService.getCompanies();
        model.addAttribute("companies", companies);
        return "showCompanies";
    }

    @RequestMapping(value = "/deleteCompany", method = RequestMethod.GET)
    public String deleteCompany(@RequestParam int companyId) {
        companyService.deleteByCompanyId(companyId);
        return "redirect:/showCompanies";
    }

    @GetMapping(value = "/showCars")
    public String showCars(@RequestParam int id, Model model) {
        Company company = companyService.findByCompanyId(id);
        companyService.showPresentCars(company);
        if (company.getCars().isEmpty()) {
            model.addAttribute("count", 0);
        }
        else {
            model.addAttribute("count", company.getCars().size());
            model.addAttribute("company", company);
        }

        return "showCars";
    }

    @Transactional
    @GetMapping(value = "/addCompany")
    public String addCompanyView(ModelMap model) {
        model.put("company", new Company());
        return "addCompany";
    }

    @Transactional
    @PostMapping("/addCompany")
    public RedirectView addCompany(@ModelAttribute("company") Company company, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/addCompany", true);
        String error = companyService.addCompany(company);
        if (error.equals(" ")) {
            redirectAttributes.addFlashAttribute("addedCompany", true);
        }
        else {
            redirectAttributes.addFlashAttribute("addedCompany", false);
            redirectAttributes.addFlashAttribute("error", error);
        }
        return redirectView;
    }

    @Transactional
    @GetMapping(value = "/updateCompany")
    public String updateCompanyView(@RequestParam int companyId, ModelMap model) {
        Company company = companyService.findByCompanyId(companyId);
        model.put("company", company);
        return "updateCompany";
    }

    @Transactional
    @PostMapping("/updateCompany")
    public RedirectView updateCompany(@ModelAttribute("company") Company company, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/updateCompany?companyId=" + company.getCompanyId(), true);
        String error = companyService.updateCompany(company);
        if (error.equals(" ")) {
            redirectAttributes.addFlashAttribute("updatedCompany", true);
        }
        else {
            redirectAttributes.addFlashAttribute("updatedCompany", false);
            redirectAttributes.addFlashAttribute("error", error);
        }
        return redirectView;
    }
}
