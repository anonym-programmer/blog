package pl.robert.blog.app.user;

import lombok.Getter;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
class User {

    @Id
    Long id;

    @Column(unique = true, length = 6, nullable = false)
    String username;

    @Column(unique = true, length = 12, nullable = false)
    String password;

    @Column(unique = true, length = 5, nullable = false)
    String role;
}
