import java.io.IOException;

public class BlockingNIOServer2 extends ServerAnswer {

    public BlockingNIOServer2(int portNumber) {
        super(portNumber);
    }

    private BlockingNIOServer2() {
    }

    public static void main(String[] args) throws IOException {
        new BlockingNIOServer2().start();
    }

    @Override
    void handle(Runnable clientConnection) {
        clientConnection.run();
    }
}