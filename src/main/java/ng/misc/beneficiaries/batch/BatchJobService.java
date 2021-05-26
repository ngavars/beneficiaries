package ng.misc.beneficiaries.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BatchJobService implements JobStarter {
    private static final Logger logger = LoggerFactory.getLogger(BatchJobService.class);

    public BatchJobService(@Autowired JobBuilderFactory jobBuilderFactory,
                           @Autowired JobLauncher jobLauncher,
                           @Autowired ImportBatchJobListener listener,
                           @Autowired Step companiesStep,
                           @Autowired Step beneficiariesStep) {
        this.jobLauncher = jobLauncher;
        this.jobBuilderFactory = jobBuilderFactory;
        this.listener = listener;
        this.companiesStep = companiesStep;
        this.beneficiariesStep = beneficiariesStep;
    }

    private JobBuilderFactory jobBuilderFactory;
    private JobLauncher jobLauncher;
    private ImportBatchJobListener listener;
    private Step companiesStep;
    private Step beneficiariesStep;


    @Override
    public void startJob(String jobName) {
        if ("companies".equalsIgnoreCase(jobName)) {
            Job companiesJob = jobBuilderFactory.get("importCompaniesJob")
                    .incrementer(new RunIdIncrementer())
                    .listener(listener)
                    .flow(companiesStep)
                    .end()
                    .build();
            runJob(companiesJob);
        }
        if ("beneficiaries".equalsIgnoreCase(jobName)) {
            Job beneficiariesJob = jobBuilderFactory.get("importBeneficiariesJob")
                    .incrementer(new RunIdIncrementer())
                    .listener(listener)
                    .flow(beneficiariesStep)
                    .end()
                    .build();
            runJob(beneficiariesJob);
        }
    }

    private void runJob (Job job) {
        try {
            jobLauncher.run(job, new JobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}