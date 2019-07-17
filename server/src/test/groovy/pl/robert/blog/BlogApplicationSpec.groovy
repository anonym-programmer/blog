package pl.robert.blog

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

import spock.lang.Specification

@SpringBootTest
class BlogApplicationSpec extends Specification {

    @Autowired
    ApplicationContext context

    def 'Should load Spring context'() {
        expect:
        context != null
    }
}
