package me.ikenga.awarder;


import me.ikenga.api.metrics.MetricValue;
import me.ikenga.api.token.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
            + "where m.metricName = :metric "
            + "group by m.userName, m.metricName "
            + "order by sum(m.value) desc")
    List<MetricValue> findHighestValuesByMetric(@Param("metric") String metric);

    @Query("select new me.ikenga.api.token.Token('early bird', m.userName, m.date) "
            + "from MetricEntity m where to_char(m.date, 'HH24:MI') = (select min(to_char(m.date, 'HH24:MI')) from m) ")
    Token findEarliestCommit();

    @Query("select new me.ikenga.api.token.Token('night owl', m.userName, m.date) "
            + "from MetricEntity m where to_char(m.date, 'HH24:MI') = (select max(to_char(m.date, 'HH24:MI')) from m) ")
    Token findLatestCommit();

}
