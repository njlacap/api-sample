package project.sample.api.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.sample.api.exception.ProductException;

@RestControllerAdvice
public class ProductRestControllerAdvice {

    @ExceptionHandler(ProductException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomApiResponse handleSurveyException(ProductException ex) { return new CustomApiResponse(ex.getMessage()); }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomApiResponse handleGenericException(Exception ex) { return new CustomApiResponse(ex.getMessage()); }
}

@Data
@AllArgsConstructor
class CustomApiResponse {
    private String message;
}
