package mk.ukim.finki.wp.lab2.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(Long courseId) {
            super("Course with "+courseId+"was not found");
    }
}
