package com.eventostec.eventostec.repositories;

import com.eventostec.eventostec.domain.coupon.builder.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICouponRepository extends JpaRepository<Coupon, UUID> {
}
