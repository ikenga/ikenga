/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ikenga;

import me.ikenga.awarder.MetricEntity;
import me.ikenga.awarder.MetricRepository;
import me.ikenga.awarder.RevisionEntity;
import me.ikenga.awarder.RevisionRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNLogClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;

/**
 * @author Stefan Kloe
 */
@Component
@EnableScheduling
public class SvnCollector {

    private static final Logger logger = LoggerFactory.getLogger(SvnCollector.class);

    private String username = null;
    private String password = null;

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private RevisionRepository latestProcessedRevisionRepository;

    private final static String PARAM_USER_NAME = "username";
    private final static String PARAM_PASSWORD = "password";
    private final static String PARAM_PROTOCOL = "protocol";
    private final static String PARAM_HOST = "host";
    private final static String PARAM_PORT = "port";
    private final static String PARAM_PATH = "path";

    private final static String SEPARATOR = "=\n";
    private final static String CONFIG = PARAM_USER_NAME + SEPARATOR + PARAM_PASSWORD + SEPARATOR + PARAM_PROTOCOL + SEPARATOR + PARAM_HOST + SEPARATOR + PARAM_PORT + SEPARATOR + PARAM_PATH + SEPARATOR;

    @Scheduled(fixedDelay = 20000)
    public void run() {
        PropertyResourceBundle properties = getProperties();
        if (properties == null) {
            return;
        }

        SVNURL svnurl = createSvnUrl(properties);
        if (svnurl == null) {
            return;
        }


        if (username == null || password == null) {
            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("enter username: ");
                username = bufferRead.readLine();
                System.out.println("enter password: ");
                password = bufferRead.readLine();
            } catch (IOException e) {
                new RuntimeException("fehlerhafte Eingabe");
            }
        }

        SVNRepository repository = createRepository(username, password, svnurl);
        if (repository == null) {
            return;
        }

        logger.debug("searching for new revisions");
        SVNLogClient logClient = SVNClientManager.newInstance().getLogClient();
        try {

            RevisionEntity latestProcessedRevision = getLatestProcessedRevision(svnurl);

            SVNRevision fromRevision = SVNRevision.create(latestProcessedRevision.getRevision());
            SVNRevision headRevision = SVNRevision.create(repository.info(".", -1).getRevision());

            if (!fromRevision.equals(headRevision)) {
                logClient.doLog(svnurl, null, SVNRevision.HEAD, fromRevision,
                        SVNRevision.HEAD, false, true, true, 0, null, new LogEntryHandler());
                latestProcessedRevision.setRevision(headRevision.getNumber());
                latestProcessedRevisionRepository.save(latestProcessedRevision);
            }

        } catch (SVNException ex) {
            logger.error("error accessing svn", ex);
        }
    }

    public PropertyResourceBundle getProperties() {
        File config = new File(System.getProperty("user.home"), ".ikenga/SvnCollector.properties");
        if (!config.exists()) {
            logger.info("no config file found, creating template");
            try {
                FileUtils.write(config, CONFIG, CharEncoding.UTF_8);
            } catch (IOException ex) {
                logger.error("could not write config file", ex);
            }
            return null;
        }
        PropertyResourceBundle properties;
        try (FileInputStream fileInputStream = new FileInputStream(config)) {
            properties = new PropertyResourceBundle(fileInputStream);
        } catch (IOException ex) {
            logger.error("could not load config file", ex);
            return null;
        }
        if (StringUtils.isBlank(properties.getString(PARAM_PROTOCOL)) || StringUtils.isBlank(properties.getString(PARAM_HOST)) || StringUtils.isBlank(properties.getString(PARAM_PORT)) || StringUtils.isBlank(properties.getString(PARAM_PATH))) {
            logger.error("config is empty");
            return null;
        }
        return properties;
    }

    private SVNURL createSvnUrl(PropertyResourceBundle properties) throws NumberFormatException {
        try {
            return SVNURL.create(properties.getString(PARAM_PROTOCOL), null, properties.getString(PARAM_HOST), Integer.valueOf(properties.getString(PARAM_PORT)),
                    properties.getString(PARAM_PATH), true);
        } catch (SVNException ex) {
            logger.error("config is not filled correctly", ex);
        }
        return null;
    }

    private SVNRepository createRepository(String username, String password, SVNURL svnurl) {
        try {
            DAVRepositoryFactory.setup();
            SVNRepository repository = SVNRepositoryFactory.create(svnurl);

            ISVNAuthenticationManager authManager
                    = SVNWCUtil.createDefaultAuthenticationManager(username, password);
            repository.setAuthenticationManager(authManager);
            return repository;
        } catch (SVNException ex) {
            logger.error("could not create SvnRepositoryFactory", ex);
        }
        return null;
    }

    public RevisionEntity getLatestProcessedRevision(SVNURL svnurl) {
        RevisionEntity latestProcessedRevision = latestProcessedRevisionRepository.findByHostAndPath(svnurl.getHost(), svnurl.getPath());
        if (latestProcessedRevision == null) {
            latestProcessedRevision = new RevisionEntity();
            latestProcessedRevision.setRevision(5700l);
            latestProcessedRevision.setHost(svnurl.getHost());
            latestProcessedRevision.setPath(svnurl.getPath());
        }
        return latestProcessedRevision;
    }

    class LogEntryHandler implements ISVNLogEntryHandler {

        @Override
        public void handleLogEntry(SVNLogEntry logEntry)
                throws SVNException {
            String author = logEntry.getAuthor();
            Date date = logEntry.getDate();
            logger.debug(String.format("found revision %d from %s by %s", logEntry.getRevision(), date, author));
            Map<String, Long> actions = new HashMap<String, Long>();
            for (Map.Entry<String, SVNLogEntryPath> entry : logEntry.getChangedPaths().entrySet()) {
                switch (entry.getValue().getType()) {
                    case SVNLogEntryPath.TYPE_ADDED:
                        addAction(actions, "SvnAddCount");
                        break;
                    case SVNLogEntryPath.TYPE_DELETED:
                        addAction(actions, "SvnDeleteCount");
                        break;
                    case SVNLogEntryPath.TYPE_MODIFIED:
                        addAction(actions, "SvnModifyCount");
                        break;
                    case SVNLogEntryPath.TYPE_REPLACED:
                        addAction(actions, "SvnReplaceCount");
                        break;
                }
            }
            for (Map.Entry<String, Long> entry : actions.entrySet()) {
                MetricEntity metricData = new MetricEntity(logEntry.getRevision(), date,
                        author, entry.getKey(), entry.getValue(), logEntry.getMessage());
                metricRepository.save(metricData);
            }
        }

        private void addAction(Map<String, Long> actions, String action) {
            if (actions.containsKey(action)) {
                actions.put(action, actions.get(action) + 1);
            } else {
                actions.put(action, 1L);
            }
        }

    }

}
