import javax.swing.*;
import java.awt.*;

public class TermsAndConditionsPage extends JPanel {
    private NewsApplication app;
    private Image backgroundImage;

    public TermsAndConditionsPage(NewsApplication app) {
        this.app = app;
        backgroundImage = new ImageIcon("src/login BackGrond.jpg").getImage(); // Load image

        setLayout(new BorderLayout());

        JTextArea termsArea = new JTextArea("Terms and Conditions\n" +
                "1. Acceptance of Terms By accessing and using this application, you agree to comply with and be bound by these terms and conditions. If you do not agree, please do not use our application.\n" +
                "\n" +
                "2. User Accounts You may be required to create an account to access certain features of the application. You are responsible for maintaining the confidentiality of your account information and for all activities that occur under your account.\n" +
                "\n" +
                "3. Content Ownership All content within this application, including text, images, and graphics, is owned by [Your Company Name] or its content suppliers. Unauthorized use or distribution of any content is prohibited.\n" +
                "\n" +
                "4. User-Generated Content Users may have the ability to submit articles or content. By submitting content, you grant us a non-exclusive, worldwide, royalty-free license to use, reproduce, and publish that content.\n" +
                "\n" +
                "5. Prohibited Activities You agree not to engage in any of the following prohibited activities:\n" +
                "\n" +
                "Transmitting any unlawful, harmful, or objectionable content.\n" +
                "Attempting to gain unauthorized access to any part of the application.\n" +
                "Using any automated means to access the application for any purpose without our express written permission.\n" +
                "6. Disclaimers The application is provided on an \"as is\" and \"as available\" basis. We do not guarantee that the application will be free from errors or interruptions.\n" +
                "\n" +
                "7. Limitation of Liability In no event shall [Your Company Name] be liable for any direct, indirect, incidental, or consequential damages arising out of or in connection with your use of the application.\n" +
                "\n" +
                "8. Modifications We reserve the right to modify these terms at any time. Changes will be effective immediately upon posting on the application. Your continued use of the application after changes are posted constitutes your acceptance of the new terms.\n" +
                "\n" +
                "9. Governing Law These terms shall be governed by and construed in accordance with the laws of [Your Country/State], without regard to its conflict of law principles.\n" +
                "\n" +
                "10. Contact Information For any questions or concerns about these terms, please contact us at haroun.m.nasreldin@gmail.com.");
        termsArea.setEditable(false);
        termsArea.setLineWrap(true);
        termsArea.setBackground(new Color(185, 229, 232, 200));
        termsArea.setForeground(Color.black);
        termsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(termsArea);

        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> goBackToLoginPage());

        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw background image
        }
    }

    private void goBackToLoginPage() {
        LoginPage loginPage = new LoginPage(app);
        app.frame.getContentPane().removeAll();
        app.frame.add(loginPage);
        app.frame.revalidate();
        app.frame.repaint();
    }
}

