package com.eventostec.eventostec.domain.event.builder;

import com.eventostec.eventostec.domain.address.builder.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity()
@Table(name = "event")
@Getter
@Setter
@AllArgsConstructor

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private String imgUrl;

    @Column(unique = true)
    private String eventUrl;

    private Boolean remote;

    private Date date;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Address address;

    protected Event() {}
}
