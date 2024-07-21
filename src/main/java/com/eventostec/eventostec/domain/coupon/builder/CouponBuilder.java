package com.eventostec.eventostec.domain.coupon.builder;

import com.eventostec.eventostec.domain.event.builder.Event;

import java.util.Date;

public class CouponBuilder {

    public Coupon coupon;

    public CouponBuilder() {
        coupon = new Coupon();
    }

    public static CouponBuilder builder() {
        return new CouponBuilder();
    }

    public CouponBuilder code(String code) {
        this.coupon.setCode(code);
        return this;
    }

    public CouponBuilder discount(Integer discount) {
        this.coupon.setDiscount(discount);
        return this;
    }

    public CouponBuilder valid(Date valid) {
        this.coupon.setValid(valid);
        return this;
    }

    public CouponBuilder event(Event event) {
        this.coupon.setEvent(event);
        return this;
    }

    public Coupon build() {
        return this.coupon;
    }
}
