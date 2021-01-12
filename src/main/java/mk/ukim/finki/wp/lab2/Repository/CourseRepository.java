package mk.ukim.finki.wp.lab2.Repository;

import mk.ukim.finki.wp.lab2.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}
