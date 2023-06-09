import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    public static boolean isAlive;

    public void run() {
        serverInit();
    }

    public void serverInit() {

        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("Server accepting connections...");
            Socket socket = serverSocket.accept();
            System.out.println("Server connected to " + socket.getLocalAddress() + ":" + socket.getPort());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            ChatGUI gui = new ChatGUI("Server");
            gui.setVisible(true);
            gui.startGUI(pw, "Server");
            String message = "";

            while (!message.equals("quit")) {
                message = br.readLine();
                System.out.println("Server: message received from client!");
                gui.setMsgDisplay("Client", message);
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
