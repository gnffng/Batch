package com.bong.batch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
public class BatchController {
    private final JobLauncher jobLauncher;
    private final Job testJob;

    @GetMapping("/test")
    public void test(@RequestParam(name="param")String param) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameter jobParameter = new JobParameter(param, String.class);
        Map<String,JobParameter<?>> parameters = new HashMap<String,JobParameter<?>>();
        parameters.put(param, jobParameter);
        jobLauncher.run(testJob, new JobParameters(parameters));
    }
}
