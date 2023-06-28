package com.example.springbatchchunk.steps;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springbatchchunk.entities.Person;
import com.example.springbatchchunk.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonItemWritter implements ItemWriter<Person>{

    @Autowired
    private PersonService personService;

    @Override
    public void write(List<? extends Person> items) throws Exception {
        items.forEach(person->person.toString());
        personService.saveAll((List<Person>) items);
    }

  
}
