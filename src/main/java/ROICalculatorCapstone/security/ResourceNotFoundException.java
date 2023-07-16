package ROICalculatorCapstone.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//The ResourceNotFoundException is a custom exception class that extends the RuntimeException
// class. It is annotated with @ResponseStatus(HttpStatus.NOT_FOUND) to indicate that when this
// exception is thrown, it should result in an HTTP response with a status code of 404 (Not Found).
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
