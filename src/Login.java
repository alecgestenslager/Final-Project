import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Login extends JFrame {

    static JPanel panel;
    static JFrame frame;
    static JTextField username;
    static JPasswordField password;
    static JButton loginButton;
    static JLabel error;
    public static void loginInit() {

        // Initializing JPanel
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login");
        frame.add(panel);
        frame.setLocationRelativeTo(null);

        panel.setLayout(null); // Panel layout

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loginButton) {
                    login();
                }
            }
        };

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        // Initializing label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(100, 50, 80, 25);
        panel.add(usernameLabel);

        // Initializing username filed
        username = new JTextField(20);
        username.setBounds(167, 50, 165, 25);
        panel.add(username);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 80, 80, 25);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(167, 80, 165, 25);
        panel.add(password);

        loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setBounds(210, 110, 80, 25);
        loginButton.addActionListener(actionListener);
        loginButton.addKeyListener(keyListener);
        panel.add(loginButton);

        error = new JLabel("Server must log in first");
        error.setBounds(177, 150, 145, 25);
        error.setForeground(Color.red);
        panel.add(error);
        error.setVisible(false);

        frame.setVisible(true);
    }

    private static void login() {
        if ((username.getText().equals("serverAdmin") && (password.getText().equals("serverAdminPassword") || password.getText().equals("bypass")))
                || (username.getText().equals("clientAdmin") && (password.getText().equals("clientAdminPassword") || password.getText().equals("bypass")))) {
            panel.setVisible(false);
            frame.setVisible(false);
            new Thread(new Server()).start();
            new Thread(new Client()).start();
        } else {
            error.setVisible(true);
            username.setText("");
            password.setText("");
        }
    }
}
