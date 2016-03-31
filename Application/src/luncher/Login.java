package luncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        JTextField area_username = new JTextField(" Pseudonyme -> ");
        JTextField area_passowrd = new JTextField(" Mot de passe -> ");
        final JTextField field_username = new JTextField("");
        final JPasswordField field_password = new JPasswordField("");

        //Tableaux contenant tous les objets
        JButton[] buttons = {button_back, button_login};
        JTextField[] textFields = {area_username, field_username, area_passowrd, field_password};

        //Positionneur
        GridLayout main_button_positionner = new GridLayout(3, 2, SPACE_BETWEEN, SPACE_BETWEEN);

        //Verouillage des texte d'informations
        area_username.setEditable(false);
        area_passowrd.setEditable(false);
        area_username.setFocusable(false);
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
                connection();
            }
        });

        field_username.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (email_clicked == false) {
                    field_username.setText("");
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

    public static void connection() {

        String host = "mysql.alwaysdata.com";
        //String host = "mysql1.alwaysdata.com";
        String dbname = "elekhyr_sokoditor";
        String username = "elekhyr_reader";
        String password = "reader";
        String port = "3306";

        //String URL = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "";
        String URL = "jdbc:mysql://" + host + "/" + dbname + "";

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        System.out.println("host : " + host);
        System.out.println("dbname : " + dbname);
        System.out.println("username : " + username);
        System.out.println("password : " + password);
        System.out.println("port : " + port);

        System.out.println("URL : " + URL);
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(URL, username, password);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}