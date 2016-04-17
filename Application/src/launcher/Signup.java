package launcher;

import storage.bean.RecoveryQuestion;
import storage.bean.User;
import storage.dao.DAO;
import storage.dao.factory.DAOFactory;
import storage.dao.factory.FactoryType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by elekhyr on 31/03/16.
 */
public class Signup extends JPanel{

    private JTextField emailField = new JTextField("");
    private JTextField usernameField = new JTextField("");
    private JPasswordField passwordField = new JPasswordField("");
    private JPasswordField verificationField = new JPasswordField("");
    private JTextField question1Field = new JTextField();
    private JTextField question2Field = new JTextField();

    public Signup(JPanel cards) {

        CardLayout cl = (CardLayout) cards.getLayout();
        this.setBackground(Color.black);

        //Initialisation des boutons
        JButton signupButton = new JButton(ComponentSettings.SIGNUP_BUTTON_TEXT);
        JButton backButton = new JButton();

        ComponentSettings.initializeDefaultButton(signupButton);
        ComponentSettings.initializeBackButton(backButton);

        //Initialisation des textes
        JLabel emailLabel = new JLabel("Adresse electronique");
        JLabel usernameLabel = new JLabel("Nom d'utilisateur");
        JLabel passwordLabel = new JLabel("Mot de passe");
        JLabel verificationLabel = new JLabel("Verification");




        JComboBox<String> questions = new JComboBox<>(ComponentSettings.RECOVERY_QUESTIONS);
        JComboBox<String> questionsBis = new JComboBox<>(ComponentSettings.RECOVERY_QUESTIONS);;


        usernameLabel.setFont(ComponentSettings.FONT);
        passwordLabel.setFont(ComponentSettings.FONT);
        verificationLabel.setFont(ComponentSettings.FONT);
        emailLabel.setFont(ComponentSettings.FONT);
        questionsBis.setOpaque(false);

        passwordLabel.setPreferredSize(emailLabel.getMinimumSize());
        verificationLabel.setPreferredSize(emailLabel.getMinimumSize());
        emailLabel.setPreferredSize(emailLabel.getMinimumSize());
        questions.setPreferredSize(emailLabel.getMinimumSize());
        questionsBis.setPreferredSize(emailLabel.getMinimumSize());



        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        verificationLabel.setHorizontalAlignment(SwingConstants.LEFT);


        usernameField.setPreferredSize(new Dimension(200,20));
        emailField.setPreferredSize(new Dimension(200,20));
        passwordField.setPreferredSize(new Dimension(200,20));
        verificationField.setPreferredSize(new Dimension(200,20));
        question1Field.setPreferredSize(new Dimension(200,20));
        question2Field.setPreferredSize(new Dimension(200,20));


        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

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
        this.add(emailLabel, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = i--;
        gbc.gridy = j++;
        this.add(emailField, gbc);

        gbc.anchor = GridBagConstraints.WEST;
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
        this.add(verificationLabel, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = i--;
        gbc.gridy = j++;
        this.add(verificationField, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = i++;
        gbc.gridy = j;
        this.add(questions, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = i--;
        gbc.gridy = j++;
        this.add(question1Field, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = i++;
        gbc.gridy = j;
        this.add(questionsBis, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = i--;
        gbc.gridy = j++;
        this.add(question2Field, gbc);


        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = i++;
        gbc.gridy = j;
        this.add(signupButton, gbc);

        gbc.gridx = i;
        gbc.gridy = j;


        backButton.addActionListener(e -> cl.show(cards, ComponentSettings.LOGIN_TITLE));
        signupButton.addActionListener(e -> signup(usernameField.getText(), String.valueOf(passwordField.getPassword()), emailField.getText(),
                String.valueOf(verificationField.getPassword()), questions.getSelectedItem().toString(),
                questionsBis.getSelectedItem().toString(), question1Field.getText(), question2Field.getText()));

        this.setVisible(true);
    }

    private void signup(String username, String password, String mail, String verification, String question1,
                        String question2, String response1, String response2){

        if(isEmailAddressValid(mail)){
            if(username.length() >= 6 && username.length() <= 32){
                if (password.length() >= 6 && password.length() <= 16){
                    if(password.equals(verification)){
                        if(response1.length() > 1 && isAlphanum(response1)){
                            if(response2.length() > 1 && isAlphanum(response2)){
                                byte[] bytesOfMessage;
                                MessageDigest md;
                                byte[] thedigest;
                                System.out.print("connecté");
                                try {
                                    bytesOfMessage = password.getBytes("UTF-8");
                                    md = MessageDigest.getInstance("MD5");
                                    thedigest = md.digest(bytesOfMessage);
                                    User u = new User();
                                    u.setAccount_activated(false);
                                    u.setMail(mail);
                                    u.setUsername(username);
                                    u.setPassword(Arrays.toString(thedigest));


                                    /*if((u = DAOFactory.getFactory(FactoryType.MYSQL_DAO).getUserDAO().create(u); != null){

                                    }
                                    else{

                                        RecoveryQuestion recoveryQuestion1 = new RecoveryQuestion();
                                        RecoveryQuestion recoveryQuestion2 = new RecoveryQuestion();
                                        recoveryQuestion1.setUser_id(u.getId());
                                        recoveryQuestion1.setQuestion(question1);
                                        recoveryQuestion1.setAnswer(response1);
                                        recoveryQuestion2.setUser_id(u.getId());
                                        recoveryQuestion2.setQuestion(question2);
                                        recoveryQuestion2.setAnswer(response2);
                                    }*/
                                    u = DAOFactory.getFactory(FactoryType.MYSQL_DAO).getUserDAO().create(u);
                                    System.out.print(u);
                                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                                    e.printStackTrace();

                                }
                            }
                            else {
                                question2Field.setText("Réponse non valide");
                                question2Field.addFocusListener(new FocusListener() {
                                    @Override
                                    public void focusGained(FocusEvent e) {
                                        question2Field.setText("");
                                    }

                                    @Override
                                    public void focusLost(FocusEvent e) {
                                        question2Field.removeFocusListener(this);
                                    }
                                });
                            }
                        }
                        else{
                            question1Field.setText("Réponse non valide");
                            question1Field.addFocusListener(new FocusListener() {
                                @Override
                                public void focusGained(FocusEvent e) {
                                    question1Field.setText("");
                                }
                                @Override
                                public void focusLost(FocusEvent e) {
                                    question1Field.removeFocusListener(this);
                                }
                            });
                        }
                    }
                    else{
                        verificationField.setText("Le mot de passe ne correspond pas");
                        verificationField.setEchoChar((char) 0);
                        verificationField.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                verificationField.setEchoChar((char) 8226);
                                verificationField.setText("");
                            }
                            @Override
                            public void focusLost(FocusEvent e) {
                                verificationField.removeFocusListener(this);
                            }
                        });
                    }
                }
                else {
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
                usernameField.setText("Le nom d'utilisateur est incorrect");
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
        else{
            emailField.setText("L'adresse mail est incorrecte");
            emailField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    emailField.setText("");
                }
                @Override
                public void focusLost(FocusEvent e) {
                    emailField.removeFocusListener(this);
                }
            });
        }
    }

    private boolean isAlphanum(String s){
        String regex = "[a-zA-Z0-9/éèêà ]*";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(s);

        return matcher.matches();
    }
    private boolean isEmailAddressValid(String email) {
        String regex = "[a-zA-Z.]+@[a-zA-Z]+[.][a-zA-Z]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
