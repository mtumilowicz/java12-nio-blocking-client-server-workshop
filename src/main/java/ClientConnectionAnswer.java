import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * Created by mtumilowicz on 2019-07-07.
 */
class ClientConnectionAnswer implements Runnable {
    private final SocketChannel client;
    private final PrintWriter writer;
    private final BufferedReader reader;

    ClientConnectionAnswer(SocketChannel client) {
        this.client = client;
        this.writer = new PrintWriter(Channels.newWriter(client, StandardCharsets.UTF_8.name()), true);
        this.reader = new BufferedReader(Channels.newReader(client, StandardCharsets.UTF_8.name()));
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
