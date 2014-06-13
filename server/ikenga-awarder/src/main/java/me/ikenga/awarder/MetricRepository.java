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

    @Query("select new me.ikenga.api.metrics.MetricValue('overall points', m.userName, sum(m.value)) "
            + "from MetricEntity m "
            + "group by m.userName "
            + "order by sum(m.value) desc")
    List<MetricValue> findOverallPoints();

    @Query("select new me.ikenga.api.metrics.MetricValue(m.metricName, m.userName, sum(m.value)) "
            + "from MetricEntity m "
            + "where m.metricName = :metric "
            + "group by m.userName, m.metricName "
            + "order by m.metricName, sum(m.value) desc")
    List<MetricValue> findHighestValuesByMetric(@Param("metric") String metric);

    @Query("select new me.ikenga.api.token.Token('early bird', m.userName, m.date, 'earliest Commit on a day') "
            + "from MetricEntity m where to_char(m.date, 'HH24:MI') = (select min(to_char(m.date, 'HH24:MI')) from m) ")
    Token findEarliestCommit();

    @Query("select new me.ikenga.api.token.Token('night owl', m.userName, m.date, 'latest Commit on a day') "
            + "from MetricEntity m where to_char(m.date, 'HH24:MI') = (select max(to_char(m.date, 'HH24:MI')) from m) ")
    Token findLatestCommit();

    @Query("select new me.ikenga.api.token.Token('big shot', m.userName, sum(m.value), 'Commit with most actions') "
            + "from MetricEntity m "
            + "group by m.userName, m.svnRevision "
            + "order by sum(m.value) desc")
    List<Token> findBiggestCommit();

    @Query("select new me.ikenga.api.token.Token('jabber',m.userName,count(distinct m.svnRevision) as coun, 'most Commits') " +
            "from MetricEntity as m " +
            "group by m.userName " +
            "order by coun desc")
    List<Token> findMostCommits();

    @Query("select new me.ikenga.api.token.Token('talkative', m.userName, round(avg(length(m.message))) as avglen, 'highest average message-length')" +
            "from MetricEntity as m " +
            "group by m.userName " +
            "order by avglen desc")
    List<Token> findLongestAvgMessageLen();


}
