package practice.app.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import practice.app.DAO.BooksDAO;
import practice.app.models.Book;

@Component
public class BookValidator implements Validator {
    private final BooksDAO booksDAO;

    public BookValidator(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if(book.getBook_name().isEmpty()){
            errors.rejectValue("book_name", "book.name.empty", "Название не должно быть пустым");
        } else if (book.getAuthor_name().isEmpty()){
            errors.rejectValue("author_name", "book.author.name.empty", "Имя автора не должно быть пустым");
        }
    }
}
