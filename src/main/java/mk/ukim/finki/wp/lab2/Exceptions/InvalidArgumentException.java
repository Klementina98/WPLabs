package mk.ukim.finki.wp.lab2.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException() {
        super("Invalid argument exception");
    }
}
