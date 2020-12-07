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
  String addPerson(@RequestParam String name, @RequestParam String surname) {
    personsDB.addPerson(new Person(name, surname));
    return "/";
  }

  @RequestMapping(value = "/person", method = RequestMethod.DELETE)
  String deletePerson(@RequestParam String name, @RequestParam String surname) {
    personsDB.addPerson(new Person(name, surname));
    return "/";
  }

  @RequestMapping("/")
  @ResponseBody
  String getIndex() {
    StringBuilder html = new StringBuilder();
    html.append("<ul>");
    for (Person p : personsDB.getPersons()) {
      html.append("<li>" + p.getName() + " " + p.getSurname() + "</li>");
    }
    html.append("</ul>");
    html.append("<button onclick=\"location.reload()\">Aggiorna</button>");

    html.append("<form method=\"POST\" action=\"/person\">");
    html.append("  <div>");
    html.append("    Nome: <input type=\"text\" name=\"name\">");
    html.append("  </div>");
    html.append("  <div>");
    html.append("    Cognome: <input type=\"text\" name=\"surname\">");
    html.append("  </div>");
    html.append("  <button type=\"submit\">Invia</button>");
    html.append("</form>");

    return html.toString();
  }
}
