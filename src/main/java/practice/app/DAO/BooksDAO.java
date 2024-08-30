package practice.app.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import practice.app.models.Book;

import java.util.List;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("select * from books", new BeanPropertyRowMapper<>(Book.class));
    }

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO books(client_id, book_name, author_name, year_of_writing) VALUES (1, ?, ?, ?)",
                 book.getBook_name(), book.getAuthor_name(), book.getYear_of_writing());
    }

    public void updateBook(Book book) {
        jdbcTemplate.update("UPDATE books SET book_name=?, author_name=?, year_of_writing=? where id=?",
                book.getBook_name(), book.getAuthor_name(), book.getYear_of_writing(), book.getId());
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE id=?", id);
    }

    public Book getBook(int id) {
        return(jdbcTemplate.query("SELECT * FROM books WHERE id=?",new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))).stream().findFirst().orElse(null);
    }

    public void setBookClient(Book book) {
        jdbcTemplate.update("update books set client_id=? where id=?", book.getClient_id(), book.getId());
    }

    public void deleteBookClient(int id) {
        jdbcTemplate.update("update books set client_id = 1 WHERE id=?", id);
    }

    public List<Book> getBooksByClientId(int client_id) {
        return (jdbcTemplate.query("Select * from books where client_id=?", new Object[]{client_id}, new BeanPropertyRowMapper<>(Book.class)));
    }
}
