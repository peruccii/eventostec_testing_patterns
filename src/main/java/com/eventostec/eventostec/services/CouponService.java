package com.eventostec.eventostec.services;

import com.eventostec.eventostec.domain.coupon.builder.CouponBuilder;
import com.eventostec.eventostec.dtos.CouponDTO;
import com.eventostec.eventostec.presenters.CouponPresenter;
import com.eventostec.eventostec.repositories.ICouponRepository;
import com.eventostec.eventostec.strategy.pack.EventStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    private  ICouponRepository couponRepository;

    @Autowired
    private EventStrategy eventStrategy;

    public ResponseEntity<CouponPresenter> addCouponToEvent(UUID eventId, CouponDTO payload)  {
        var responseEvent = this.eventStrategy.validate(eventId);

        var couponBuilder = CouponBuilder.builder()
                .code(payload.code())
                .discount(payload.discount())
                .valid(payload.valid())
                .event(responseEvent)
                .build();

        this.couponRepository.save(couponBuilder);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CouponPresenter(
                couponBuilder.getCode(),
                couponBuilder.getDiscount(),
                couponBuilder.getValid()
        ));
    }
}
