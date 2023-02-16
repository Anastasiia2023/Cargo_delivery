package ua.cargo.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeListeners implements HttpSessionAttributeListener {

    private static final Logger logger = LogManager.getLogger(SessionAttributeListeners.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.info(String.format("added attribute name: %s, value:%s %n", event.getName(),
                event.getValue()));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.info(String.format("removed attribute name: %s, value:%s %n", event.getName(),
                event.getValue()));
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.info(String.format("replaced attribute name: %s, value:%s %n", event.getName(),
                event.getValue()));
    }

}
