package com.eventostec.eventostec.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EventoDataAlreadyExistsException extends EventostecException {

    private String detail;

    public EventoDataAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("EVENTO DATA ALREADY EXISTS");
        pb.setDetail(detail);

        return pb;
    }
}
