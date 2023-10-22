package com.oncampus.oncampusApi.system.exception;

import com.oncampus.oncampusApi.system.Result;
import com.oncampus.oncampusApi.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handleObjectNotFoundException(ObjectNotFoundException ex) {
        return new Result(false, StatusCode.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Result handleInvalidDataException(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());

        errors.forEach((error) -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });
        return new Result(false, StatusCode.INVALID_ARGUMENT, "Provided arguments are invalid, see data for details", map);
    }

//    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    Result handleAuthenticationException(Exception ex) {
//        return new Result(false, StatusCode.UNAUTHORISED, "username or password is incorrect");
//    }

//    @ExceptionHandler(AccountStatusException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    Result handleAccountStatusException(Exception ex) {
//        return new Result(false, StatusCode.UNAUTHORISED, "user account is abnormal", ex.getMessage());
//    }

//    @ExceptionHandler(InvalidBearerTokenException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    Result handleInvalidBearerTokenException(Exception ex) {
//        return new Result(false, StatusCode.UNAUTHORISED, "Access token provided is expired, revoked, malformed or invalid for other reasons");
//    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    Result handleAccessDeniedException(Exception ex) {
        return new Result(false, StatusCode.FORBIDDEN, "No permission");
    }

    /*
     * Fallback handles any unhandled exceptions
     * */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Result handleOtherException(Exception ex) {
        return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "A server internal error occurred");
    }

}
