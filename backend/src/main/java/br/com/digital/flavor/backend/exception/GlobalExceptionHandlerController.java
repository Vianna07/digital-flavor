package br.com.digital.flavor.backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtEncodingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@RestControllerAdvice
public class GlobalExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    public ResponseEntity<String> error(String message, HttpStatus httpStatus, Exception e) {
        logger.error("{}{}", message, e.getMessage(), e);
        return ResponseEntity.status(httpStatus).body(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        return error("Credenciais inválidas: ", HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return error("Argumento inválido: ", HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(JwtEncodingException.class)
    public ResponseEntity<String> handleJwtEncodingException(JwtEncodingException e) {
        return error("Erro ao codificar o token JWT: ", HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return error("Recurso não encontrado: ", HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return error("Erro interno no servidor: ", HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
}
