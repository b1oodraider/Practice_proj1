package practice.app.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import practice.app.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> allPeople() {
        return jdbcTemplate.query("select * from clients", new BeanPropertyRowMapper<>(Person.class));
    }

    public void createClient(Person person) {
        jdbcTemplate.update("INSERT INTO clients(fio, year) values(?, ?)", person.getFio(), person.getYear());
    }

    public void updateClient(Person person) {
        jdbcTemplate.update("update clients SET fio=?, year=? where id=?", person.getFio(), person.getYear(), person.getId());
    }

    public void deleteClient(int id) {
        jdbcTemplate.update("delete from clients where id=?", id);
    }

    public Optional<Person> getByFio(String fio) {
        return jdbcTemplate.query("select * from clients where fio=?",new Object[]{fio} ,new BeanPropertyRowMapper<>(Person.class)).stream().findFirst();
    }
}
