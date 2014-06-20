/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ikenga;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Stefan Kloe
 */
@Component
@Scope(value = "prototype")
public class SvnCollector {

    private static final Logger logger = LoggerFactory.getLogger(SvnCollector.class);

    private final static String PARAM_USER_NAME = "username";
    private final static String PARAM_PASSWORD = "password";
    private final static String PARAM_PROTOCOL = "protocol";
    private final static String PARAM_HOST = "host";
    private final static String PARAM_PORT = "port";
    private final static String PARAM_PATH = "path";
    private final static String LAST_REVISION = "lastRevision";

    private final static String SEPARATOR = "=\n";
    private final static String CONFIG = PARAM_USER_NAME + SEPARATOR + PARAM_PASSWORD + SEPARATOR + PARAM_PROTOCOL + SEPARATOR + PARAM_HOST + SEPARATOR + PARAM_PORT + SEPARATOR + PARAM_PATH + SEPARATOR;

    private Properties properties = new Properties();

    public SvnCollector() {
        loadProperties();
    }

    public void pull(final ScmHandler handler) throws ScmException {
        logger.debug("searching for new revisions");
        try {
            SVNURL svnurl = createSvnUrl();
            long headRevision = getHeadRevision(svnurl);

            long lastRevision = getLastRevision();
            if (lastRevision != headRevision) {
                ISVNLogEntryHandler svnLogHandler = new ISVNLogEntryHandler() {

                    @Override
                    public void handleLogEntry(SVNLogEntry logEntry) throws SVNException {
                        Date date = logEntry.getDate();
                        String author = logEntry.getAuthor();
                        long revision = logEntry.getRevision();

                        logger.debug(String.format("found revision %d from %s by %s", revision, date, author));

                        Map<String, Long> actions = new HashMap<>();
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
                            handler.handleLog(revision, date, logEntry.getMessage(), author, entry.getKey(), entry.getValue());
                        }
                    }

                    private void addAction(Map<String, Long> actions, String action) {
                        if (actions.containsKey(action)) {
                            actions.put(action, actions.get(action) + 1);
                        } else {
                            actions.put(action, 1L);
                        }
                    }
                };

                SVNClientManager.newInstance().getLogClient().doLog(svnurl, null, SVNRevision.HEAD, SVNRevision.create(lastRevision),
                        SVNRevision.HEAD, false, true, true, 0, null, svnLogHandler);
            }

            properties.setProperty(LAST_REVISION, Long.toString(headRevision));
            saveProperties();
        } catch (SVNException ex) {
            logger.error("error accessing svn", ex);

            throw new ScmException(ex);
        }
    }

    private long getHeadRevision(SVNURL svnurl) throws ScmException {
        try {
            DAVRepositoryFactory.setup();
            SVNRepository repository = SVNRepositoryFactory.create(svnurl);

            byte[] decodedBytes = Base64.getDecoder().decode(properties.getProperty("password"));

            ISVNAuthenticationManager authManager
                    = SVNWCUtil.createDefaultAuthenticationManager(properties.getProperty("username"), new String(decodedBytes));
            repository.setAuthenticationManager(authManager);

            return repository.info(".", SVNRevision.HEAD.getNumber()).getRevision();
        } catch (SVNException ex) {

            throw new ScmException("could not create SvnRepositoryFactory", ex);
        }

    }

    private long getLastRevision() {
        return Long.valueOf(properties.getProperty(LAST_REVISION));
    }

    private void loadProperties() {
        File config = new File(System.getProperty("user.home"), ".ikenga/SvnCollector.properties");
        if (!config.exists()) {
            logger.info("no config file found, creating template");
            try {
                FileUtils.write(config, CONFIG, CharEncoding.UTF_8);
            } catch (IOException ex) {
                logger.error("could not write config file", ex);
            }
        }
        try (FileInputStream fileInputStream = new FileInputStream(config)) {
            properties.load(fileInputStream);
        } catch (IOException ex) {
            logger.error("could not load config file", ex);
        }

        if (StringUtils.isBlank(properties.getProperty(PARAM_PROTOCOL)) || StringUtils.isBlank(properties.getProperty(PARAM_HOST)) || StringUtils.isBlank(properties.getProperty(PARAM_PORT)) || StringUtils.isBlank(properties.getProperty(PARAM_PATH))) {
            logger.error("config is empty");

            throw new IllegalStateException("not configured");
        }
    }

    private void saveProperties() {
        File config = new File(System.getProperty("user.home"), ".ikenga/SvnCollector.properties");

        try (FileOutputStream fileOutputStream = new FileOutputStream(config)) {
            properties.store(fileOutputStream, "");
        } catch (IOException ex) {
            logger.error("could not load config file", ex);
        }
    }

    private SVNURL createSvnUrl() throws NumberFormatException, ScmException {
        try {
            return SVNURL.create(properties.getProperty(PARAM_PROTOCOL), null, properties.getProperty(PARAM_HOST), Integer.valueOf(properties.getProperty(PARAM_PORT)),
                    properties.getProperty(PARAM_PATH), true);
        } catch (SVNException ex) {
            throw new ScmException("config is not filled correctly", ex);
        }
    }

}
