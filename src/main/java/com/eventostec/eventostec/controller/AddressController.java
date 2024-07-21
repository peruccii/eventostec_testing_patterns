package com.eventostec.eventostec.controller;

import com.eventostec.eventostec.domain.event.builder.Event;
import com.eventostec.eventostec.dtos.EventDTO;
import com.eventostec.eventostec.presenters.AddressPresenter;
import com.eventostec.eventostec.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressPresenter> createAddress(@RequestBody EventDTO payload, Event event) {
        return this.addressService.create(payload,event);
    }
}
