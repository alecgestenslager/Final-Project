import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    public static boolean isAlive;
    public static String message;

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


            while (!message.equals("quit")) {
                message = br.readLine();
                System.out.println("Server: message received from client!");

            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
