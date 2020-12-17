package com.tui.proof.ws.event.dispatcher;

import lombok.AllArgsConstructor;
import java.util.concurrent.CompletableFuture;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventDispatcherImpl implements EventDispatcher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void dispatch(ApplicationEvent event) {
        CompletableFuture.runAsync(() -> applicationEventPublisher.publishEvent(event));
    }
}
