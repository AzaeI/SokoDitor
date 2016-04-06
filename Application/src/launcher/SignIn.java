package launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends JPanel {

    //Espace entre les boutons
    final static int SPACE_BETWEEN = 20;

    final static String text_button_back = new String("Retourner en arriere");
    final static String text_button_signIn = new String("S'inscrire");

    boolean email_clicked = false;
    boolean password_clicked = false;

    public SignIn() {
        final SignIn itself = this;

        //Initialisation des boutons
        JButton button_signIn = new JButton(text_button_signIn);
        JButton button_back = new JButton(text_button_back);

        //Initialisation des textes
        JTextField area_email = new JTextField(" Adresse électronique -> ");
        JTextField area_user = new JTextField(" Nom d'utilisateur -> ");
        JTextField area_passowrd = new JTextField(" Mot de passe -> ");
        JTextField area_passowrd2 = new JTextField(" Verification mot de passe -> ");
        final JTextField field_email = new JTextField("");
        final JTextField field_user = new JTextField("");
        final JPasswordField field_password = new JPasswordField("");
        final JPasswordField field_password2 = new JPasswordField("");

        //Tableaux contenant tous les objets
        JButton[] buttons = {button_back, button_signIn};
        JTextField[] textFields = {area_email, field_email, area_user, field_user, area_passowrd, field_password, area_passowrd2, field_password2};

        //Positionneur
        GridLayout main_button_positionner = new GridLayout((buttons.length + textFields.length) / 2, 2, SPACE_BETWEEN, SPACE_BETWEEN);

        //Verouillage des texte d'informations
        area_email.setEditable(false);
        area_user.setEditable(false);
        area_passowrd.setEditable(false);
        area_passowrd2.setEditable(false);
        area_email.setFocusable(false);
        area_user.setFocusable(false);
        area_passowrd.setFocusable(false);
        area_passowrd2.setFocusable(false);

        //Coloration et ajout des boutons dans la grille
        for (int i = 0; i < buttons.length; i++) {
            //Apparence des boutons
           // buttons[i].setBackground(MainFrame.soko_button_background);
            //buttons[i].setForeground(MainFrame.soko_foreground);
            //buttons[i].setFont(MainFrame.font);

            //Grille
            main_button_positionner.addLayoutComponent("", buttons[i]);

            //Ajout des boutons
            this.add(buttons[i]);
        }

        for (int i = 0; i < textFields.length; i++) {
            //Apparence des boutons
            //textFields[i].setBackground(MainFrame.soko_background);
            //textFields[i].setForeground(MainFrame.soko_foreground);
            //textFields[i].setFont(MainFrame.font);
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
              //  MainFrame.SetScreen(MainFrame.mainMenu);
            }
        });

        button_signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Recuperation des informations
                String email = field_email.getText();
                String password = field_password.getText();
                String password2 = field_password2.getText();
                System.out.println("email : " + email);
                System.out.println("password : " + password);
                System.out.println("password2 : " + password2);
                //Verification du format de l'email
                boolean is_email = isValidEmailAddress(email);
                if (!is_email) {
                    JOptionPane pane = new JOptionPane();
                    pane.showMessageDialog(null, "Vous n'avez pas ecris un email dans la case dédiée a l'email", "Oh mais quand meme !", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane jop1 = new JOptionPane();
                    jop1.showMessageDialog(null, "Format de l'entré pour email valide", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                //Verification des mots de passe
                if (!password.equals(password2)) {
                    JOptionPane pane = new JOptionPane();
                    pane.showMessageDialog(null, "Les deux mots de passe sont differents !", "Oh mais quand meme !", JOptionPane.ERROR_MESSAGE);
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
        //this.setBackground(MainFrame.soko_menu_background);

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