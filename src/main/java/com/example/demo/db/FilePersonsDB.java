package com.example.demo.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component("File")
public class FilePersonsDB implements PersonsDB {
  private final ObjectMapper objectMapper = new ObjectMapper();

  public FilePersonsDB() {
    if (!Files.exists(Path.of("data"))){
      try {
        Files.createDirectory(Path.of("data"));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public List<Person> getPersons() {
    ArrayList<Person> personArrayList = new ArrayList<>();

    try {
      File folder = new File("data");
      File[] listOfFiles = folder.listFiles();
      if (listOfFiles != null) {
        for (File f : listOfFiles) {
          Person readtPerson = objectMapper.readValue(f, Person.class);
          personArrayList.add(readtPerson);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return personArrayList;
  }

  @Override
  public void addPerson(Person p) {
    List<Person> persons = getPersons();
    persons.add(p);
    try {
      Files.writeString(
              Path.of("data/" + UUID.randomUUID().toString()),
              objectMapper.writeValueAsString(p)
      );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
