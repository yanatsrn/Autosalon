package com.pluralsight.conference.controller;

import com.pluralsight.conference.entity.Person;
import com.pluralsight.conference.report.PeopleReport;
import com.pluralsight.conference.service.PersonService;
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
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/showPeople")
    public String viewBooks(Model model) {
        List<Person> people = personService.list();
        model.addAttribute("people", people);
        return "showPeople";
    }

    @GetMapping(value = "/addPerson")
    public String addPersonView(Model model) {
        model.addAttribute("person", new Person());
        return "addPerson";
    }

    @Transactional
    @PostMapping("/addPerson")
    public RedirectView addPerson(@ModelAttribute("person") Person person, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/addPerson", true);
        String error = personService.correctPerson(person);
        if(error.equals(" ")) {
            person.getUser().setPerson(person);
            personService.addPerson(person);
            redirectAttributes.addFlashAttribute("savedPerson", person);
            redirectAttributes.addFlashAttribute("addPersonSuccess", true);
        }
        else {
            redirectAttributes.addFlashAttribute("addPersonSuccess", false);
            redirectAttributes.addFlashAttribute("personError", error);
        }

        return redirectView;
    }

    @GetMapping(value = "/updatePerson")
    public String updatePersonView(@RequestParam int personId, ModelMap model) {
        Person person = personService.getPersonByPersonId(personId).get();
        model.put("person", person);
        return "updatePerson";
    }

    @Transactional
    @PostMapping("/updatePerson")
    public RedirectView updatePerson(@ModelAttribute("person") Person person, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/updatePerson?personId=" + person.getPersonId(), true);
        String error = personService.correctPerson(person);
        if (error.equals(" ")) {
            Person savedPerson = person;
            person.getUser().setPerson(person);
            personService.updatePerson(person);
            redirectAttributes.addFlashAttribute("savedPerson", savedPerson);
            redirectAttributes.addFlashAttribute("updatePersonSuccess", true);
        }
        else {
            redirectAttributes.addFlashAttribute("updatePersonSuccess", false);
            redirectAttributes.addFlashAttribute("personError", error);
        }

        return redirectView;
    }

    @Transactional
    @RequestMapping(value = "/blockPerson", method = RequestMethod.GET)
    public String blockPerson(@RequestParam int id) {
        Person person = personService.findByPersonId(id);
        personService.blockPerson(person);
        return "redirect:/showPeople";
    }

    @Transactional
    @RequestMapping(value = "/unblockPerson", method = RequestMethod.GET)
    public String unblockPerson(@RequestParam int id) {
        Person person = personService.findByPersonId(id);
        personService.unblockPerson(person);
        return "redirect:/showPeople";
    }

    @Transactional
    @RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
    public String deletePerson(@RequestParam int id) {
        personService.deletePerson(id);
        return "redirect:/showPeople";
    }

    @GetMapping(value = "/getReport")
    public void getReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Person> people = personService.list();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Users");
            PeopleReport.writeHeaderLine(sheet);

            PeopleReport.writeDataLines(people, workbook, sheet);

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();

            outputStream.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/showProfile")
    public String updateProfileView(HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();
        Person personFromSession = (Person) session.getAttribute("currentUser");
        Person person = personService.getPersonByPersonId(personFromSession.getPersonId()).get();
        model.put("person", person);
        return "showProfile";
    }

    @Transactional
    @PostMapping("/showProfile")
    public RedirectView updateProfile(@ModelAttribute("person") Person person, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/showProfile?personId=" + person.getPersonId(), true);
        String error = personService.correctFields(person);
        if (error.equals(" ")) {
            person.getUser().setPerson(person);
            personService.updatePerson(person);
            redirectAttributes.addFlashAttribute("savedPerson", person);
            redirectAttributes.addFlashAttribute("updatePersonSuccess", true);

        }
        else {
            redirectAttributes.addFlashAttribute("updatePersonSuccess", false);
            redirectAttributes.addFlashAttribute("personError", error);
        }
        return redirectView;
    }
}
