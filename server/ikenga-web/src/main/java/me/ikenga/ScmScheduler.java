package me.ikenga;

import me.ikenga.persistence.entity.MetricEntity;
import me.ikenga.persistence.entity.RevisionEntity;
import me.ikenga.persistence.entity.UserEntity;
import me.ikenga.persistence.repository.MetricRepository;
import me.ikenga.persistence.repository.RevisionRepository;
import me.ikenga.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.TreeSet;

@Component
@EnableScheduling
public class ScmScheduler {


    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private RevisionRepository latestProcessedRevisionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Scheduled(fixedDelay = 20000)
    @Transactional
    public void run() throws ScmException {

        SvnCollector collector = applicationContext.getBean(SvnCollector.class);
        collector.pull(new ScmHandler() {

            @Override
            public void handleLog(long revision, Date logDate, String message, String userName, String action, long actionCount) {

                metricRepository.save(new MetricEntity(revision, logDate, getUser(userName), action, actionCount, message));
            }
        });
    }

    private UserEntity getUser(String author) {
        assert author != null;

        UserEntity user = userRepository.findByUsername(author);
        if (null == user) {
            user = new UserEntity(author);
            user.setTeam(getTeam(author));
            userRepository.save(user);
        }
        return user;
    }

    /**
     * sorry sorry :D only for POC!!! TODO Refactoring urgently needed
     *
     * @param author
     * @return
     */
    private String getTeam(String author) {
        switch (author) {
            case "kuksin":
            case "kmueller":
            case "hombergs":
            case "schiller":
            case "terstiege":
                return "Ost";
            case "zuber":
            case "schoemer":
            case "kloe":
            case "jdeponte":
                return "West";
            default:
                return "unknown";
        }

    }


}
