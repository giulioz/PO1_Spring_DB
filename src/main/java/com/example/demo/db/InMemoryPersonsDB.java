package com.example.demo.db;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("InMemory")
public class InMemoryPersonsDB implements PersonsDB {
  ArrayList<Person> persons;

  public InMemoryPersonsDB() {
    this.persons = new ArrayList<>();

    this.addPerson(new Person("Giulio", "Zausa"));
    this.addPerson(new Person("Pietro", "Ferrara"));
    this.addPerson(new Person("Mario", "Rossi"));
  }

  @Override
  public List<Person> getPersons() {
    return persons;
  }

  @Override
  public void addPerson(Person p) {
    persons.add(p);
  }
}
