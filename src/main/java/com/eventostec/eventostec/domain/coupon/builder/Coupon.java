package com.eventostec.eventostec.domain.coupon.builder;

import com.eventostec.eventostec.domain.event.builder.Event;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "coupon")
@Getter
@Setter
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue
    private UUID id;

    private String code;

    private Integer discount;

    private Date valid;

    @ManyToOne // Um evento pode ter muitos cupons e um cupon pode pertecer a apenas um evento
    @JoinColumn(name = "event_id")
    private Event event;

    protected Coupon() {}
}
