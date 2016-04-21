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

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login(JPanel cards) {
        CardLayout cl = (CardLayout) cards.getLayout();

        this.setBackground(ComponentSettings.BACKGROUND_COLOR);

        //Initialisation des boutons
        JButton loginButton     = new JButton(ComponentSettings.LOGIN_BUTTON_TEXT);
        JButton backButton      = new JButton();
        JButton signupButton    = new JButton(ComponentSettings.GO_SIGNUP_BUTTON_TEXT);

        //Initialisation des textes
        JLabel usernameLabel = new JLabel(ComponentSettings.USERNAME_LABEL);
        JLabel passwordLabel = new JLabel(ComponentSettings.PASSWORD_LABEL);
        usernameField = new JTextField("");
        passwordField = new JPasswordField("");

        usernameLabel.setFont(ComponentSettings.FONT);
        passwordLabel.setFont(ComponentSettings.FONT);

        ComponentSettings.initializeDefaultButton(signupButton);
        ComponentSettings.initializeDefaultButton(loginButton);
        ComponentSettings.initializeBackButton(backButton);

        passwordLabel.setPreferredSize(usernameLabel.getMinimumSize());

        usernameField.setPreferredSize(new Dimension(200,20));
        passwordField.setPreferredSize(new Dimension(200,20));

        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        int i = 0;
        int j = 0;

        gbc.gridheight = 1;
        gbc.gridwidth = 1;


        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = i;
        gbc.gridy = j++;
        this.add(backButton, gbc);

        gbc.gridx = i++;
        gbc.gridy = j;
        this.add(usernameLabel, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = i--;
        gbc.gridy = j++;
        this.add(usernameField, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = i++;
        gbc.gridy = j;
        this.add(passwordLabel, gbc);


        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = i--;
        gbc.gridy = j++;
        this.add(passwordField, gbc);


        gbc.anchor = GridBagConstraints.WEST;
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
                if (login(usernameField.getText(), String.valueOf(passwordField.getPassword())))
                    cl.show(cards, ComponentSettings.MENU_TITLE);
            }
        });

        this.setVisible(true);
    }

    private boolean login(String username, String password){
        if(username.length() >= 6 && username.length() <= 16 && isAlphanum(username)){
            if (password.length() >= 6 && password.length() <= 16){

                byte[] bytesOfMessage;
                MessageDigest md;
                byte[] thedigest;
                User user = new User();

                try {
                    bytesOfMessage = password.getBytes("UTF-8");
                    md = MessageDigest.getInstance("MD5");
                    thedigest = md.digest(bytesOfMessage);
                    StringBuilder hashString = new StringBuilder();
                    for (byte aThedigest : thedigest) {
                        String hex = Integer.toHexString(aThedigest);
                        if (hex.length() == 1) {
                            hashString.append('0');
                            hashString.append(hex.charAt(hex.length() - 1));
                        } else
                            hashString.append(hex.substring(hex.length() - 2));
                    }
                    user.setUsername(username);
                    user = DAOFactory.getFactory(FactoryType.MYSQL_DAO).getUserDAO().get(user);

                    if(user.getPassword().equals(hashString.toString())){
                        if(!MainFrame.isConnected())
                            MainFrame.setConnected();
                            MainFrame.setUser(user);
                        return true;
                    }
                    else {
                        erreur(passwordField);
                    }

                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                    e.printStackTrace();

                    erreur(passwordField);
                }

            }
            else{
                erreur(passwordField);
            }
        }
        else{
            usernameField.setText("Le nom d'utilisateur est incorrect");
            usernameField.setForeground(Color.red);
            usernameField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {

                    usernameField.setForeground(null);
                    usernameField.setText("");
                }
                @Override
                public void focusLost(FocusEvent e) {
                    usernameField.removeFocusListener(this);
                }
            });
        }
        return false;
    }

    private void erreur(JTextField field){

        passwordField.setText("Le mot de passe est incorrect");
        passwordField.setEchoChar((char) 0);

        passwordField.setForeground(Color.red);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setEchoChar((char) 8226);

                usernameField.setForeground(null);
                passwordField.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                passwordField.removeFocusListener(this);
            }
        });
    }

    private boolean isAlphanum(String s){
        String regex = "[a-zA-Z0-9/éèêà ]*";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(s);

        return matcher.matches();
    }

}