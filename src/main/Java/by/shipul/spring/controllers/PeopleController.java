package by.shipul.spring.controllers;

import by.shipul.spring.dao.PersonDAO;
import by.shipul.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {


    private final PersonDAO dao;

    public PeopleController(PersonDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people",dao.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model) {
        model.addAttribute("person",dao.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") Person person) {
        dao.save(person);
        return "redirect: /people";
    }



}
