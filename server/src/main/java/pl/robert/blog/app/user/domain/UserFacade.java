package pl.robert.blog.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import pl.robert.blog.app.user.domain.dto.UserDto;
import pl.robert.blog.app.user.domain.dto.UserDetailsDto;
import pl.robert.blog.app.user.domain.dto.ChangePasswordDto;

import pl.robert.blog.app.user.domain.exception.InvalidPasswordException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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

    public String changePassword(ChangePasswordDto dto) {

        if (!find().getPassword().equals(dto.getOldPassword())) {
            throw new InvalidPasswordException("Old password do not equal current password!");
        }

        return repository.findById(1L)
                .stream()
                .peek(user -> user.setPassword(dto.getNewPassword()))
                .map(User::getPassword)
                .collect(Collectors.joining());
    }
}
