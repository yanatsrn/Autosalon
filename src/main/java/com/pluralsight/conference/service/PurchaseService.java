package com.pluralsight.conference.service;

import com.pluralsight.conference.entity.Purchase;
import com.pluralsight.conference.repository.CarRepository;
import com.pluralsight.conference.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;


    public void addPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}
