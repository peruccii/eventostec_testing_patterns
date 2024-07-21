package com.eventostec.eventostec.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CouponDTO(
        @Valid

        @NotBlank(message = "CODE IS REQUIRED")
        String code,

        @Positive(message = "DISCOUNT MUST BE POSITIVE")
        @NotBlank(message = "DISCOUNT IS REQUIRED")
        Integer discount,
        @NotBlank(message = "VALID DATE IS REQUIRED")
        Long valid
) {
}
