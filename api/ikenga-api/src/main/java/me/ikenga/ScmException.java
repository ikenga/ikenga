package me.ikenga;

public class ScmException extends Exception {

    public ScmException() {
    }

    public ScmException(String message) {
        super(message);
    }

    public ScmException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScmException(Throwable cause) {
        super(cause);
    }

    public ScmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
