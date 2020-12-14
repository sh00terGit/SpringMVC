package by.shipul.spring.dao;

import by.shipul.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private List<Person> people;
    private static int PEOPLE_COUNT;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Andrey","Shipul","sh00ter@tut.by"));
        people.add(new Person(++PEOPLE_COUNT,"Tom","Kruz","tomcruz@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT,"Sasha","Polyakov","po@yandex.ru"));
        people.add(new Person(++PEOPLE_COUNT,"Valera","Valera","valera@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT,"Richard","Rom","rom@tut.by"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(Person person, int id) {
        Person personUpdated = show(id);
        personUpdated.setEmail(person.getEmail());
        personUpdated.setSurname(person.getSurname());
        personUpdated.setName(person.getName());
    }

    public void delete(int id) {
        people.removeIf(p ->p.getId() == id);
    }
}
