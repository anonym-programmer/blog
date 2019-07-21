package pl.robert.blog.app.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import pl.robert.blog.app.user.domain.dto.UserDetailsDto;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserDetails {

    @Id
    Long id;

    @Column
    String details;

    @OneToOne(mappedBy = "details")
    private User user;

    UserDetailsDto query() {
        return new UserDetailsDto(details);
    }
}
