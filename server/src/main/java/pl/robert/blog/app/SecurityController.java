package pl.robert.blog.app;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", origins = "*", maxAge = 3600)
@RequestMapping("/")
class SecurityController {

    @PostMapping("/login")
    public boolean login(@RequestBody LoginDto dto) {
        return dto.getUsername().equals("user") && dto.getPassword().equals("pass");
    }

    @GetMapping("/secured")
    public HttpEntity<?> sayHello() {
        return ResponseEntity.ok("Hello from secured area!");
    }
}
