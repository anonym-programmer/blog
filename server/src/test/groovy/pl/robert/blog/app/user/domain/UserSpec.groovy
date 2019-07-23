package pl.robert.blog.app.user.domain

import spock.lang.Shared
import spock.lang.Specification

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import java.util.concurrent.ConcurrentHashMap

import pl.robert.blog.app.user.domain.dto.UserDetailsDto

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

    def 'Should save and update details'() {
        when: 'we add user to db'
        db.put(1L, new User(1L, null, null, null, new UserDetails()))

        and: 'we save details'
        facade.saveDetails(new UserDetailsDto('<h1>Hello</h1>'))

        then: 'user has this details'
        facade.findDetails().details == '<h1>Hello</h1>'

        and: 'we save again details'
        facade.saveDetails(new UserDetailsDto('<h1>HelloWorld</h1>'))

        then: 'user details has been updated'
        facade.findDetails().details == '<h1>HelloWorld</h1>'
    }
}
