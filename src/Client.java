import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {

    public void run() {
        try {
            Socket socket = new Socket("localhost", 5555); // Establishes connection to server
            System.out.println("Client connected to " + socket.getLocalAddress() + ":" + socket.getLocalPort());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Initializes data input stream
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // Initializes data output stream
            ChatGUI gui = new ChatGUI(pw, "Client"); // Creates a new GUI object
            gui.startGUI(); // Starts the GUI
            String message = "";

            while (true) { // Reads in messages while the message is not "quit"
                if (message.equals("quit")) {
                    break;
                }

                message = br.readLine();
                System.out.println("Client Message Received!");
                gui.setMsgDisplay("Client", message);
            }
            pw.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
