package ng.misc.beneficiaries.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


@Configuration
@EnableScheduling
public class ExecutorConfiguration {
    @Bean("fixedThreadPool")
    public ScheduledExecutorService fixedThreadPool() {
        return Executors.newScheduledThreadPool(2);
    }
}
