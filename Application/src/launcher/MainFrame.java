package launcher;

import javax.swing.*;
import java.awt.*;

import static launcher.ComponentSettings.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("SokoDitor");
        this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        this.setLocationRelativeTo(null);

        JPanel cards = new JPanel(new CardLayout());
        cards.add(new Menu(cards), MENU_TITLE);
        cards.add(new Player(cards), PLAYER_TITLE);
        cards.add(new Editor(cards), ComponentSettings.EDITOR_TITLE);
        /*cards.add(new Login(cards), ComponentSettings.LOGIN_TITLE);
        cards.add(new Signup(cards), ComponentSettings.SIGNUP_TITLE);*/
        cards.add(new Settings(cards), ComponentSettings.SETTINGS_TITLE);

        this.setContentPane(cards);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
    }

}