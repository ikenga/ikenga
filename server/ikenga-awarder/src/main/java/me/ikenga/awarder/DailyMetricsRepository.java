package me.ikenga.awarder;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DailyMetricsRepository extends
        CrudRepository<DailyMetric, Long> {

    List<DailyMetric> findByUserName(String userName);

    @Query("from DailyMetric dm order by dm.metricName, dm.value desc")
    List<DailyMetric> findHighestValues();

}
