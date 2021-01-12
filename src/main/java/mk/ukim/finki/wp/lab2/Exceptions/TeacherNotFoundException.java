package mk.ukim.finki.wp.lab2.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException(Long teacherId) {
        super("Theacher with this "+teacherId+"was not found");
    }
}
