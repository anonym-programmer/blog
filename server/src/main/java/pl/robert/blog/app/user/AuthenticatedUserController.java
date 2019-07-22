package pl.robert.blog.app.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.robert.blog.app.user.domain.UserFacade;
import pl.robert.blog.app.user.domain.dto.UserDetailsDto;

@RestController(value = "/api/admin")
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class AuthenticatedUserController {

    UserFacade facade;

    @PatchMapping
    public ResponseEntity updateDetails(@RequestBody UserDetailsDto dto) {
        return ResponseEntity.ok(facade.saveDetails(dto));
    }
}
