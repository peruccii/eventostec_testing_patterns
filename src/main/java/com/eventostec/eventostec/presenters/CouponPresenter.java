package com.eventostec.eventostec.presenters;

import java.util.Date;

public record CouponPresenter(String code, Integer discount, Date valid) {
}
