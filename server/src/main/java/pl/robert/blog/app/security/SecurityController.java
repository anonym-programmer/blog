package pl.robert.blog.app.security;

import java.util.Base64;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.*;
import pl.robert.blog.app.user.domain.UserFacade;
import pl.robert.blog.app.user.domain.dto.UserDto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/login")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SecurityController {

    UserFacade facade;

    @PostMapping
    public ResponseEntity login(@RequestBody UserDto dto) {
        UserDto user = facade.find();

        if (dto.getUsername().equals(user.getUsername()) && dto.getPassword().equals(user.getPassword())) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", Base64.getUrlEncoder()
                    .encodeToString((dto.getUsername() + ":" + dto.getPassword()).getBytes()));

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
