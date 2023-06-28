package com.example.springbatchchunk.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.springbatchchunk.entities.Person;

public interface PersonDAO extends CrudRepository<Person,Long> {
    
}
