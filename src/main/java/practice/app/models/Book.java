package practice.app.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.Nullable;

public class Book {
    private int id;

    @Nullable
    private int client_id;

    @NotEmpty(message = "Название не должно быть пустым")
    private String book_name;

    @NotEmpty(message = "Имя автора не должно быть пустым")
    private String author_name;

    @Min(value = 0, message = "Дата написания не должна быть отрицательной")
    private int year_of_writing;

    public Book() {}

    public Book(int id, int clientId, String book_name, String author_name, int year_of_writing) {
        this.id = id;
        this.client_id = clientId;
        this.book_name = book_name;
        this.author_name = author_name;
        this.year_of_writing = year_of_writing;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getYear_of_writing() {
        return year_of_writing;
    }

    public void setYear_of_writing(int year_of_writing) {
        this.year_of_writing = year_of_writing;
    }
}
