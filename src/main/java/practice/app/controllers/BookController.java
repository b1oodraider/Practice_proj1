package practice.app.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import practice.app.DAO.BooksDAO;
import practice.app.DAO.PersonDAO;
import practice.app.models.Book;
import practice.app.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BooksDAO booksDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;

    public BookController(BooksDAO booksDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String getAllBooks(Model model) {
        model.addAttribute("books", booksDAO.getAllBooks());
        return "books/allBooks";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksDAO.getBook(id));
        model.addAttribute("people", personDAO.allPeople());
        return "books/showBook";
    }

    @PatchMapping("/{id}/add")
    public String updateBookClient(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        booksDAO.setBookClient(book);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/deleteClient")
    public String deleteBookClient(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        booksDAO.deleteBookClient(id);
        return "redirect:/books/" + id;

    }

    @GetMapping("createBook")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/createBook";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/createBook";
        }
        booksDAO.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/updateBook")
    public String updateBookStatus(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksDAO.getBook(id));
        return "books/updateBook";
    }

    @PatchMapping("/{id}")
    public String updateBookStatus(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult, @PathVariable("id") int id) {
//        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/updateBook";
        }
        booksDAO.updateBook(book);

        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksDAO.deleteBook(id);
        return "redirect:/books";
    }

}
