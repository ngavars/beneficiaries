package ng.misc.beneficiaries.downloader;

import ng.misc.beneficiaries.batch.BatchJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DownloadService implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);

    public DownloadService(
            @Autowired BatchJobService batchJobService,
            @Autowired @Qualifier("fixedThreadPool") ScheduledExecutorService executorService) {
        this.executorService = executorService;
        this.batchJobService = batchJobService;
    }

    private ScheduledExecutorService executorService;
    private BatchJobService batchJobService;

    @Value("${register.csv.url}")
    private String registerUrl;

    @Value("${register.csv.input.file.name}")
    private String registerFile;

    @Value("${beneficiaries.csv.url}")
    private String beneficiariesUrl;

    @Value("${beneficiaries.csv.input.file.name}")
    private String beneficiariesFile;

    public void scheduleDownloads() {
        logger.info("Scheduling file downloads");
        executorService.schedule(
                new FileDownloader("companies" , registerUrl, registerFile, batchJobService),
                5,
                TimeUnit.SECONDS);

        executorService.schedule(
                new FileDownloader("beneficiaries",  beneficiariesUrl, beneficiariesFile, batchJobService),
                5,
                TimeUnit.SECONDS);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // context has loaded, let's schedule downloads
        scheduleDownloads();
    }
}
