package mk.ukim.finki.wp.lab2.Exceptions;

public class UsernameAlreadyExsistException extends RuntimeException{
    public UsernameAlreadyExsistException(String message) {
        super("Username Already Exsist Exception");
    }
}
