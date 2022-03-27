package org.dennis.dodede_example.business.shared.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventsPublisher {

    private final ApplicationEventPublisher publisher;

    public EventsPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishEvent(final ApplicationEvent event) {
        publisher.publishEvent(event);
    }
}
