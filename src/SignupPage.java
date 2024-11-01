import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupPage extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private NewsApplication app;
    private Image backgroundImage;

    public SignupPage(NewsApplication app) {
        this.app = app;
        app.frame.setResizable(false);
        backgroundImage = new ImageIcon("src/login BackGrond.jpg").getImage(); // Load image

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 10); // Padding around components

        gbc.gridy = 0;
        gbc.gridx = 0;
//        JLabel usernameLabel = new JLabel("User name:");
//        add(usernameLabel, gbc);
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

        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(new SignupAction());
        gbc.gridy = 4;
        add(signupButton, gbc);

        // Button for navigating to login Page
        JButton loginButton = new JButton("Back to login");
        loginButton.addActionListener(e -> showLoginPage());
        gbc.gridy = 5; // Place below the terms button
        gbc.anchor = GridBagConstraints.SOUTHEAST; // Align to bottom right
        add(loginButton, gbc);
    }
    private void showLoginPage(){
        // Navigate to Login Page
        LoginPage loginPage = new LoginPage(app);
        app.frame.getContentPane().removeAll();
        app.frame.add(loginPage);
        app.frame.revalidate();
        app.frame.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw background image
        }

    }


    private class SignupAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Add your user creation logic here
            app.dbManager.addUser(username, password);

            JOptionPane.showMessageDialog(app.frame, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            app.showMainPage();

            // Navigate back to the login page or main page as necessary
        }
    }
}
