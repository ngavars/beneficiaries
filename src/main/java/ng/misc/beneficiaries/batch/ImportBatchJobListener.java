package ng.misc.beneficiaries.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class ImportBatchJobListener extends JobExecutionListenerSupport {
    private static final Logger logger = LoggerFactory.getLogger(ImportBatchJobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Starting import job {} ", jobExecution.getJobInstance().getJobName());
        super.beforeJob(jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("Completed import job {} ", jobExecution.getJobInstance().getJobName());
        super.afterJob(jobExecution);
    }
}
