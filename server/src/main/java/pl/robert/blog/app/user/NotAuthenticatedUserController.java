package pl.robert.blog.app.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.robert.blog.app.user.domain.UserFacade;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotAuthenticatedUserController {

    UserFacade facade;

    @GetMapping
    public ResponseEntity findDetails() {
        return ResponseEntity.ok(facade.findDetails());
    }
}
