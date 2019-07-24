package workshop

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2019-07-08.
 */
class ClientConnectionWorkshopTest extends Specification {
    def "test communication"() {
        given:
        def outputStream = new ByteArrayOutputStream()
        def inputStream = new ByteArrayInputStream("Michal".bytes)

        def socket = Mock(Socket) {
            getOutputStream() >> outputStream
            getInputStream() >> inputStream
        }

        when:
        new ClientConnectionWorkshop(socket).run()
        def out = outputStream.toString()

        then:
        out.contains("What's your name?")
        out.contains('Hello, Michal')
    }
}
