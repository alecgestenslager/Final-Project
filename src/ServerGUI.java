import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerGUI extends JFrame implements Runnable {

    static PrintWriter pwServer;
    static boolean isAlive = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ServerGUI());
        serverInit();
    }


    private void initComponents() {
        setVisible(true);
        jScrollPane1 = new JScrollPane();
        msgDisplay = new JTextArea();
        jTextField1 = new JTextField();
        sendButton = new JButton();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        msgDisplay.setEditable(false);
        msgDisplay.setColumns(20);
        msgDisplay.setRows(5);
        jScrollPane1.setViewportView(msgDisplay);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == sendButton) {
                    sendButtonActionPerformed();
                }
            }
        };

        sendButton.setText("Send");
        sendButton.addActionListener(actionListener);

        jLabel1.setText("Server");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane1)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 761, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 851, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                        .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }

    private void sendButtonActionPerformed() {
        String message = jTextField1.getText();
        pwServer.println(message);
        setMsgDisplay("Server", message);
        jTextField1.setText("");
    }

    public static void setMsgDisplay(String prefix, String message) {
        msgDisplay.setText(msgDisplay.getText() + "\n" + prefix + ": " + message);
    }



    public static void serverInit() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ServerGUI().setVisible(true);
//            }
//        });

        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("Server accepting connections...");
            Socket socket = serverSocket.accept();
            System.out.println("Server connected to " + socket.getLocalAddress() + ":" + socket.getPort());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pwServer = new PrintWriter(socket.getOutputStream(), true);
            String message = "";

            while (true) {
                message = br.readLine();
                setMsgDisplay("Client", message);
                System.out.println("Server: message received from client");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Variables declaration - do not modify
    private JLabel jLabel1;
    private static JScrollPane jScrollPane1;
    private JTextField jTextField1;
    private static JTextArea msgDisplay;
    private JButton sendButton;

    public void run() {
        initComponents();
    }
}
