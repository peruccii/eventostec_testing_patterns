package com.eventostec.eventostec.domain.event.builder;

import java.util.Date;

public class EventBuilder {

    private Event event;

    private EventBuilder() {
        event = new Event();
    } // Constructor

    public static EventBuilder builder() {
        return new EventBuilder();
    }

    public EventBuilder title(String title) {
        this.event.setTitle(title);
        return this;
    }

    public EventBuilder description(String description) {
        this.event.setDescription(description);
        return this;
    }

    public EventBuilder imgUrl(String imgUrl) {
        this.event.setImgUrl(imgUrl);
        return this;
    }

    public EventBuilder eventUrl(String eventUrl) {
        this.event.setEventUrl(eventUrl);
        return this;
    }

    public EventBuilder remote(Boolean remote) {
        this.event.setRemote(remote);
        return this;
    }

    public EventBuilder date(Date date) {
        this.event.setDate(date);
        return this;
    }

    public Event build() {
        return this.event;
    }
}
