package me.ikenga;

import me.ikenga.awarder.DailyMetric;
import me.ikenga.awarder.DailyMetricsRepository;
import me.ikenga.web.IkengaWebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableAutoConfiguration
@ComponentScan
public class IkengaBoot {

    /**
     * spring configuration main method to build context
     *
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(
                IkengaBoot.class, args);

        DailyMetricsRepository repository = context
                .getBean(DailyMetricsRepository.class);
        Date now = new Date();
        repository.save(new DailyMetric(now, "kloe", "LINES_OF_CODE", 20L));
        repository.save(new DailyMetric(now, "steven", "LINES_OF_CODE", 30L));
        repository.save(new DailyMetric(now, "penta", "LINES_OF_CODE", 10L));
        repository.save(new DailyMetric(now, "kloe", "ADDED_LINES", 20L));
        repository.save(new DailyMetric(now, "steven", "ADDED_LINES", 25L));
        repository.save(new DailyMetric(now, "penta", "ADDED_LINES", 8L));
        repository.save(new DailyMetric(now, "kloe", "REMOVED_LINES", 0L));
        repository.save(new DailyMetric(now, "steven", "REMOVED_LINES", 5L));
        repository.save(new DailyMetric(now, "penta", "REMOVED_LINES", 2L));

    }
}
