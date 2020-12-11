package by.shipul.spring.dao;

import by.shipul.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(1,"Andrey"));
        people.add(new Person(2,"Tom"));
        people.add(new Person(3,"Sasha"));
        people.add(new Person(4,"Valera"));
        people.add(new Person(5,"Richard"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
