package groovy.pl.robert.blog.app.user.domain

import pl.robert.blog.app.user.domain.User
import pl.robert.blog.app.user.domain.UserConfiguration
import pl.robert.blog.app.user.domain.UserDetails
import pl.robert.blog.app.user.domain.UserFacade

import spock.lang.Shared
import spock.lang.Specification

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import java.util.concurrent.ConcurrentHashMap

import org.springframework.security.core.userdetails.UsernameNotFoundException

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserSpec extends Specification {

    @Shared
    UserFacade facade

    @Shared
    ConcurrentHashMap<Long, User> db = new ConcurrentHashMap<>()

    def setupSpec() {
        facade = new UserConfiguration().facade(db)
    }

    def 'Should find one user with default fields'() {
        when: 'we add user to db'
        db.put(1L, new User(1L, 'Robert', '!23QweAsd#@!', 'ADMIN', new UserDetails()))

        and: 'we find user'
        def user = facade.find()

        then: 'user contains default fields'
        checkDefaultFields(user)

        and: 'we delete user from db'
        db.clear()
    }

    def checkDefaultFields(user) {
        user.username == 'Robert' && user.password == '!23QweAsd#@!' && user.role == 'ADMIN'
    }

    def 'Should throw an exception cause user not found'() {
        when: 'we try to find user'
        facade.find()

        then: 'exception is thrown'
        thrown UsernameNotFoundException
    }
}
