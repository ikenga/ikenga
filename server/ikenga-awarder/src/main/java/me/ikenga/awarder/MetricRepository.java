package me.ikenga.awarder;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import me.ikenga.api.metrics.MetricValue;
import org.springframework.data.repository.query.Param;

public interface MetricRepository extends
        CrudRepository<MetricEntity, Long> {

    List<MetricEntity> findByUserName(String userName);

    @Query("select distinct userName from MetricEntity")
    List<String> findUserNames();

    @Query("select new me.ikenga.api.metrics.MetricValue(m.metricName, m.userName, sum(m.value)) "
            + "from MetricEntity m "
            + "where m.userName = :userName "
            + "group by m.userName, m.metricName "
            + "order by m.metricName, sum(m.value) desc")
    List<MetricValue> findHighestValuesByUserName(@Param("userName") String userName);

    @Query("select new me.ikenga.api.metrics.MetricValue(m.metricName, m.userName, sum(m.value)) "
            + "from MetricEntity m "
            + "group by m.userName, m.metricName "
            + "order by m.metricName, sum(m.value) desc")
    List<MetricValue> findHighestValues();

    @Query("select new me.ikenga.api.metrics.MetricValue(m.metricName, m.userName, sum(m.value)) "
            + "from MetricEntity m "
            + "where m.metricName = :metric "
            + "group by m.userName, m.metricName "
            + "order by sum(m.value) desc")
    List<MetricValue> findHighestValuesByMetric(@Param("metric") String metric);

}
