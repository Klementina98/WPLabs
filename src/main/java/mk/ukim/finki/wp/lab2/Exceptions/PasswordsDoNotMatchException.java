package mk.ukim.finki.wp.lab2.Exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("Passwords Do Not Match Exception");
    }
}
