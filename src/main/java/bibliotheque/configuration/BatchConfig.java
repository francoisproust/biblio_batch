package bibliotheque.configuration;

import bibliotheque.steps.EnvoiMailTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    public final JobBuilderFactory jobs;

    public final StepBuilderFactory steps;

    public final EnvoiMailTasklet task;

    @Autowired
    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, EnvoiMailTasklet envoiMailTasklet) {
        this.jobs = jobBuilderFactory;
        this.steps = stepBuilderFactory;
        this.task = envoiMailTasklet;
    }

    @Bean
    public Job sendReminderJob() {
        return jobs.get("sendReminderJob")
                .incrementer(new RunIdIncrementer())
                .start(stepOne()).build();
    }


    @Bean
    public Step stepOne() {
        return steps.get("stepOne").tasklet(task).build();
    }


}