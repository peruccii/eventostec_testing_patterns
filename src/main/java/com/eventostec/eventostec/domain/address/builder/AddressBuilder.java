package com.eventostec.eventostec.domain.address.builder;

import com.eventostec.eventostec.domain.event.builder.Event;

public class AddressBuilder {

    private Address address;

    public AddressBuilder() {
        address = new Address();
    }

    public static AddressBuilder builder() {
        return new AddressBuilder();
    }

    public AddressBuilder city(String city) {
        address.setCity(city);
        return this;
    }

    public AddressBuilder uf(String uf) {
        address.setUf(uf);
        return this;
    }

    public AddressBuilder event(Event event) {
        address.setEvent(event);
        return this;
    }

    public Address build() {
        return this.address;
    }

}
