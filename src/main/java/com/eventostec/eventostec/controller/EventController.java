package com.eventostec.eventostec.controller;

import com.eventostec.eventostec.dtos.EventDTO;
import com.eventostec.eventostec.presenters.EventPresenter;
import com.eventostec.eventostec.services.EventService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@Validated
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<EventPresenter> create(
            @RequestParam(value = "title") @NotBlank(message = "TITLE IS REQUIRED") String title,
            @RequestParam(value = "description", required = false) @NotBlank(message = "DESCRIPTION IS REQUIRED") String description,
            @RequestParam(value = "date") Long date,
            @RequestParam(value = "city") @NotBlank(message = "CITY IS REQUIRED") String city,
            @RequestParam(value = "state") @NotBlank(message = "STATE IS REQUIRED") String state,
            @RequestParam(value = "eventUrl") @NotBlank(message = "EVENTURL IS REQUIRED") String eventUrl,
            @RequestParam(value = "remote") Boolean remote,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        EventDTO payload = new EventDTO(title, description, date, city, state, eventUrl, remote, image);
        return this.eventService.createEvent(payload);
    }

    @GetMapping
    public ResponseEntity<List<EventPresenter>> getEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return this.eventService.getAllUpcomingEvents(page, size);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventPresenter>> getFilterEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return this.eventService.filterEvents(page, size, city, uf, startDate, endDate);
    }
}
