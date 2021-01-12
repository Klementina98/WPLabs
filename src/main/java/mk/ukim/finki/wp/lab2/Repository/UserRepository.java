package mk.ukim.finki.wp.lab2.Repository;

import mk.ukim.finki.wp.lab2.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<MyUser,String> {
    MyUser findByUsernameAndPassword(String username, String password);

}
