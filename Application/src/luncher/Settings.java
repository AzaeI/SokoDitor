package luncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JPanel {

    //Espace entre les boutons
    final static int SPACE_BETWEEN = 50;

    final static String text_button_back = new String("Retourner en arriere");

    public Settings() {
        final Settings itself = this;

        //Initialisation des boutons
        JButton button_back = new JButton(text_button_back);

        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itself.setVisible(false);
                Screens.SetScreen(Screens.mainMenu);
            }
        });

        //Tableau contenant tout les boutons
        JButton buttons[] = {button_back};

        //Coloration des boutons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(Color.white);
            buttons[i].setForeground(Color.GREEN);
            buttons[i].setFont(Screens.font);
        }

        //Coloration du fond du panel
        this.setBackground(Color.darkGray);

        //Ajout des boutons du fond du panel
        this.add(button_back);

        //End
        this.setVisible(true);
    }
}