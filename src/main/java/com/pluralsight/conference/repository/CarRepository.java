package com.pluralsight.conference.repository;

import com.pluralsight.conference.entity.Car;
import com.pluralsight.conference.entity.Company;
import com.pluralsight.conference.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    void deleteByCarId(int id);
    List<Car> findByName(String name);
    List<Car> findByYear(int year);
    List<Car> findByDistance(int distance);
    List<Car> findByFuelConsumption(String fuelConsumption);
    List<Car> findByPrice(int price);
    Car findByCarId(int id);
}
