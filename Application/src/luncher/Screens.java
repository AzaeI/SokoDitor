package luncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screens extends JFrame {

    final static int X_SIZE = 600;
    final static int Y_SIZE = 600;

    final static Font font = new Font("Calibri light", Font.BOLD, 20);

    final static Color soko_background = Color.BLUE;
    final static Color soko_button_background = Color.GRAY;
    final static Color soko_font_color = Color.cyan;
    final static Color soko_menu_background = Color.black;
    final static Color soko_foreground = Color.white;

    final static JFrame mainFrame = new JFrame();
    static MainMenu mainMenu = new MainMenu();
    static Login login = new Login();
    static Settings settings = new Settings();
    static Play play = new Play();


    public Screens() {
        mainFrame.setTitle("SokoDitor");
        mainFrame.setSize(X_SIZE, Y_SIZE);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.setContentPane(mainMenu);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public static void SetScreen(JPanel panel) {
        mainFrame.setContentPane(panel);
        panel.setVisible(true);
    }



}