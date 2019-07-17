package pl.robert.blog.app.user;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
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
