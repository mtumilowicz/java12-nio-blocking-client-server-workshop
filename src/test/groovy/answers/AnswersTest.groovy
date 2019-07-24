package answers

import client.TestClient
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2019-07-08.
 */
class AnswersTest extends Specification {

    def expectedClientOutput = ["received: What's your name?", "send: Michal", "received: Hello, Michal"]

    def "Step1_SingleThreadedServerAnswer"() {
        given:
        def port = 1
        
        expect:
        expectedClientOutput == extractClientOutputFor(port, new SingleThreadedNIOBlockingServerAnswer(port))
    }

    def extractClientOutputFor(port, server) {
        new Thread({ server.start() }).start()
        Thread.sleep(10)
        new TestClient(port).run()
    }
}