import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
                        ByteBuffer buf = ByteBuffer.allocateDirect(80);
                        int read = sc.read(buf);
                        if (read == -1) {
                            sc.close();
                            return;
                        }
                        if (read > 0) {
                            Util.transmogrify(buf);
                            while (buf.hasRemaining()) {
                                sc.write(buf);
                            }
                        }
                    } catch (Exception ex) {
                        // workshop
                    }
                }
                System.out.println("Disconnected from " + sc);
            });
        }
    }
}