package ua.cargo.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListeners implements HttpSessionListener {

    private static final Logger logger = LogManager.getLogger(SessionListeners.class);

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        logger.info("Session Created:: ID=" + sessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        logger.info("Session Destroyed:: ID=" + sessionEvent.getSession().getId());
    }
}
