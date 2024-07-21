package com.eventostec.eventostec.services;
import com.eventostec.eventostec.domain.address.builder.Address;
import com.eventostec.eventostec.domain.coupon.builder.Coupon;
import com.eventostec.eventostec.domain.event.builder.Event;
import com.eventostec.eventostec.domain.event.builder.EventBuilder;
import com.eventostec.eventostec.dtos.EventDTO;
import com.eventostec.eventostec.presenters.EventDetailsPresenter;
import com.eventostec.eventostec.presenters.EventPresenter;
import com.eventostec.eventostec.repositories.IAddressRepository;
import com.eventostec.eventostec.repositories.IEventRepository;
import com.eventostec.eventostec.strategy.pack.EventStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private IEventRepository eventRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private EventStrategy eventStrategy;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CouponService couponService;

    private AmazonService amazonService;

    public EventService(AmazonService amazonService) {
        this.amazonService = amazonService;
    }

    public ResponseEntity<EventPresenter> createEvent(EventDTO data) {
        String imgUrl = null;

        if (Objects.nonNull(data.image())) {
            imgUrl = this.amazonService.uploadImg(data.image());
        }

        var newEvent = EventBuilder.builder()
                .title(data.title())
                .description(data.description())
                .date(new Date(data.date()))
                .imgUrl(imgUrl)
                .remote(data.remote())
                .eventUrl(data.eventUrl()).build();

        this.eventStrategy.validate(newEvent.getEventUrl());

        this.eventRepository.save(newEvent);

        if (!data.remote()) this.addressService.create(data, newEvent);

        return ResponseEntity.status(HttpStatus.CREATED).body(new EventPresenter(
                newEvent.getTitle(),
                newEvent.getId(),
                newEvent.getAddress().getCity()
        ));

    }

    public ResponseEntity<List<EventPresenter>> getAllUpcomingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = this.eventRepository.findAllByOrderByUpcomingEvent(new Date(), pageable);

        return ResponseEntity.ok(eventsPage.map(event -> new EventPresenter(
                event.getTitle(),
                event.getId(),
                event.getAddress() != null ?  event.getAddress().getCity() : ""
                )).stream().toList());
    }

    public ResponseEntity<List<EventPresenter>> getFilteredEvents(int page, int size, String city, String uf, Date startDate, Date endDate) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = this.eventRepository.findFilteredEvents(city, uf, startDate, endDate, pageable);

        return ResponseEntity.ok(eventsPage.map(event -> new EventPresenter(
                event.getTitle(),
                event.getId(),
                event.getAddress() != null ?  event.getAddress().getCity() : ""
        )).stream().toList());
    }

    public ResponseEntity<EventDetailsPresenter> getEventDetails(UUID eventId) {
        var event = this.eventStrategy.validate(eventId);

        List<Coupon> coupon = this.couponService.consultCoupons(eventId, new Date());

        Optional<Address> address = this.addressRepository.findByEventId(eventId);

        List<EventDetailsPresenter.CouponDTO> couponsPresenter = coupon.stream().map(
                couponpresenter -> new EventDetailsPresenter.CouponDTO(
                        couponpresenter.getCode(),
                        couponpresenter.getDiscount(),
                        couponpresenter.getValid()
                )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(new EventDetailsPresenter(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                address.isPresent() ? address.get().getCity() : "",
                address.isPresent() ? address.get().getUf() : "",
                event.getImgUrl(),
                event.getEventUrl(),
                couponsPresenter
        ));
    }
}
