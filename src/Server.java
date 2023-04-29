import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {


    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            Socket socket = serverSocket.accept();
            System.out.println("Server connected to " + socket.getLocalAddress() + ":" + socket.getPort());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            ChatGUI gui = new ChatGUI(pw, "Server");
            gui.startGUI();
            String message = "";

            while (!message.equals("quit")) {
                message = br.readLine();
                System.out.println("Server: " + message);
                gui.setMsgDisplay("Server", message);
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
