package com.example.springbatchchunk.steps;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;

import com.example.springbatchchunk.entities.Person;

public class PersonItemProcessor implements ItemProcessor<Person,Person>{

    @Override
    public Person process(Person person) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        person.setInsertionDate(formatter.format(date));
        return person;
    }
    
}
