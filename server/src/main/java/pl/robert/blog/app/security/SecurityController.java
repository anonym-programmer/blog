package pl.robert.blog.app.security;

import java.util.Base64;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import pl.robert.blog.app.user.domain.UserFacade;
import pl.robert.blog.app.user.domain.dto.UserDto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController(value = "/api/login")
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SecurityController {

    UserDto user;

    @Autowired
    public SecurityController(UserFacade facade) {
        user = facade.find();
    }

    @PostMapping
    public ResponseEntity login(@RequestBody UserDto dto) {
        if (dto.getUsername().equals(user.getUsername()) && dto.getPassword().equals(user.getPassword())) {

            HttpHeaders headers = new HttpHeaders();
            String s = dto.getUsername() + ":" + dto.getPassword();
            headers.set("Authorization", Base64.getUrlEncoder().encodeToString(s.getBytes()));

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .build();
        }

        return ResponseEntity
                .badRequest()
                .build();
    }
}
