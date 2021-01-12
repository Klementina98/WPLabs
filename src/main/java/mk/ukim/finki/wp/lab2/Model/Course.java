package mk.ukim.finki.wp.lab2.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;
    String name;
    String description;
    Type type;
    //list studenti koi slushat odreden kurs
    @ManyToMany
    List<Student> students;

    @ManyToOne
    Teacher teacher;

    public Course(String name, String description, Teacher teacher,Type type) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.type=type;

    }

    public Course(Long courseId, String name, String description, Teacher teacher,Type type) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.type=type;
    }

    public Course() {
    }

}
