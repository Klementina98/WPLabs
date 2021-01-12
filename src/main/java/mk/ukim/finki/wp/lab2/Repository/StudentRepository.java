package mk.ukim.finki.wp.lab2.Repository;

import mk.ukim.finki.wp.lab2.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    //valda kje raboti
    List<Student> findAllByNameOrSurnameLike(String name,String surname);
}
