package launcher;

import storage.bean.User;
import storage.dao.DAO;
import storage.dao.factory.DAOFactory;
import storage.dao.factory.FactoryType;
import storage.dao.mysql.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JPanel {

    private final static String text_button_back = "Retourner en arriere";
    private final static String text_button_login = "Se Connecter";

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login(JPanel cards) {
        CardLayout cl = (CardLayout) cards.getLayout();

        //Initialisation des boutons
        JButton loginButton     = new JButton(text_button_login);
        JButton backButton      = new JButton();
        JButton signupButton    = new JButton("Signup");

        //Initialisation des textes
        JLabel usernameArea = new JLabel("Nom d'utilisateur ");
        JLabel passwordArea = new JLabel("Mot de passe");
        usernameField = new JTextField("");
        passwordField = new JPasswordField("");

        backButton.setOpaque(false);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setPreferredSize(new Dimension(usernameArea.getMinimumSize()));
        backButton.setHorizontalAlignment(SwingConstants.LEFT);
        Image img = new ImageIcon("Icones/back.ico").getImage().getScaledInstance(20,20, Image.SCALE_AREA_AVERAGING);
        backButton.setIcon(new ImageIcon(img));

        passwordArea.setPreferredSize(usernameArea.getMinimumSize());

        usernameField.setPreferredSize(new Dimension(200,20));
        passwordField.setPreferredSize(new Dimension(200,20));

        usernameArea.setHorizontalAlignment(SwingConstants.LEFT);
        passwordArea.setHorizontalAlignment(SwingConstants.LEFT);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        int i = 0;
        int j = 0;

        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        gbc.gridx = i;
        gbc.gridy = j++;
        this.add(backButton, gbc);

        gbc.gridx = i++;
        gbc.gridy = j;
        this.add(usernameArea, gbc);

        gbc.gridx = i--;
        gbc.gridy = j++;
        this.add(usernameField, gbc);

        gbc.gridx = i++;
        gbc.gridy = j;
        this.add(passwordArea, gbc);

        gbc.gridx = i--;
        gbc.gridy = j++;
        this.add(passwordField, gbc);

        gbc.gridx = i++;
        gbc.gridy = j;
        this.add(signupButton, gbc);

        gbc.gridx = i;
        gbc.gridy = j;
        this.add(loginButton, gbc);

        backButton.addActionListener(e -> cl.show(cards, ComponentSettings.MENU_TITLE));

        signupButton.addActionListener(e -> cl.show(cards, ComponentSettings.SIGNUP_TITLE));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                login(usernameField.getText(), String.valueOf(passwordField.getPassword()));
            }
        });


        this.setVisible(true);
    }



    private void login(String mail, String password){
        if(isEmailAddressValid(mail)){
            if (password.length() >= 6 && password.length() <= 16){

                byte[] bytesOfMessage;
                MessageDigest md;
                byte[] thedigest;
                User user;

                try {
                    bytesOfMessage = password.getBytes("UTF-8");
                    md = MessageDigest.getInstance("MD5");
                    thedigest = md.digest(bytesOfMessage);

                    user = (User) DAOFactory.getFactory(FactoryType.MYSQL_DAO).getUserDAO().get(0);

                    if(user.getPassword().equals(Arrays.toString(thedigest))){

                    }
                    else {

                    }

                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                    e.printStackTrace();

                }

            }
            else{
                passwordField.setText("Le mot de passe est incorrect");
                passwordField.setEchoChar((char) 0);
                passwordField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        passwordField.setEchoChar((char) 8226);
                        passwordField.setText("");
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                        passwordField.removeFocusListener(this);
                    }
                });
            }
        }
        else{
            usernameField.setText("L'adresse mail est incorrecte");
            usernameField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    usernameField.setText("");
                }
                @Override
                public void focusLost(FocusEvent e) {
                    usernameField.removeFocusListener(this);
                }
            });
        }
    }

    private boolean isEmailAddressValid(String email) {
        String regex = "[a-zA-Z.]+@[a-zA-Z]+[.][a-zA-Z]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        System.out.println(email + " : " + matcher.matches());

        return matcher.matches();
    }

}