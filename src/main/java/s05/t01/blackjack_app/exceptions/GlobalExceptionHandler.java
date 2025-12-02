package s05.t01.blackjack_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handlePlayerNotFoundException(PlayerNotFoundException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleGameNotFoundException(GameNotFoundException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(InvalidActionException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleInvalidPlayException(InvalidActionException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}