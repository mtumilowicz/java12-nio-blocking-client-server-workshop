import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BlockingNIOServer {

    public static void main(String... args) throws IOException {
        System.out.println("run");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(81));

        ExecutorService pool = new ThreadPoolExecutor(
                10, 100,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000));

        while (true) {
            SocketChannel sc = ssc.accept(); // never null - blocks
            System.out.println("Connected to " + sc);
            pool.submit(() -> {
                while (sc.isConnected()) {
                    try {
                        PrintWriter writer = new PrintWriter(Channels.newWriter(sc, StandardCharsets.UTF_8.name()), true);
                        BufferedReader reader = new BufferedReader(Channels.newReader(sc, StandardCharsets.UTF_8.name()));

                        String s = reader.readLine();
                        writer.println(s.toUpperCase());
                    } catch (Exception ex) {
                        // workshop
                    }
                }
                System.out.println("Disconnected from " + sc);
            });
        }
    }
}