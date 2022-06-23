package com.inditex.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private class JsonResponse {
        String message;

        public JsonResponse() {
        }

        public JsonResponse(String message) {
            super();
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<JsonResponse> handleException(ResponseStatusException e) {
        return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<JsonResponse> handleException(Exception e) {
        return new ResponseEntity<JsonResponse>(new JsonResponse("BAD REQUEST"), HttpStatus.BAD_REQUEST);
    }

}
