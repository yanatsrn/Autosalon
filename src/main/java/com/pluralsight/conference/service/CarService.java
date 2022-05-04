package com.pluralsight.conference.service;

import com.pluralsight.conference.entity.*;
import com.pluralsight.conference.repository.CarRepository;
import com.pluralsight.conference.validator.CarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car addCar(Car car) {
        Car newCar = carRepository.save(car);
        return newCar;
    }

    public void deleteCar(int id) {
        carRepository.deleteByCarId(id);
    }

    public Car updateCar(Car car, Company company, Type type) {
        car.setCompany(company);
        car.setTypeCar(type);
        car.getCompany().getCars().add(car);
        car.getTypeCar().getModels().add(car);
        carRepository.save(car);
        return car;
    }

    public Car findByCarId(int id) {
        return carRepository.findByCarId(id);
    }

    public List<Car> findByName(String name) {
        return carRepository.findByName(name);
    }

    public List<Car> findByYear(int year) {
        return carRepository.findByYear(year);
    }

    public List<Car> findByDistance(int distance) {
        return carRepository.findByDistance(distance);
    }

    public List<Car> findByFuelConsumption(String fuelConsumption) {
        return carRepository.findByFuelConsumption(fuelConsumption);
    }

    public List<Car> findByPrice(int price) {
        return carRepository.findByPrice(price);
    }

    public String correctCar(Car car) {
        String carError = " ";
        if (!CarValidator.isValidName(car.getName())) {
            carError += "Некорректное название\n";
        }
        if (!CarValidator.isValidFuelConsumption(car.getFuelConsumption())) {
            carError += "Некорректный расход топлива\n";
        }
        if (!CarValidator.isValidYear(car.getYear())) {
            carError += "Некорректный год\n";
        }
        if (!CarValidator.isValidDistanceAndPrice(car.getDistance(), car.getPrice())) {
            carError += "Некорректный пробег или цена\n";
        }
        return carError;
    }

    public List<Car> findCars(String filter, String input) {
        List<Car> cars = new ArrayList<>();
        switch (filter) {
            case "Название":
                cars = findByName(input);
                break;
            case "Год":
                if (CarValidator.isNumber(input)) {
                    if (CarValidator.isValidYear(Integer.parseInt(input)))
                        cars = findByYear(Integer.parseInt(input));
                }
                break;
            case "Пробег":
                if (CarValidator.isNumber(input)) {
                    if (CarValidator.isValidDistanceAndPrice(Integer.parseInt(input), Integer.parseInt(input)))
                        cars = findByDistance(Integer.parseInt(input));
                }
                break;
            case "Расход топлива":
                if (CarValidator.isValidFuelConsumption(input))
                    cars = findByFuelConsumption(input);
                break;
            case "Цена":
                if (CarValidator.isNumber(input)) {
                    if (CarValidator.isValidDistanceAndPrice(Integer.parseInt(input), Integer.parseInt(input)))
                        cars = findByPrice(Integer.parseInt(input));
                }
                break;
        }
        return cars;
    }

    public Person updatePersonAfterBuyCar(Person person, int price) {
        int discountSum = price / 100;
        discountSum += person.getUser().getDiscountSum();
        person.getUser().setDiscountSum(discountSum);
        return person;
    }

    public Car updateCarAfterBuy(Car car) {
        car.setBought(true);
        return car;
    }

    public List<Car> sortModelsByName() {
        return carRepository.findAll(Sort.by("name"));
    }

    public List<Car> sortModelsByYear() {
        return carRepository.findAll(Sort.by("year"));
    }

    public List<Car> sortModelsByDistance() {
        return carRepository.findAll(Sort.by("distance"));
    }

    public List<Car> sortModelsByFuelConsumption() {
        return carRepository.findAll(Sort.by("fuelConsumption"));
    }

    public List<Car> sortModelsByPrice() {
        return carRepository.findAll(Sort.by("price"));
    }

    public List<Car> getCarsForCompare(List<Integer> ids) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            cars.add(findByCarId(ids.get(i)));
        }
        return cars;
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }
}
