import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            Socket socket = serverSocket.accept();
            System.out.println("Server connected to " + socket.getLocalAddress() + ":" + socket.getPort());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            ChatGUI gui = new ChatGUI(pw);
            gui.startGUI();
            String message = "";

            while (!message.equals("quit")) {
                message = dis.readUTF();
                System.out.println("You are here (server)");
                gui.setMsgDisplay("Server", message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
