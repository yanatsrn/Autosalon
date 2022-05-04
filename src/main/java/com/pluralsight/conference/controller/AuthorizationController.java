package com.pluralsight.conference.controller;

import com.pluralsight.conference.entity.Person;
import com.pluralsight.conference.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuthorizationController {

    private final PersonService personService;

    public AuthorizationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/authorization")
    public String addPersonView(Model model) {
        model.addAttribute("person", new Person());
        return "authorization";
    }

    @GetMapping(value = "/exit")
    public RedirectView goOut(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        RedirectView redirectView = new RedirectView("/index.jsp", true);
        HttpSession session = request.getSession();
        session.invalidate();
        return redirectView;
    }

    @PostMapping("/authorization")
    public RedirectView addPerson(@ModelAttribute("person") Person person, RedirectAttributes redirectAttributes,
                                            HttpServletRequest request) {
        RedirectView redirectView = new RedirectView("/index.jsp", true);
        Person personByLogin = personService.signInSystem(person);
        if (personByLogin != null) {
            if (personByLogin.getUser().isActive()) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", personByLogin);
                redirectAttributes.addFlashAttribute("entered", true);
            }
            else {
                redirectView.setUrl("/authorization");
                redirectAttributes.addFlashAttribute("blocked", true);
            }
        }
        else {
            redirectView.setUrl("/authorization");
            redirectAttributes.addFlashAttribute("entered", false);
        }

        return redirectView;
    }
}
