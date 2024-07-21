package com.eventostec.eventostec.strategy.pack;
import com.eventostec.eventostec.domain.event.builder.Event;

import java.util.UUID;

public interface EventStrategy {
    Event validate(UUID eventId);
    void validate(String eventUrl);
}
