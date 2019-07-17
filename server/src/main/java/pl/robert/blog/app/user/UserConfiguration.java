package pl.robert.blog.app.user;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade facade(UserRepository repository) {
        return new UserFacade(repository);
    }

    UserFacade facade(ConcurrentHashMap<Long, User> db) {
        return new UserFacade(new InMemoryUserRepository(db));
    }
}
