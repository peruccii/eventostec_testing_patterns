package com.eventostec.eventostec.presenters;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record EventDetailsPresenter(
        UUID id,
        String title,
        String description,
        Date date,
        String city,
        String state,
        String imgUrl,
        String eventUrl,
        List<CouponDTO> coupons) {

    public record CouponDTO(
            String code,
            Integer discount,
            Date valid) {
    }
}