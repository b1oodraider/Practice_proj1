package practice.app.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import practice.app.DAO.BooksDAO;
import practice.app.DAO.PersonDAO;
import practice.app.models.Person;
import practice.app.util.ClientValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BooksDAO booksDAO;
    private final ClientValidator clientValidator;

    public PeopleController(PersonDAO personDAO, BooksDAO booksDAO, ClientValidator clientValidator) {
        this.personDAO = personDAO;
        this.booksDAO = booksDAO;
        this.clientValidator = clientValidator;
    }

    @GetMapping()
    public String allPeople(Model model) {
        model.addAttribute("people",personDAO.allPeople());
        return "people/allPeople";
    }

    @GetMapping("createClient")
    public String newPeople(@ModelAttribute("person") Person person) {
        return "people/createClient";
    }

    @PostMapping()
    public String createClient(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        clientValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/createClient";
        }
        personDAO.createClient(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showClient(@PathVariable("id") int id, Model model) {
        model.addAttribute("books", booksDAO.getBooksByClientId(id));
        model.addAttribute("person",personDAO.allPeople().get(id-1));
        return "people/getOneClient";
    }

    @GetMapping("/{id}/editClient")
    public String editClient(@PathVariable("id") int id, Model model) {
        model.addAttribute("person",personDAO.allPeople().get(id-1));
        return "people/editClient";
    }

    @PatchMapping("/{id}")
    public String updateClient(@ModelAttribute("person") @Valid Person person, @PathVariable("id") int id, BindingResult bindingResult) {

        clientValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/editClient";
        }
        personDAO.updateClient(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable("id") int id) {
        personDAO.deleteClient(id);
        return "redirect:/people";
    }
}
