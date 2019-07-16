package pl.robert.blog.app.user;

import org.springframework.data.repository.Repository;

import java.util.Optional;

interface UserRepository extends Repository<User, Long> {

    Optional<User> findById(Long id);
}
