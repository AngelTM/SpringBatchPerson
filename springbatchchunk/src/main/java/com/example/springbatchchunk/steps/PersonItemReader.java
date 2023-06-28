package com.example.springbatchchunk.steps;

import java.nio.charset.StandardCharsets;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import com.example.springbatchchunk.entities.Person;

public class PersonItemReader extends FlatFileItemReader<Person>{
    public PersonItemReader(){
        setName("readPersons");
        setResource(new ClassPathResource("persons.csv"));
        setLinesToSkip(1);
        setEncoding(StandardCharsets.UTF_8.name());
        setLineMapper(getLineMapper());
    }


    public LineMapper<Person> getLineMapper(){
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        String[] columns = new String[]{"name","lastName","age"};
        int[] indexFields = new int[]{0,1,2};
        lineTokenizer.setNames(columns);
        lineTokenizer.setIncludedFields(indexFields);
        lineTokenizer.setDelimiter(",");

        BeanWrapperFieldSetMapper<Person> fieldsetmapper = new BeanWrapperFieldSetMapper<>();
        fieldsetmapper.setTargetType(Person.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldsetmapper);

        return lineMapper;
    }
}
