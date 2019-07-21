package pl.robert.blog.app.user.domain;

import java.util.Optional;

import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<User, Long> {

    Optional<User> findById(Long id);
}
