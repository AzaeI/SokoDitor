package luncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {

    //Espace entre les boutons
    final static int SPACE_BETWEEN = 50;

    final static String text_button_back = new String("Retourner en arriere");
    final static String text_button_login = new String("Se Connecter");

    public Login() {
        final Login itself = this;

        //Initialisation des boutons
        JButton button_login = new JButton(text_button_login);
        JButton button_back = new JButton(text_button_back);

        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itself.setVisible(false);
                Screens.SetScreen(Screens.mainMenu);
            }
        });

        //Initialisation des textes
        JTextArea area_email  = new JTextArea("Email : ");
        JTextArea area_passowrd = new JTextArea("Mot de passe : ");
        JTextArea field_email = new JTextArea("Entrer votre email");
        JTextArea field_password = new JTextArea("Entrer votre mot de passe ici");

        //Tableaux contenant tous les objets
        JButton buttons[] = {button_back};
        JTextArea textAreas[] = {area_email,area_passowrd,field_email,  field_password };

        //Positionneur
        GridLayout main_button_positionner = new GridLayout(2, 2, SPACE_BETWEEN, SPACE_BETWEEN);

        //Verouillage des texte d'informations
        area_email.setEditable(false);
        area_passowrd.setEditable(false);

        //Coloration et ajout des boutons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(Color.white);
            buttons[i].setForeground(Color.GREEN);
            buttons[i].setFont(Screens.font);
            this.add(buttons[i]);
        }

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(Color.white);
            buttons[i].setForeground(Color.GREEN);
            buttons[i].setFont(Screens.font);
            this.add(buttons[i]);
        }

        //Coloration du fond du panel
        this.setBackground(Color.darkGray);

        //End
        this.setVisible(true);
    }
}