package pl.robert.blog.app.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import pl.robert.blog.app.user.domain.dto.UserDetailsDto;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserDetails {

    @Id
    Long id;

    @Column(length = 1000000)
    String details;

    @OneToOne(mappedBy = "details")
    private User user;

    UserDetailsDto query() {
        return new UserDetailsDto(details);
    }
}
