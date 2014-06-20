package me.ikenga;

import java.util.Date;

public interface ScmHandler {

    void handleLog(long revision, Date logDate, String message, String userName, String action, long actionCount);

}
