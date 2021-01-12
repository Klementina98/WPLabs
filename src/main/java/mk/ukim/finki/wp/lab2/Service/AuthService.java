package mk.ukim.finki.wp.lab2.Service;

import mk.ukim.finki.wp.lab2.Model.MyUser;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    MyUser logIn(String userName, String password);
}
