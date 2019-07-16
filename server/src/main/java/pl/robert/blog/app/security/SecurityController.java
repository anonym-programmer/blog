package pl.robert.blog.app.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.robert.blog.app.user.dto.UserDto;
import pl.robert.blog.app.user.UserService;

import java.util.Base64;

@RestController(value = "/login")
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SecurityController {

    UserDto user;

    @Autowired
    public SecurityController(UserService service) {
        user = service.find();
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
