package mk.ukim.finki.wp.lab2.Service.Impl;


import mk.ukim.finki.wp.lab2.Exceptions.TeacherNotFoundException;
import mk.ukim.finki.wp.lab2.Model.Teacher;
import mk.ukim.finki.wp.lab2.Repository.TeacherRepository;
import mk.ukim.finki.wp.lab2.Service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    public final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(()->new TeacherNotFoundException(teacherId));
    }
}
