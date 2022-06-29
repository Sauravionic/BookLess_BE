package io.bookless.BookLess.Entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;


@Entity
@Data
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @Column(length = 60)
    private String password;
    private String role;
    private boolean enabled = false;

}
