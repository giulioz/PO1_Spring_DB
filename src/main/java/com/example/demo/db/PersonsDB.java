package com.example.demo.db;

import java.util.List;

public interface PersonsDB {
  List<Person> getPersons();
  void addPerson(Person p);
}
