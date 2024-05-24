package com.bong.batch.configuration;

import com.bong.batch.Tasklet.TestTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final TestTasklet testTasklet;

    @Bean
    public Job testJob(Step myStep){
        return new JobBuilder("testJob", this.jobRepository)
                .start(myStep)
                .build();
    }

    @Bean
    public Step myStep() {
        return new StepBuilder("myStep", this.jobRepository)
                .tasklet(testTasklet, platformTransactionManager)
                .build();
    }
}
