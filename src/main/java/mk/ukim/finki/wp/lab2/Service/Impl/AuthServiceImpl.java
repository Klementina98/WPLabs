package mk.ukim.finki.wp.lab2.Service.Impl;


import mk.ukim.finki.wp.lab2.Exceptions.InvalidArgumentException;
import mk.ukim.finki.wp.lab2.Exceptions.UserNotExsist;
import mk.ukim.finki.wp.lab2.Model.MyUser;
import mk.ukim.finki.wp.lab2.Repository.UserRepository;
import mk.ukim.finki.wp.lab2.Service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    public final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public MyUser logIn(String userName, String password) {
        if (userName==null || userName.isEmpty() ||
                password==null || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(userName,password);
    }
}
