import java.io.IOException;

public class BlockingNIOServer extends ServerAnswer {

    public BlockingNIOServer(int portNumber) {
        super(portNumber);
    }

    private BlockingNIOServer() {
    }

    public static void main(String[] args) throws IOException {
        new BlockingNIOServer().start();
    }

    @Override
    void handle(Runnable clientConnection) {
        clientConnection.run();
    }
}