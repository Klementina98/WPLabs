package mk.ukim.finki.wp.lab2.Service.Impl;

import mk.ukim.finki.wp.lab2.Exceptions.InvalidArgumentException;
import mk.ukim.finki.wp.lab2.Exceptions.StudentNotFoundException;
import mk.ukim.finki.wp.lab2.Model.Student;
import mk.ukim.finki.wp.lab2.Repository.StudentRepository;
import mk.ukim.finki.wp.lab2.Service.StudentService;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    public final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        if (text.isEmpty()){
            throw new InvalidArgumentException();
        }
        return studentRepository.findAllByNameOrSurnameLike(text,text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        //pred da napravime save da gi proverime argumentite
        if (username==null || username.isEmpty() ||
                password==null || password.isEmpty() ||
                name==null || name.isEmpty() ||
                surname==null || surname.isEmpty()){
            throw new InvalidArgumentException();
        }
        return studentRepository.save(new Student(username,password,name,surname));
    }

    @Override
    public Student findById(String username) {
        return studentRepository.findById(username).orElseThrow(()->new StudentNotFoundException(username));
    }
}
