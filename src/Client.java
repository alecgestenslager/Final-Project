import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {


    public void run() {
        try {
            Socket socket = new Socket("localhost", 5555); // Establishes connection to server
            System.out.println("Client connected to " + socket.getLocalAddress() + ":" + socket.getLocalPort());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Initializes data input stream
            PrintWriter pw = new PrintWriter(socket.getOutputStream()); // Initializes data output stream
            ChatGUI gui = new ChatGUI(pw); // Creates a new GUI object
            gui.startGUI(); // Starts the GUI
            String message = "";

            while (!message.equals("quit")) { // Reads in messages while the message is not "quit"
                System.out.println("You are here! (line 20)");
                message = br.readLine();
                System.out.println("You are here (client)");
                gui.setMsgDisplay("Client", message);
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
