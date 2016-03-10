package luncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JPanel {

    //Espace entre les boutons
    final static int SPACE_BETWEEN = 10;

    final static String text_button_back = new String("Retourner en arriere");
    final static String text_button_default = new String("Remmetre les valeurs par defaut");

    final static String text_button_sound = new String("Desactiver le son : ");

    final static String text_button_change_chara = new String("Changer de personage : ");
    final static String text_button_change_wall = new String("Changer la texture des murs : ");
    final static String text_button_change_box = new String("Changer la texture des caisses : ");
    final static String text_button_change_box_final = new String("Changer la texture des caisses en place : ");
    final static String text_button_change_final = new String("Changer la texture des emplacements pour caisses : ");
    final static String text_button_change_ground = new String("Changer la texture du sol : ");

    final static String text_button_new_mdp = new String("Changer de mot de passe : ");

    public Settings() {
        final Settings itself = this;

        //Initialisation des boutons
        JButton button_back = new JButton(text_button_back);
        JButton button_default = new JButton(text_button_default);

        JButton button_sound = new JButton(text_button_sound);

        JButton button_change_chara = new JButton(text_button_change_chara);
        JButton button_change_wall = new JButton(text_button_change_wall);
        JButton button_change_box = new JButton(text_button_change_box);
        JButton button_change_box_final = new JButton(text_button_change_box_final);
        JButton button_change_final = new JButton(text_button_change_final);
        JButton button_change_ground = new JButton(text_button_change_ground);

        JButton button_new_mdp = new JButton(text_button_new_mdp);

        //Actions des boutons
        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itself.setVisible(false);
                Screens.SetScreen(Screens.mainMenu);
            }
        });
        button_default.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        button_sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        button_change_chara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        button_change_wall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        button_change_box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        button_change_box_final.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        button_change_final.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        button_change_ground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        button_new_mdp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        //Tableau contenant tout les boutons
        JButton buttons[] = {button_back, button_default, button_sound, button_change_chara, button_change_wall, button_change_box, button_change_box_final, button_change_final, button_change_ground, button_new_mdp};

        //Positionneur
        GridLayout main_button_positionner = new GridLayout(buttons.length, 1, SPACE_BETWEEN, SPACE_BETWEEN);

        //Coloration des boutons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(Screens.soko_background);
            buttons[i].setForeground(Screens.soko_foreground);
            buttons[i].setFont(Screens.font);
            main_button_positionner.addLayoutComponent("", buttons[i]);
            this.add(buttons[i]);

        }

        //Connection
        this.setLayout(main_button_positionner);

        //Coloration du fond du panel
        this.setBackground(Screens.soko_menu_background);

        //End
        this.setVisible(true);
    }
}