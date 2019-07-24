package client

/**
 * Created by mtumilowicz on 2019-07-08.
 */
class TestClient {
    private final int port

    TestClient(int port) {
        this.port = port
    }

    def run() throws IOException {
        new Socket('localhost', port).withCloseable { client ->
            def br = new BufferedReader(new InputStreamReader(client.getInputStream()))

            def list = []

            def receivedMessage = br.readLine()
            list.add('received: ' + receivedMessage)

            PrintWriter pw = new PrintWriter(client.getOutputStream(), true)

            def sendMessage = 'Michal'
            pw.println(sendMessage)
            list.add('send: ' + sendMessage)

            receivedMessage = br.readLine()
            list.add("received: " + receivedMessage)

            return list
        }
    }
}
