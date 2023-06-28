package com.example.springbatchchunk.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.springbatchchunk.entities.Person;
import com.example.springbatchchunk.steps.PersonItemProcessor;
import com.example.springbatchchunk.steps.PersonItemReader;
import com.example.springbatchchunk.steps.PersonItemWritter;



@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public PersonItemReader itemReader(){
        return new PersonItemReader();
    }

    @Bean
    public PersonItemWritter itemWritter(){
        return new PersonItemWritter();
    }

    @Bean
    public PersonItemProcessor itemProcessor(){
        return new PersonItemProcessor();
    }

    /* 
    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.setQueueCapacity(4);
        return taskExecutor();
    }
    */
    @Bean
    public Step readFile(){
        return stepBuilderFactory.get("readFile")
               .<Person,Person>chunk(10)
               .reader(itemReader())
               .processor(itemProcessor())
               .writer(itemWritter())
               //.taskExecutor(taskExecutor())
               .build();
    }
    
    @Bean
    public Job job(){
        return jobBuilderFactory.get("readFileWithChunk")
                .start(readFile())
                .build();
    }
}
