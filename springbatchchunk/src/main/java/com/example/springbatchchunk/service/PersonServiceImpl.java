package com.example.springbatchchunk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbatchchunk.entities.Person;
import com.example.springbatchchunk.persistence.PersonDAO;

@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonDAO ipersonDao;

    @Override
    @Transactional
    public void saveAll(List<Person> personList) {
        ipersonDao.saveAll(personList);
    }
}
