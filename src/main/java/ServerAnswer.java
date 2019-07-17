import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by mtumilowicz on 2019-06-29.
 */
abstract class ServerAnswer {

    private final int portNumber;

    ServerAnswer(int portNumber) {
        this.portNumber = portNumber;
    }

    ServerAnswer() {
        this.portNumber = 81;
    }

    void start() throws IOException {
        log("Creating server socket on port " + portNumber);
        ServerSocketChannel serverSocket = ServerSocketChannel.open()
                .bind(new InetSocketAddress(portNumber));
        log("Created server socket on port " + portNumber);

        while (true) {
            final var client = serverSocket.accept();
            log("Accepted connection from " + client);
            
            handle(new ClientConnectionAnswer(client));
        }
    }

    private void log(String message) {
        System.out.println(message);
    }

    abstract void handle(Runnable client);
}