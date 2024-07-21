package com.eventostec.eventostec.dtos;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;


public record EventDTO(

        @Valid

        @NotBlank(message = "TITLE IS REQUIRED")
        String title,

        @NotBlank(message = "DESCRIPTION IS REQUIRED")
        String description,

        @NotBlank(message = "DATE IS REQUIRED")
        Long date,

        @NotBlank(message = "CITY IS REQUIRED")
        String city,

        @NotBlank(message = "STATE IS REQUIRED")
        String state,

        @NotBlank(message = "THE URL OF THE EVENT IS REQUIRED")
        String eventUrl,

        @NotBlank(message = "REMOTE IS REQUIRED")
        Boolean remote,

        MultipartFile image

        ) {
}
