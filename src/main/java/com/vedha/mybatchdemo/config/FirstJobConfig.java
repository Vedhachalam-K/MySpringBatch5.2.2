package com.vedha.mybatchdemo.config;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Component
public class FirstJobConfig {

    @Bean
    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.)
//                .addScript("/org/springframework/batch/core/schema-hsqldb.sql")
//                .generateUniqueName(true).build();
        DriverManagerDataSource dataSource =  new DriverManagerDataSource ();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=MySpringBatch;integratedSecurity=true;encrypt=false;trustServerCertificate=true;"); // encrypt=false
        return dataSource;
    }

    @Bean
    public JdbcTransactionManager transactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

    @Bean
    Job myFirstJob(JobRepository jobRepository, Step myFirstStep, PlatformTransactionManager platformTransactionManager){
        return new JobBuilder("FirstJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(myFirstStep)
                .build();
    }

    @Bean
    Step myFirstStep(JobRepository jobRepository, Tasklet firstTasklet, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("FirstStep", jobRepository)
                .tasklet(firstTasklet, platformTransactionManager)
                .build();
    }

    @Bean
    Tasklet firstTasklet(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Inside First Tasklet");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
