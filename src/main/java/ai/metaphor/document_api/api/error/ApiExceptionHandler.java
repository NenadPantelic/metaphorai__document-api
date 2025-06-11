package ai.metaphor.document_api.api.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.processing.FilerException;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(FilerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleFilterException(FilerException exception) {
        return new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleException(RuntimeException exception) {
        return new ApiError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}