package by.shipul.spring.dao;

import by.shipul.spring.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT;
    private static final String URL = "jdbc:sqlite:D:/java/programming/java/home/SpringMVC/spring.db";

    private static Connection connection;

    {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person newPerson = new Person();
                newPerson.setId(resultSet.getInt("id"));
                newPerson.setName(resultSet.getString("name"));
                newPerson.setSurname(resultSet.getString("surname"));
                newPerson.setEmail(resultSet.getString("email"));
                people.add(newPerson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        Person newPerson = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from Person where id=?");

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            newPerson = new Person();
            newPerson.setId(resultSet.getInt("id"));
            newPerson.setName(resultSet.getString("name"));
            newPerson.setSurname(resultSet.getString("surname"));
            newPerson.setEmail(resultSet.getString("email"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newPerson;
    }

    public void save(Person person) {
     //   people.add(person);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "Insert into Person (id, name, surname, email) values (1,?,?,?)");
            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getSurname());
            preparedStatement.setString(3,person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Person person, int id) {
//        Person personUpdated = show(id);
//        personUpdated.setEmail(person.getEmail());
//        personUpdated.setSurname(person.getSurname());
//        personUpdated.setName(person.getName());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "Update Person set name= ? ,surname=?,email=? where id=?");
            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getSurname());
            preparedStatement.setString(3,person.getEmail());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
//        people.removeIf(p ->p.getId() == id);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
