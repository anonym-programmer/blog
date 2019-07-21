package pl.robert.blog.app.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.blog.app.user.domain.dto.UserDto;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class User {

    @Id
    Long id;

    @Column(unique = true, length = 6, nullable = false)
    String username;

    @Column(unique = true, length = 12, nullable = false)
    String password;

    @Column(unique = true, length = 5, nullable = false)
    String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    UserDetails details;

    UserDto query() {
        return new UserDto(username, password, role, details.getDetails());
    }
}
