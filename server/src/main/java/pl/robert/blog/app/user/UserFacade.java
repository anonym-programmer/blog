package pl.robert.blog.app.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import pl.robert.blog.app.user.dto.UserDto;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

    UserRepository repository;

    public UserDto find() {
        return repository
                .findById(1L)
                .stream()
                .map(user -> new UserDto(user.username, user.password, user.role))
                .findAny()
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
