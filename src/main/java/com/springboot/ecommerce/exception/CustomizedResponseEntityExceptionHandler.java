package com.springboot.ecommerce.exception;

import com.springboot.ecommerce.auth.exception.CredentialsMismatchException;
import com.springboot.ecommerce.auth.exception.UserAlreadyExistsException;
import com.springboot.ecommerce.auth.exception.UserNotFoundException;
import com.springboot.ecommerce.brand.BrandNotFoundException;
import com.springboot.ecommerce.cart.CartItemNotFoundException;
import com.springboot.ecommerce.cart.CartNotFoundException;
import com.springboot.ecommerce.category.CategoryNotFoundException;
import com.springboot.ecommerce.product.ProductNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception exception, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CredentialsMismatchException.class)
    public final ResponseEntity<ErrorDetails> handleCredentialsMismatchException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleBrandNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleCartNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleCartItemNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleCategoryNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleProductNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : methodArgumentNotValidException.getFieldErrors()) {
            errorMessage.append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append("\n");
        }

        String errorDescription = errorMessage.toString();

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), errorDescription,
                request.getDescription(false));

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
