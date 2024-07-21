package com.eventostec.eventostec.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EventostecException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("EVENTOSTEC INTERNAL SERVER ERROR");

        return pb;
    }
}
