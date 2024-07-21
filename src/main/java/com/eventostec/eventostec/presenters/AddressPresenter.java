package com.eventostec.eventostec.presenters;

import com.eventostec.eventostec.domain.event.builder.Event;

public record AddressPresenter(
        String city,

        String uf,

        Event event
) {
}
