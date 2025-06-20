package com.vedha.mybatchdemo.service;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Service;

@Service
public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before-Job listener");
        jobExecution.getExecutionContext().put("myMessage", "Message from JobListener");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After-Job listener");
    }
}
