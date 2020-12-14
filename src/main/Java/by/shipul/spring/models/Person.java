package by.shipul.spring.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 30,message = "Name should be between 2 and 30 char")
    private String name;
    @Size(min = 2,max = 30,message = "Name should be between 2 and 30 char")
    private String surname;
    @NotEmpty(message =" email should not be empty")
    @Email(message = "email is not valid")
    private String email;

    public Person(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
