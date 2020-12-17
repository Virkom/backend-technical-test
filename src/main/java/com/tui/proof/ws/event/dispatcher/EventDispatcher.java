package com.tui.proof.ws.event.dispatcher;

import org.springframework.context.ApplicationEvent;

public interface EventDispatcher {
    void dispatch(ApplicationEvent event);
}
