package com.vedha.mybatchdemo.mytasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SimpleTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Inside second tasklet (in a seperate class)");

        Object messageFromListener = chunkContext.getStepContext().getJobExecutionContext().get("myMessage");
        Object messageFromAnotherStep = chunkContext.getStepContext().getJobExecutionContext().get("taskletMessage");

        System.out.println("Job execution context messageFromListener: " + messageFromListener);
        System.out.println("Job execution context messageFromAnotherStep: " + messageFromAnotherStep);

        return RepeatStatus.FINISHED;
    }
}
