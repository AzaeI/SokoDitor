package luncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    final static int SPACE_BETWEEN = 30;

    final static String text_button_play = new String("Jouer");
    final static String text_button_editor = new String("Editeur");
    final static String text_button_login = new String("Se connecter");
    final static String text_button_settings = new String("Options");

    public MainMenu() {

        final MainMenu itself = this;

        //Initialisation des boutons
        JButton button_play = new JButton(text_button_play);
        JButton button_editor = new JButton(text_button_editor);
        JButton button_login = new JButton(text_button_login);
        JButton button_settings = new JButton(text_button_settings);

        //Tableau contenant tout les boutons
        JButton buttons[] = {button_play, button_editor,button_login ,button_settings};

        //Positionneur
        GridLayout main_button_positionner = new GridLayout(4, 1, SPACE_BETWEEN, SPACE_BETWEEN);

        //Coloration et insertion des boutons dans la grille
        for (int i = 0; i < buttons.length; i++) {

            //Coloration
            buttons[i].setBackground(Screens.soko_button_background);
            buttons[i].setForeground(Screens.soko_foreground);
            buttons[i].setFont(Screens.font);

            //Grille
            main_button_positionner.addLayoutComponent("", buttons[i]);

            //Ajout
            this.add(buttons[i]);
        }

        //Coloration du fond du MainMenu
        this.setBackground(Screens.soko_menu_background);

        //Connection
        this.setLayout(main_button_positionner);

        //Actions Listeners
        button_settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itself.setVisible(false);
                Screens.SetScreen(Screens.settings);
            }
        });

        button_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itself.setVisible(false);
                Screens.SetScreen(Screens.login);
            }
        });

        button_play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itself.setVisible(false);
                Screens.SetScreen(Screens.play);
            }
        });

        //End
        this.setVisible(true);
    }
}
