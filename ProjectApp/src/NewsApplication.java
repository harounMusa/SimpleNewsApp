import javax.swing.*;

public class NewsApplication {
    JFrame frame;
    DatabaseManager dbManager;

    public NewsApplication() {
        dbManager = new DatabaseManager();
        frame = new JFrame("News Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 430);
        showLoginPage();
        frame.setVisible(true);
    }

    private void showLoginPage() {
        LoginPage loginPage = new LoginPage(this);
        frame.getContentPane().removeAll();
        frame.add(loginPage);
        frame.revalidate();
        frame.repaint();
    }

    public void showMainPage() {
        MainPage mainPage = new MainPage(this, dbManager);
        frame.getContentPane().removeAll();
        frame.add(mainPage);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewsApplication::new);
    }
}


