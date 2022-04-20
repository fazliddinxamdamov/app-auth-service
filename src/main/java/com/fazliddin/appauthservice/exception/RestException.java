package com.fazliddin.appauthservice.exception;

import com.fazliddin.appauthservice.common.MessageService;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * This class not documented :(
 *
 * @author Muhammad Mo'minov
 * @since 17.12.2021
 */

@Data
public class RestException extends RuntimeException {

    private String message;

    private HttpStatus status;


    public RestException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public static RestException restThrow(String message, HttpStatus status) {
        return new RestException(message, status);
    }

    public static RestException notFound(String termin) {
        return new RestException(MessageService.notFound(termin), HttpStatus.NOT_FOUND);
    }


    public static RestException forbidden() {
        return new RestException(MessageService.getMessage("FORBIDDEN"), HttpStatus.FORBIDDEN);
    }

    public static RestException badRequest() {
        return new RestException(MessageService.getMessage("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
    }

    public static RestException serverError() {
        return new RestException(MessageService.getMessage("INTERNAL_SERVER_ERROR"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
