package com.tui.proof.ws.service;

/**
 * Service for messages (from messages.properties)
 */
public interface MessageService {

    /**
     * Find message in message.properties, resolve params and return
     * @param msg message key
     * @param params params for message if needed
     * @return resolved message string
     */
    String getMessage(String msg, Object... params);
}
