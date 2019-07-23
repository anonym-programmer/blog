package pl.robert.blog.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import pl.robert.blog.app.user.domain.dto.UserDto;
import pl.robert.blog.app.user.domain.dto.UserDetailsDto;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class UserFacade {

    UserRepository repository;

    public UserDto find() {
        return repository
                .findById(1L)
                .stream()
                .map(User::query)
                .findAny()
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public UserDetailsDto saveDetails(UserDetailsDto dto) {
        return repository.findById(1L)
                .stream()
                .peek(user -> user.getDetails().setDetails(dto.getDetails()))
                .map(User::getDetails)
                .map(UserDetails::query)
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public UserDetailsDto findDetails() {
        return repository.findById(1L)
                .stream()
                .map(User::getDetails)
                .map(UserDetails::query)
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
