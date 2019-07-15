package pl.robert.blog.app;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.Base64;

@RestController
@CrossOrigin(value = "*", origins = "*", maxAge = 3600)
class SecurityController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto dto) {
        if (dto.getUsername().equals("user") && dto.getPassword().equals("pass")) {

            HttpHeaders headers = new HttpHeaders();
            String s = dto.getUsername() + ":" + dto.getPassword();
            headers.set("Authorization", Base64.getUrlEncoder().encodeToString(s.getBytes()));

            return ResponseEntity.ok()
                    .headers(headers)
                    .build();
        }

        return ResponseEntity
                .badRequest()
                .build();
    }

    @GetMapping("/secured")
    public HttpEntity<?> sayHello() {
        return ResponseEntity
                .ok()
                .build();
    }
}
