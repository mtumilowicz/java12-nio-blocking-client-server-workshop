package answers

import spock.lang.Specification

import java.nio.channels.SocketChannel

/**
 * Created by mtumilowicz on 2019-07-08.
 */
class ClientConnectionAnswerTest extends Specification {
    def "test communication"() {
        given:
        def outputStream = new ByteArrayOutputStream()
        def inputStream = new ByteArrayInputStream("Michal".bytes)

        when:
        new ClientConnectionAnswer(
                SocketChannel.open(),
                new PrintWriter(outputStream, true),
                new BufferedReader(new InputStreamReader(inputStream)))
                .run()
        def out = outputStream.toString()

        then:
        out.contains("What's your name?")
        out.contains('Hello, Michal')
    }
}
