package ru.pronichev.handlers;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.pronichev.repository.RepositoryException;

public class RepositoryHandler {

    @ExceptionHandler
    public ResponseEntity<RepositoryErrorResponse> handleException(RepositoryException exception) {
        return new ResponseEntity<>(
                RepositoryErrorResponse.builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(System.currentTimeMillis())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @Builder
    public static class RepositoryErrorResponse {
        private final int status;
        private final String message;
        private final long timestamp;
    }
}
