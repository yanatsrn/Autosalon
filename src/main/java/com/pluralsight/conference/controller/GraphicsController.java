package com.pluralsight.conference.controller;

import com.pluralsight.conference.entity.Car;
import com.pluralsight.conference.entity.Company;
import com.pluralsight.conference.service.CarService;
import com.pluralsight.conference.service.CompanyService;
import com.pluralsight.conference.service.GraphicsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GraphicsController {

    private final CompanyService companyService;
    private final CarService carService;
    private final GraphicsService graphicsService;

    public GraphicsController(CompanyService companyService, CarService carService, GraphicsService graphicsService) {
        this.companyService = companyService;
        this.carService = carService;
        this.graphicsService = graphicsService;
    }

    @GetMapping(value = "/showDiagram")
    public String viewDiagram(Model model) {
        List<Company> companies = companyService.getCompanies();
        Map<String, Integer> map = graphicsService.getCompaniesInMap(companies);
        model.addAttribute("statistics", map);
        return "diagram";
    }

    @GetMapping(value = "/showGraphic")
    public String viewGraphic(@RequestParam String name, ModelMap model) {
        List<Car> cars = carService.findByName(name);
        Map<Integer, Integer> map = graphicsService.getCarInMap(cars);
        model.addAttribute("statistics", map);
        return "graphic";
    }

}
