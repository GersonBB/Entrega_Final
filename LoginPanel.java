
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private LoginManager loginManager;
    private MainFrame mainFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(LoginManager loginManager, MainFrame mainFrame) {
        this.loginManager = loginManager;
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Usuario:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Iniciar Sesión");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);

        JButton registerButton = new JButton("Registrarse");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(registerButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (performLogin()) {
                    mainFrame.showMainOptions();
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Inicio de sesión fallido");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performRegistration();
            }
        });
    }

    private boolean performLogin() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        if (loginManager.validateLogin(username, password)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(LoginPanel.this, "Credenciales no válidas. Regístrese si es un nuevo usuario.");
            return false;
        }
    }

    private void performRegistration() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        if (!loginManager.userExists(username)) {
            loginManager.registerUser(username, password);
            JOptionPane.showMessageDialog(LoginPanel.this, "Registro exitoso. Puede iniciar sesión ahora.");
        } else {
            JOptionPane.showMessageDialog(LoginPanel.this, "El usuario ya existe. Inicie sesión con sus credenciales.");
        }
    }
}
