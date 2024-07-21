package com.eventostec.eventostec.services;

import com.eventostec.eventostec.domain.address.builder.AddressBuilder;
import com.eventostec.eventostec.domain.event.builder.Event;
import com.eventostec.eventostec.dtos.EventDTO;
import com.eventostec.eventostec.presenters.AddressPresenter;
import com.eventostec.eventostec.repositories.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private IAddressRepository addressRepository;

    public ResponseEntity<AddressPresenter> create(EventDTO payload, Event event) {
        var addressBuilder = AddressBuilder.builder()
                .city(payload.city())
                .uf(payload.state())
                .event(event)
                .build();

        this.addressRepository.save(addressBuilder);

        return ResponseEntity.status(HttpStatus.CREATED).body(new AddressPresenter(
                addressBuilder.getCity(),
                addressBuilder.getUf(),
                addressBuilder.getEvent()));
    }
}
