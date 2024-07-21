package com.eventostec.eventostec.strategy.pack;
import com.eventostec.eventostec.domain.event.builder.Event;
import com.eventostec.eventostec.exceptions.EventoDataAlreadyExistsException;
import com.eventostec.eventostec.exceptions.EventoDataNotFoundException;
import com.eventostec.eventostec.repositories.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


public class EventStrategyMethods {

    @Service
    public static class EventExistsValidator implements EventStrategy {

        @Autowired
        private IEventRepository eventRepository;

        public EventExistsValidator(IEventRepository eventRepository) {
            this.eventRepository = eventRepository;
        }

        @Override
        public Event validate(UUID eventId) {
            Event responseEvent = this.eventRepository.findById(eventId)
                    .orElseThrow(() -> new EventoDataNotFoundException("EVENT NOT FOUND"));

            return responseEvent;
        }

        @Override
        public void validate(String eventUrl) {
            Optional<Event> responseEvent = this.eventRepository.findEventByEventUrl(eventUrl);
            responseEvent.ifPresent(event -> {
                throw new EventoDataAlreadyExistsException("EVENT ALREADY EXISTS");
            });
        }
    }
}
