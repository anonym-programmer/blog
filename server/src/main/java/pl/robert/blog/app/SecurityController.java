package pl.robert.blog.app;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import java.util.Base64;

@RestController
@CrossOrigin("*")
class SecurityController {

    @PostMapping("/login")
    public boolean login(@RequestBody LoginDto dto) {
        return dto.getUsername().equals("user") && dto.getPassword().equals("pass");
    }

    @GetMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request
                .getHeader("Authorization")
                .substring("Basic".length())
                .trim();
        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }
}
