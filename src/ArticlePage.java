import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArticlePage extends JPanel {
    private DatabaseManager dbManager;
    private JTextField titleField;
    private JTextField imagePathField;
    private JTextArea contentArea;
    private NewsApplication app;
    private Image backgroundImage;

    public ArticlePage(NewsApplication app, DatabaseManager dbManager) {
        this.app = app;
        app.frame.setResizable(false);
        backgroundImage = new ImageIcon("src/newArticalBackground.jpg").getImage(); // Load image
        this.dbManager = dbManager;

        setLayout(new GridBagLayout()); // Use BoxLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 10); // Padding around components
        gbc.gridy = 0;
        gbc.gridx = 0;

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font with a specific family
        titleLabel.setForeground(Color.white); // Set the text color
        add(titleLabel, gbc);

        titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(200, 30));
        gbc.gridy = 1;
        add(titleField, gbc);

        JLabel imageLabel = new JLabel("Image Path:");
        imageLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font with a specific family
        imageLabel.setForeground(Color.white); // Set the text color
        gbc.gridy = 2;
        add(imageLabel, gbc);

        // Add file picker button
        JButton filePickerButton = new JButton("Select Image");
        filePickerButton.addActionListener(e -> chooseImageFile());
        gbc.gridy = 3;
        add(filePickerButton, gbc);


        JLabel contentLabel = new JLabel("The Content of The Article:");
        contentLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font with a specific family
        contentLabel.setForeground(Color.white); // Set the text color
        gbc.gridy = 4;
        add(contentLabel, gbc);

        contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setPreferredSize(new Dimension(400, 100));
        gbc.gridy = 5;
        add(contentArea, gbc);


        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center buttons
        buttonPanel.setAlignmentX(Component.BOTTOM_ALIGNMENT); // Center the panel
        buttonPanel.setBackground(Color.lightGray);

        JButton uploadButton = new JButton("Upload Article");
        uploadButton.addActionListener(new UploadAction());
        JButton backButton = new JButton("Back to Main Page");
        backButton.addActionListener(e -> goBackToMainPage());

        // Add buttons to the button panel
        buttonPanel.add(uploadButton);
        buttonPanel.add(backButton);

        gbc.gridy = 6;
        add(buttonPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw background image
        }
    }

    private void chooseImageFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
            imagePathField.setText(imagePath);
        }
    }

    private class UploadAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String imagePath = imagePathField.getText();
            String content = contentArea.getText();
            NewsArticle article = new NewsArticle(title, imagePath, content);
            dbManager.addArticle(article);
            // Optionally, refresh the news list or show success message
        }
    }

    private void goBackToMainPage() {
        MainPage mainPage = new MainPage(app, dbManager);
        app.frame.getContentPane().removeAll();
        app.frame.add(mainPage);
        app.frame.revalidate();
        app.frame.repaint();
    }
}

