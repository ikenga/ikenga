package me.ikenga.awarder;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import me.ikenga.api.metrics.MetricValue;

public interface MetricRepository extends
        CrudRepository<MetricEntity, Long> {

    List<MetricEntity> findByUserName(String userName);

    @Query("select new me.ikenga.api.metrics.MetricValue(m.metricName, m.userName, sum(m.value)) "
            + "from MetricEntity m "
            + "group by m.userName, m.metricName "
            + "order by m.metricName, sum(m.value) desc")
    List<MetricValue> findHighestValues();

}
