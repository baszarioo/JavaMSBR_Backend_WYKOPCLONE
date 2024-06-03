package com.b1lbudinhox.wykopclone.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;
import java.util.stream.Collectors;

public class InvalidateAuthException {
    public static String formatValidationErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField()+ " - " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));
    }

    public static Optional<String> processValidationErrors(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = formatValidationErrors(bindingResult);
            return Optional.of(errorMessage);
        }
        return Optional.empty();
    }
//    public static Optional<String> processValidationErrors(BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMessage = new StringBuilder("Invalid request body. Errors: ");
//            for (FieldError error: bindingResult.getFieldErrors()) {
//                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
//            }
//            if(errorMessage.toString().length()>0) {
//                return Optional.of(errorMessage.toString());
//            }
//        }
//        return Optional.empty();
//    }
}
