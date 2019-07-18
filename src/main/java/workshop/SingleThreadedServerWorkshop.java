package workshop;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by mtumilowicz on 2019-06-23.
 */
class SingleThreadedServerWorkshop {

    private final int portNumber;

    SingleThreadedServerWorkshop(int portNumber) {
        this.portNumber = portNumber;
    }

    private SingleThreadedServerWorkshop() {
        this.portNumber = 81;
    }

    public static void main(String[] args) throws IOException {
        new SingleThreadedServerWorkshop().start();
    }

    void start() throws IOException {
        log("Creating server socket on port " + portNumber);
        var serverSocket = new ServerSocket(portNumber); // ServerSocketChannel.open().bind(InetSocketAddress(...))
        log("Created server socket on port " + portNumber);

        while (true) {
            final var client = serverSocket.accept();
            log("Accepted connection from " + client);
            
            new ClientConnectionWorkshop(client).run();
        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}
