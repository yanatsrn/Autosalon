package com.pluralsight.conference.repository;

import com.pluralsight.conference.entity.Car;
import com.pluralsight.conference.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
