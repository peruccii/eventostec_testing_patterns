package com.eventostec.eventostec.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EventoDataNotFoundException extends EventostecException {

    private String detail;

    public EventoDataNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("EVENT NOT FOUND");
        pb.setDetail(detail);

        return pb;
    }
}
