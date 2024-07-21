package com.eventostec.eventostec.controller;

import com.eventostec.eventostec.dtos.CouponDTO;
import com.eventostec.eventostec.presenters.CouponPresenter;
import com.eventostec.eventostec.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<CouponPresenter> addCouponstoEvent(@PathVariable UUID eventId, @RequestBody CouponDTO payload) {
        return this.couponService.addCouponToEvent(eventId, payload);
    }
}
