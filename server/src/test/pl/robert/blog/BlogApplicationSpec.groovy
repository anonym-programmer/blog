package pl.robert.blog

import spock.lang.Specification

import org.springframework.context.ApplicationContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired

@SpringBootTest
class BlogApplicationSpec extends Specification {

    @Autowired
    ApplicationContext context

    def 'Should load Spring context'() {
        expect:
        context != null
    }
}
