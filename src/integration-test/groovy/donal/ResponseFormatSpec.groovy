package donal

import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@Integration
class ResponseFormatSpec extends Specification {

    @Shared
    def rest = new RestBuilder()

    void 'test default response format for index'() {
        when: 'the index action is invoked'
        def resp = rest.get("http://localhost:${serverPort}/demo")
        def contentType = resp.headers.getContentType()

        then: 'the default response format should be xml'
        resp.status == OK.value()
        contentType.subtype == 'xml'
        contentType.type == 'text'
    }

    void 'test default response format for save'() {
        when: 'the save action is invoked'
        def resp = rest.post("http://localhost:${serverPort}/demo") {
            json {
                name = 'Jeff'
            }
        }
        def contentType = resp.headers.getContentType()

        then: 'the default response format should be json'
        resp.status == CREATED.value()
        contentType.subtype == 'json'
        contentType.type == 'application'
    }
}
