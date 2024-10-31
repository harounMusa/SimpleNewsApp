import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private NewsApplication app;
    private Image backgroundImage;

    public LoginPage(NewsApplication app) {
        this.app = app;
        app.frame.setResizable(false);
        backgroundImage = new ImageIcon("src/login BackGrond.jpg").getImage(); // Load image

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 10); // Padding around components

        gbc.gridy = 0;
        gbc.gridx = 0;
        JLabel usernameLabel = new JLabel("User name:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font with a specific family
        usernameLabel.setForeground(Color.white); // Set the text color
        add(usernameLabel, gbc);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridy = 1;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font with a specific family
        passwordLabel.setForeground(Color.white); // Set the text color
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridy = 3;
        add(passwordField, gbc);

        // Button for Show the Main page
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());
        gbc.gridy = 4;
        add(loginButton, gbc);

        // Button for Terms and Conditions
        JButton termsButton = new JButton("Terms and Conditions");
        termsButton.addActionListener(e -> showTermsAndConditions());
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.SOUTHEAST; // Align to bottom right
        add(termsButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw background image
        }
    }

    private void showTermsAndConditions() {
        // Navigate to Terms and Conditions Page
        TermsAndConditionsPage termsPage = new TermsAndConditionsPage(app);
        app.frame.getContentPane().removeAll();
        app.frame.add(termsPage);
        app.frame.revalidate();
        app.frame.repaint();
    }

    private class LoginAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (app.dbManager.authenticateUser(username, password)) {
                app.showMainPage();
            } else {
                JOptionPane.showMessageDialog(app.frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}


