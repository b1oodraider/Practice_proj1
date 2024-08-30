package practice.app.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 6, max = 80, message = "FIO must be 6 to 80 characters")
    private String fio;

    @Min(6)
    private int year;

    public Person() {}

    public Person(String fio, int year) {
        this.fio = fio;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public String getFio() {
        return fio;
    }

    public int getId() {
        return id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setId(int id) {
        this.id = id;
    }
}
