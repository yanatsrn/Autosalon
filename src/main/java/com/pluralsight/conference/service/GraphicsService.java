package com.pluralsight.conference.service;

import com.pluralsight.conference.entity.Car;
import com.pluralsight.conference.entity.Company;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GraphicsService {

    public Map<String, Integer> getCompaniesInMap(List<Company> companies) {
        Map<String, Integer> map = new HashMap<>();
        for (Company c : companies) {
            map.put(c.getCompanyName(), c.getCars().size());
        }
        return map;
    }

    public Map<Integer, Integer> getCarInMap(List<Car> cars) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Car c : cars) {
            map.put(c.getYear(), c.getPrice());
        }
        return map;
    }
}
