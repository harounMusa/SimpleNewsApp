import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainPage extends JPanel {
    private NewsApplication app;
    private DatabaseManager dbManager;
    private JLabel newsPaperName;
    private JList<String> articleList;
    private DefaultListModel<String> articleListModel;

    public MainPage(NewsApplication app, DatabaseManager dbManager) {
        this.app = app;
        app.frame.setResizable(true);
        this.dbManager = dbManager;
        setLayout(new BorderLayout());

        // Create the article list model and list
        articleListModel = new DefaultListModel<>();
        articleList = new JList<>(articleListModel);
        loadArticles();

        // Newspaper Name
        newsPaperName = new JLabel("News Application");
        newsPaperName.setFont(new Font("Arial", Font.ROMAN_BASELINE, 55));
        newsPaperName.setForeground(Color.white);
        JPanel newsNamePanel = new JPanel();
        newsNamePanel.setBackground(new Color(190, 190, 195));
        newsNamePanel.setOpaque(true);
        newsNamePanel.add(newsPaperName);
        add(newsNamePanel, BorderLayout.NORTH);

        // Set up the split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(150); // Set initial divider position

        JScrollPane liftPane = new JScrollPane(articleList);
        liftPane.setBackground(Color.lightGray);
        liftPane.setOpaque(true);
        splitPane.setLeftComponent(liftPane);

        // Create a panel for article details with GridBagLayout
        JPanel detailPanel = new JPanel(new GridBagLayout());
        detailPanel.setBackground(Color.lightGray); // Set the background color
        splitPane.setRightComponent(new JScrollPane(detailPanel)); // Make it scrollable

        // Add the split pane to the main panel
        add(splitPane, BorderLayout.CENTER);

        // Listener for article selection
        articleList.addListSelectionListener(e -> showArticleDetails(detailPanel, articleList.getSelectedValue()));

        // New Article button
        JButton newArticleButton = new JButton("New Article");
        newArticleButton.addActionListener(e -> showArticlePage());
        add(newArticleButton, BorderLayout.SOUTH);
    }

    private void loadArticles() {
        ResultSet rs = dbManager.getArticles();
        try {
            while (rs != null && rs.next()) {
                String title = rs.getString("title");
                articleListModel.addElement(title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showArticleDetails(JPanel detailPanel, String selectedTitle) {
        detailPanel.removeAll(); // Clear previous content
        if (selectedTitle != null) {
            // Fetch article details from the database
            ResultSet rs = dbManager.getArticles(); // You might want to fetch based on title
            try {
                while (rs != null && rs.next()) {
                    if (rs.getString("title").equals(selectedTitle)) {
                        String content = rs.getString("content");
                        String imagePath = rs.getString("imagePath");

                        // Create constraints for GridBagLayout
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        gbc.insets = new Insets(10, 10, 10, 10); // Padding

                        // Title
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        JLabel titleLabel = new JLabel(selectedTitle);
                        titleLabel.setFont(new Font("Arial", Font.BOLD, 45));
                        detailPanel.add(titleLabel, gbc);

                        // Image
                        gbc.gridx = 0;
                        gbc.gridy = 1;
                        JLabel imageLabel = new JLabel();
                        ImageIcon imageIcon = new ImageIcon(imagePath); // Load image
                        imageLabel.setIcon(imageIcon);
                        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        detailPanel.add(imageLabel, gbc);

                        // Content
                        gbc.gridx = 0;
                        gbc.gridy = 2;
                        JTextArea contentArea = new JTextArea(content);
                        contentArea.setEditable(false);
                        contentArea.setLineWrap(true);
                        contentArea.setWrapStyleWord(true);
                        contentArea.setBackground(detailPanel.getBackground());
                        contentArea.setFont(new Font("Arial", Font.PLAIN, 20));
                        contentArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Small gap above content
                        detailPanel.add(contentArea, gbc);

                        detailPanel.revalidate();
                        detailPanel.repaint();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void showArticlePage() {
        ArticlePage articlePage = new ArticlePage(app, dbManager);
        app.frame.getContentPane().removeAll();
        app.frame.add(articlePage);
        app.frame.revalidate();
        app.frame.repaint();
    }
}

