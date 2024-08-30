package practice.app.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import practice.app.DAO.PersonDAO;
import practice.app.models.Person;

@Component
public class ClientValidator implements Validator {
    private final PersonDAO personDAO;

    public ClientValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }



    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personDAO.getByFio(person.getFio()).isPresent()){
            errors.rejectValue("fio", null, "Client already exists");
        }
    }
}
