package org.amirziya.todoweb.exception;


import org.amirziya.todoweb.responseModel.MassageResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHanling  {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MassageResponse> notFound(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MassageResponse(HttpStatus.NOT_FOUND.toString(),ex.getMessage()));
    }
}
