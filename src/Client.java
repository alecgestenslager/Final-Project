import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    public void run() {
        clientInit();
    }

    public static void clientInit() {
        try {
            Socket socket = new Socket("localhost", 5555); // Establishes connection to server
            System.out.println("Client connected to " + socket.getLocalAddress() + ":" + socket.getLocalPort());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Initializes data input stream
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // Initializes data output stream
            ChatGUI clientWindow = new ChatGUI("Client"); // Creates a new GUI object
            clientWindow.setVisible(true);
            clientWindow.startGUI(pw, "Client"); // Starts the GUI
            String message = "";

            while (true) { // Reads in messages while the message is not "quit"
                if (message.equals("quit")) {
                    break;
                }

                message = br.readLine();
                System.out.println("Client: Message received from server!");
                clientWindow.setMsgDisplay("Server", message);
            }
            pw.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
