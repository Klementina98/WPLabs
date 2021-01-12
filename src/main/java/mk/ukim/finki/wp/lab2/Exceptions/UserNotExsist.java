package mk.ukim.finki.wp.lab2.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class UserNotExsist extends RuntimeException{
    public UserNotExsist() {
        super("User not exist");
    }
}
