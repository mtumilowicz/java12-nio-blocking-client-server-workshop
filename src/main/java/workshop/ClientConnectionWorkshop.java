package workshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by mtumilowicz on 2019-07-07.
 */
class ClientConnectionWorkshop implements Runnable {
    private final Socket client; // change to SocketChannel
    private final PrintWriter writer;
    private final BufferedReader reader;

    ClientConnectionWorkshop(Socket client) throws IOException { // change to SocketChannel
        this(
                client,
                new PrintWriter(client.getOutputStream(), true), // Channels.newWriter(..., UTF_8)
                new BufferedReader(new InputStreamReader(client.getInputStream())) // Channels.newReader(..., UTF_8)
        );
    }

    public ClientConnectionWorkshop(Socket client, PrintWriter writer, BufferedReader reader) {
        this.client = client;
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void run() {
        try (client) {
            sendLine("What's your name?");

            var name = readLine();
            sendLine("Hello, " + name);

            log("Just said hello to:" + name);
        } catch (IOException exception) {
            // workshops
        }
    }

    private void sendLine(String message) {
        writer.println(message);
    }

    private String readLine() throws IOException {
        return reader.readLine();
    }

    private void log(String message) {
        System.out.println(message);
    }
}
