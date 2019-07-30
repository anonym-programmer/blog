package pl.robert.blog.app.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.robert.blog.app.user.domain.UserFacade;
import pl.robert.blog.app.user.domain.dto.UserDetailsDto;
import pl.robert.blog.app.user.domain.dto.ChangePasswordDto;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class AuthenticatedUserController {

    UserFacade facade;

    @PatchMapping
    public ResponseEntity updateDetails(@RequestBody UserDetailsDto dto) {
        return ResponseEntity.ok(facade.saveDetails(dto));
    }

    @PatchMapping("password")
    public ResponseEntity changePassword(@RequestBody ChangePasswordDto dto) {
        return ResponseEntity.ok(facade.changePassword(dto));
    }
}
