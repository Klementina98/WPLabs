package mk.ukim.finki.wp.lab2.Service;

import mk.ukim.finki.wp.lab2.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeacherService {
    public List<Teacher> findAll();
    public Teacher findById(Long teacherId);
}
