package pl.robert.blog.app.user.domain;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class InMemoryUserRepository implements UserRepository {

    ConcurrentHashMap<Long, User> map;

    @Override
    public Optional<User> findById(Long id) {
        return map.entrySet()
                .stream()
                .filter(map -> map.getKey().equals(id))
                .map(Map.Entry::getValue)
                .findFirst();
    }
}
