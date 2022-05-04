package com.pluralsight.conference.controller;


import com.pluralsight.conference.entity.*;
import com.pluralsight.conference.report.CarReport;
import com.pluralsight.conference.service.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CarController {

    private final CarService carService;
    private final PurchaseService purchaseService;
    private final PersonService personService;
    private final Facade facade;

    public CarController(CarService carService, PurchaseService purchaseService, PersonService personService, Facade facade) {
        this.carService = carService;
        this.purchaseService = purchaseService;
        this.personService = personService;
        this.facade = facade;
    }

    @GetMapping(value="/compare")
    public String compare(@RequestParam List<Integer> ids, Model model) {
        if (!ids.isEmpty()) {
            List<Car> cars = carService.getCarsForCompare(ids);
            model.addAttribute("selectedCars", cars);
            model.addAttribute("count", ids.size());
        }
        else
            model.addAttribute("count", 0);
        return "compare";
    }

    @PostMapping(value="/compare")
    public RedirectView comparePage(@ModelAttribute("selectedCars") List<Car> cars) {
        final RedirectView redirectView = new RedirectView("/compare", true);
        return redirectView;
    }

    @GetMapping(value="/searchCar")
    public String search() {
        return "searchCar";
    }

    @PostMapping(value="/searchCar")
    public RedirectView searchPage(@RequestParam("selectedParam") String param, @RequestParam("input") String input, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/searchCar", true);
        List<Car> cars = carService.findCars(param, input);
        redirectAttributes.addFlashAttribute("count", cars.size());
        redirectAttributes.addFlashAttribute("cars", cars);
        return redirectView;
    }

    @GetMapping(value = "/addCar")
    public String addCarView(Model model) {
        List<Company> companies = facade.getCompanies();
        List<Type> types = facade.getTypes();
        model.addAttribute("car", new Car());
        model.addAttribute("companies", companies);
        model.addAttribute("types", types);
        return "addCar";
    }

    @Transactional
    @PostMapping("/addCar")
    public RedirectView addCar(@ModelAttribute("car") Car car, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/addCar", true);
        Company company = facade.findCompanyByName(car.getCompany().getCompanyName());
        Type type = facade.findTypeByTypeId(car.getTypeCar().getBody());
        car.setCompany(company);
        car.setTypeCar(type);
        String error = carService.correctCar(car);
        if (error.equals(" ")) {
            Car savedCar = carService.addCar(car);
            redirectAttributes.addFlashAttribute("savedCar", savedCar);
            redirectAttributes.addFlashAttribute("addCarSuccess", true);
        }
        else {
            redirectAttributes.addFlashAttribute("addCarSuccess", false);
            redirectAttributes.addFlashAttribute("carError", error);
        }
        return redirectView;
    }

    @Transactional
    @GetMapping(value = "/buyCar")
    public String buyCar(@RequestParam int carId, ModelMap model, HttpSession session) {
        Person person = (Person) session.getAttribute("currentUser");
        Car car = carService.findByCarId(carId);
        carService.updateCarAfterBuy(car);
        carService.updatePersonAfterBuyCar(person, car.getPrice());
        personService.updatePerson(person);
        Purchase purchase = new Purchase(car, person);
        purchaseService.addPurchase(purchase);
        model.put("purchase", purchase);
        return "buyCar";
    }

    @PostMapping("/buyCar")
    public RedirectView buyCarShow(@ModelAttribute("purchase") Purchase purchase, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/buyCar?carId=" + purchase.getCar().getCarId(), true);
        return redirectView;
    }

    @GetMapping(value = "/updateCar")
    public String updateCarView(@RequestParam int carId, ModelMap model) {
        List<Company> companies = facade.getCompanies();
        List<Type> types = facade.getTypes();
        model.addAttribute("companies", companies);
        model.addAttribute("types", types);
        Car car = carService.findByCarId(carId);
        model.addAttribute("car", car);
        return "updateCar";
    }

    @Transactional
    @PostMapping("/updateCar")
    public RedirectView updateCar(@ModelAttribute("car") Car car, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/updateCar?carId=" + car.getCarId(), true);
        Company company = facade.findCompanyByName(car.getCompany().getCompanyName());
        Type type = facade.findTypeByTypeId(car.getTypeCar().getBody());
        Car updatedCar = carService.updateCar(car, company, type);
        redirectAttributes.addFlashAttribute("updatedCar", updatedCar);
        redirectAttributes.addFlashAttribute("updateCarSuccess", true);
        return redirectView;
    }

    @Transactional
    @RequestMapping(value = "/deleteCar", method = RequestMethod.GET)
    public RedirectView deleteCar(@RequestParam int carId) {
        final RedirectView redirectView = new RedirectView("/showCompanies", true);
        carService.deleteCar(carId);
        return redirectView;
    }

    @GetMapping(value = "/showModels")
    public String viewModels(Model model) {
        List<Car> cars = carService.getCars();
        model.addAttribute("cars", cars);
        return "showModels";
    }

    @GetMapping(value = "/getCarReport")
    public void getReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=cars_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Car> cars = carService.getCars();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Cars");
            CarReport.writeHeaderLine(sheet);
            CarReport.writeDataLines(cars, workbook, sheet);
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/sortName")
    public String sortModelsByName(Model model) {
        List<Car> cars = carService.sortModelsByName();
        model.addAttribute("cars", cars);
        return "showModels";
    }

    @GetMapping(value = "/sortYear")
    public String sortModelsByYear(Model model) {
        List<Car> cars = carService.sortModelsByYear();
        model.addAttribute("cars", cars);
        return "showModels";
    }

    @GetMapping(value = "/sortDistance")
    public String sortModelsByDistance(Model model) {
        List<Car> cars = carService.sortModelsByDistance();
        model.addAttribute("cars", cars);
        return "showModels";
    }

    @GetMapping(value = "/sortFuelConsumption")
    public String sortModelsByFuelConsumption(Model model) {
        List<Car> cars = carService.sortModelsByFuelConsumption();
        model.addAttribute("cars", cars);
        return "showModels";
    }

    @GetMapping(value = "/sortPrice")
    public String sortModelsByPrice(Model model) {
        List<Car> cars = carService.sortModelsByPrice();
        model.addAttribute("cars", cars);
        return "showModels";
    }
}
