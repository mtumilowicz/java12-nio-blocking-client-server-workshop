package answers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class SingleThreadedNIOBlockingServerAnswer {

    private final int portNumber;

    SingleThreadedNIOBlockingServerAnswer(int portNumber) {
        this.portNumber = portNumber;
    }

    SingleThreadedNIOBlockingServerAnswer() {
        this.portNumber = 81;
    }
    
    public static void main(String[] args) throws IOException {
        new SingleThreadedNIOBlockingServerAnswer().start();
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
    
    private void handle(Runnable clientConnection) {
        clientConnection.run();
    }
}