package me.ikenga.persistence.repository;


import me.ikenga.api.feedback.metrics.MetricValue;
import me.ikenga.api.feedback.token.Token;
import me.ikenga.persistence.entity.MetricEntity;
import me.ikenga.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetricRepository extends
        CrudRepository<MetricEntity, Long> {

    @Query("select distinct m.metricName from MetricEntity m")
    List<String> findDistinctMetricNames();

    @Query("select new me.ikenga.api.feedback.metrics.MetricValue(m.metricName, m.user.username, sum(m.value)) "
            + "from MetricEntity m "
            + "where m.user = :user "
            + "group by m.user.username, m.metricName "
            + "order by m.metricName, sum(m.value) desc")
    List<MetricValue> findSumValuesByUserName(@Param("user") UserEntity user);

    @Query("select new me.ikenga.api.feedback.metrics.MetricValue(m.metricName, m.user.team, sum(m.value)) "
            + "from MetricEntity m "
            + "where m.metricName = :metric "
            + "group by m.user.team, m.metricName "
            + "order by m.metricName, sum(m.value) desc")
    List<MetricValue> findTeamHigscoresByMetric(@Param("metric") String metric);

    @Query("select new me.ikenga.api.feedback.metrics.MetricValue('overall points', m.user.username, sum(m.value)) "
            + "from MetricEntity m "
            + "group by m.user.username "
            + "order by sum(m.value) desc")
    List<MetricValue> findOverallPoints();

    @Query("select new me.ikenga.api.feedback.metrics.MetricValue('overall team points', m.user.team, sum(m.value)) "
            + "from MetricEntity m "
            + "group by m.user.team "
            + "order by sum(m.value) desc")
    List<MetricValue> findOverallTeamPoints();

    @Query("select new me.ikenga.api.feedback.metrics.MetricValue('overall team points', m.user.team, sum(m.value)) "
            + "from MetricEntity m "
            + "where m.user.team = :team "
            + "group by m.metricName, m.user.username "
            + "order by sum(m.value) desc")
    List<MetricValue> findSumValuesByTeam(@Param("team") String team);

    @Query("select new me.ikenga.api.feedback.metrics.MetricValue(m.metricName, m.user.username, sum(m.value)) "
            + "from MetricEntity m "
            + "where m.metricName = :metric "
            + "group by m.user.username, m.metricName "
            + "order by m.metricName, sum(m.value) desc")
    List<MetricValue> findSumValuesByMetric(@Param("metric") String metric);

    @Query("select new me.ikenga.api.feedback.metrics.MetricValue(m.metricName, m.user.username, sum(m.value)) "
            + "from MetricEntity m "
            + "where m.user.team = :team "
            + "group by m.user.username, m.metricName "
            + "order by m.metricName, sum(m.value) desc")
    List<MetricValue> findSumValuesOfTeam(@Param("team") String team);

    @Query("select new me.ikenga.api.feedback.token.Token('early bird', m.user.username, m.date, 'earliest Commit on a day') "
            + "from MetricEntity m where to_char(m.date, 'HH24:MI') = (select min(to_char(m.date, 'HH24:MI')) from m) ")
    List<Token> findEarliestCommit();

    @Query("select new me.ikenga.api.feedback.token.Token('night owl', m.user.username, m.date, 'latest Commit on a day') "
            + "from MetricEntity m where to_char(m.date, 'HH24:MI') = (select max(to_char(m.date, 'HH24:MI')) from m) ")
    List<Token> findLatestCommit();

    @Query("select new me.ikenga.api.feedback.token.Token('big shot', m.user.username, sum(m.value), 'Commit with most actions') "
            + "from MetricEntity m "
            + "group by m.user.username, m.svnRevision "
            + "order by sum(m.value) desc")
    List<Token> findBiggestCommit();

    @Query("select new me.ikenga.api.feedback.token.Token('jabber',m.user.username,count(distinct m.svnRevision) as coun, 'most Commits') " +
            "from MetricEntity as m " +
            "group by m.user.username " +
            "order by coun desc")
    List<Token> findMostCommits();

    @Query("select new me.ikenga.api.feedback.token.Token('safety first',m.user.username, count(distinct m.svnRevision) as coun, 'most Commits on one day') " +
            "from MetricEntity as m " +
            "group by m.user.username, to_char(m.date, 'DD.MM.YYYYS') " +
            "order by coun desc")
    List<Token> findMostCommitsOnOneDay();

    @Query("select new me.ikenga.api.feedback.token.Token('talkative', m.user.username, round(avg(length(m.message))) as avglen, 'highest average message-length')" +
            "from MetricEntity as m " +
            "group by m.user.username " +
            "order by avglen desc")
    List<Token> findLongestAvgMessageLen();

    List<MetricEntity> findByUser(UserEntity user);
}
