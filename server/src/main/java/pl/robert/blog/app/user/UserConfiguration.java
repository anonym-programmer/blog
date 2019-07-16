package pl.robert.blog.app.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    @Bean
    UserService service(UserRepository repository) {
        return new UserService(repository);
    }
}
