package launcher;

import storage.bean.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static launcher.ComponentSettings.*;

public class MainFrame extends JFrame {

    private static boolean isConnected = false;
    private static User user = null;

    private static JPanel cards = new JPanel(new CardLayout());

    public MainFrame() throws IOException {
        super("SokoDitor");
        this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        this.setLocationRelativeTo(null);

        cards.add(new Menu(cards), MENU_TITLE);
        cards.add(new Player(cards), PLAYER_TITLE);
        cards.add(new Editor(cards), ComponentSettings.EDITOR_TITLE);
        cards.add(new Downloader(cards), ComponentSettings.DOWNLOADER_TITLE);
        cards.add(new Login(cards), ComponentSettings.LOGIN_TITLE);
        cards.add(new Signup(cards), ComponentSettings.SIGNUP_TITLE);
        cards.add(new Settings(cards), ComponentSettings.SETTINGS_TITLE);
        cards.add(new ModifyMap(cards), "Modifier Map");



        this.setContentPane(cards);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
    }


    public static boolean isConnected() {
        return isConnected;
    }

    public static void setConnected() {
        isConnected = !isConnected;
    }


    public static User getUser() {
        return user;
    }
    public static void setUser(User u) {
        user = u;
    }

}