package mk.ukim.finki.wp.lab2.Model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MyUser {

    @Id
    String username;
    String password;
    String name;
    String surname;

    public MyUser() {
    }

    public MyUser(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

}
