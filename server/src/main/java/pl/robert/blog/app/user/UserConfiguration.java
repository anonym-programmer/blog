package pl.robert.blog.app.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

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
