package com.pluralsight.conference.service;

import com.pluralsight.conference.entity.Credit;
import com.pluralsight.conference.entity.Currency;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CalculatorService {

    private static final float RUB = (float) 0.047;
    private static final float USD = (float) 2.53;
    private static final float EUR = (float) 3.361;

    private HashMap<String, Float> currencyMap;

    public CalculatorService() {
        currencyMap = new HashMap<>();
        currencyMap.put("RUB", RUB);
        currencyMap.put("USD", USD);
        currencyMap.put("EUR", EUR);
    }

    public HashMap<String, Float> getCurrencyMap() {
        return currencyMap;
    }

    public int getNewCost(Currency cost) {
        int oldPrice = cost.getValue();
        String name = cost.getName();
        float curs = currencyMap.get(name);
        return (int) (oldPrice/curs);
    }

    public Credit getCreditCost(Credit cost) {
        float sum = cost.getPrice();
        float percent = cost.getProcent();
        float percentForMonth = percent/12;
        int months = cost.getYear()*12;
        int formula = (int) (sum*(percentForMonth+(percentForMonth/(1+percentForMonth)*months-1)));
        cost.setNewPrice(formula);
        return cost;
    }

    public int getMonthCost(int price, int year) {
        return (int) price/year/12;
    }
 }
