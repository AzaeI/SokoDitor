package luncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JPanel {

    //Espace entre les boutons
    final static int SPACE_BETWEEN = 20;

    final static String text_button_back = new String("Retourner en arriere");
    final static String text_button_login = new String("Se Connecter");

    boolean email_clicked = false;
    boolean password_clicked = false;

    public Login() {
        final Login itself = this;

        //Initialisation des boutons
        JButton button_login = new JButton(text_button_login);
        JButton button_back = new JButton(text_button_back);

        //Initialisation des textes
        JTextField area_email = new JTextField(" Adresse électronique -> ");
        JTextField area_passowrd = new JTextField(" Mot de passe -> ");
        final JTextField field_email = new JTextField("");
        final JPasswordField field_password = new JPasswordField("");

        //Tableaux contenant tous les objets
        JButton[] buttons = {button_back, button_login};
        JTextField[] textFields = {area_email, field_email, area_passowrd, field_password};

        //Positionneur
        GridLayout main_button_positionner = new GridLayout(3, 2, SPACE_BETWEEN, SPACE_BETWEEN);

        //Verouillage des texte d'informations
        area_email.setEditable(false);
        area_passowrd.setEditable(false);
        area_email.setFocusable(false);
        area_passowrd.setFocusable(false);

        //Coloration et ajout des boutons dans la grille
        for (int i = 0; i < buttons.length; i++) {
            //Apparence des boutons
            buttons[i].setBackground(Screens.soko_button_background);
            buttons[i].setForeground(Screens.soko_foreground);
            buttons[i].setFont(Screens.font);

            //Grille
            main_button_positionner.addLayoutComponent("", buttons[i]);

            //Ajout des boutons
            this.add(buttons[i]);
        }

        for (int i = 0; i < textFields.length; i++) {
            //Apparence des boutons
            textFields[i].setBackground(Screens.soko_background);
            textFields[i].setForeground(Screens.soko_foreground);
            textFields[i].setFont(Screens.font);
            textFields[i].setHorizontalAlignment(JTextField.CENTER);

            //Grille
            main_button_positionner.addLayoutComponent("", textFields[i]);

            //Ajout des boutons
            this.add(textFields[i]);
        }

        //Actions
        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itself.setVisible(false);
                Screens.SetScreen(Screens.mainMenu);
            }
        });

        button_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Verification du format de l'email
                String email = field_email.getText();
                System.out.println("email : " + email);
                boolean is_email = isValidEmailAddress(email);
                if (!is_email) {
                    JOptionPane pane = new JOptionPane();
                    pane.showMessageDialog(null, "Vous n'avez pas ecris un email dans la case dédiée a l'email", "Oh mais quand meme !", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane jop1 = new JOptionPane();
                    jop1.showMessageDialog(null, "Format de l'entré pour email valide", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        field_email.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (email_clicked == false) {
                    field_email.setText("");
                    email_clicked = true;
                }
            }
        });

        field_password.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (password_clicked == false) {
                    field_password.setText("");
                    password_clicked = true;
                }
            }
        });

        //Coloration du fond du panel
        this.setBackground(Screens.soko_menu_background);

        //Choix du positionneur
        this.setLayout(main_button_positionner);

        //End
        this.setVisible(true);
    }

    public static boolean isValidEmailAddress(String email) {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        System.out.println(email + " : " + matcher.matches());

        return matcher.matches();
    }
}