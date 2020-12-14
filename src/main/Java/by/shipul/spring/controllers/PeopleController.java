package by.shipul.spring.controllers;

import by.shipul.spring.dao.PersonDAO;
import by.shipul.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "people/new";
        }
        dao.save(person);
        return "redirect: /people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person",dao.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "people/edit";
        }
        dao.update(person,id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") int id) {
        dao.delete(id);
        return "redirect: /people";
    }




}
