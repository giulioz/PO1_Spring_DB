package com.example.demo;

import com.example.demo.db.Person;
import com.example.demo.db.PersonsDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PeopleController {
  PersonsDB personsDB;

  @Autowired
  public PeopleController(@Qualifier("File") PersonsDB personsDB) {
    this.personsDB = personsDB;
  }

  @RequestMapping("/persons")
  @ResponseBody
  List<Person> getPersons() {
    return personsDB.getPersons();
  }

  @RequestMapping(value = "/person", method = RequestMethod.POST)
  @ResponseBody
  List<Person> addPerson(@RequestParam String name, @RequestParam String surname) {
    personsDB.addPerson(new Person(name, surname));
    return personsDB.getPersons();
  }
}
