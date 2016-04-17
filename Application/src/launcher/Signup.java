package launcher;

import storage.bean.RecoveryQuestion;
import storage.bean.User;
import storage.dao.factory.DAOFactory;
import storage.dao.factory.FactoryType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends JPanel{

    private JTextField usernameField = new JTextField("");
    private JPasswordField passwordField = new JPasswordField("");
    private JPasswordField verificationField = new JPasswordField("");
    private JTextField question1Field = new JTextField();
    private JTextField question2Field = new JTextField();
    private JLabel error = new JLabel("");

    public Signup(JPanel cards) {

        CardLayout cl = (CardLayout) cards.getLayout();
        this.setBackground(Color.black);

        //Initialisation des boutons
        JButton signupButton = new JButton(ComponentSettings.SIGNUP_BUTTON_TEXT);
        JButton backButton = new JButton();

        ComponentSettings.initializeDefaultButton(signupButton);
        ComponentSettings.initializeBackButton(backButton);

        JLabel usernameLabel = new JLabel("Nom d'utilisateur");
        JLabel passwordLabel = new JLabel("Mot de passe");
        JLabel verificationLabel = new JLabel("Verification");

        error.setHorizontalAlignment(SwingConstants.RIGHT);
        error.setForeground(Color.red);
        error.setSize(200,200);



        JComboBox<String> questions = new JComboBox<>(ComponentSettings.RECOVERY_QUESTIONS);
        JComboBox<String> questionsBis = new JComboBox<>(ComponentSettings.RECOVERY_QUESTIONS);


        usernameLabel.setFont(ComponentSettings.FONT);
        passwordLabel.setFont(ComponentSettings.FONT);
        verificationLabel.setFont(ComponentSettings.FONT);
        questionsBis.setOpaque(false);

        passwordLabel.setPreferredSize(usernameLabel.getMinimumSize());
        verificationLabel.setPreferredSize(usernameLabel.getMinimumSize());
        questions.setPreferredSize(usernameLabel.getMinimumSize());
        questionsBis.setPreferredSize(usernameLabel.getMinimumSize());



        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        verificationLabel.setHorizontalAlignment(SwingConstants.LEFT);


        usernameField.setPreferredSize(new Dimension(200,20));
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
        gbc.gridx = i++;
        gbc.gridy = j;
        this.add(backButton, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = i;
        gbc.gridy = j++;
        this.add(error, gbc);

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
        signupButton.addActionListener(e -> signup(usernameField.getText(), String.valueOf(passwordField.getPassword()),
                String.valueOf(verificationField.getPassword()), questions.getSelectedItem().toString(),
                questionsBis.getSelectedItem().toString(), question1Field.getText(), question2Field.getText()));

        this.setVisible(true);
    }

    private void signup(String username, String password, String verification, String question1,
                        String question2, String response1, String response2){


        if(username.length() >= 6 && username.length() <= 32 && isAlphanum(username)) {
            if (password.length() >= 6 && password.length() <= 16) {
                if (password.equals(verification)) {
                    if (!question1.equals(question2)) {
                        if (response1.length() > 1 && isAlphanum(response1)) {
                            if (response2.length() > 1 && isAlphanum(response2)) {
                                byte[] bytesOfMessage;
                                MessageDigest md;
                                byte[] thedigest;
                                try {
                                    bytesOfMessage = password.getBytes();
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

                                    User u = new User();
                                    u.setAccount_activated(false);
                                    u.setUsername(username);
                                    u.setPassword(hashString.toString());


                                    if (DAOFactory.getFactory(FactoryType.MYSQL_DAO).getUserDAO().get(u) == null) {
                                        if ((u = DAOFactory.getFactory(FactoryType.MYSQL_DAO).getUserDAO().create(u)) == null) {
                                            error.setText("Erreur critique: Contacter l'administrateur");
                                        } else {
                                            RecoveryQuestion recoveryQuestion1 = new RecoveryQuestion();
                                            RecoveryQuestion recoveryQuestion2 = new RecoveryQuestion();
                                            recoveryQuestion1.setUser_id(u.getId());
                                            recoveryQuestion1.setQuestion(question1);
                                            recoveryQuestion1.setAnswer(response1);
                                            recoveryQuestion2.setUser_id(u.getId());
                                            recoveryQuestion2.setQuestion(question2);
                                            recoveryQuestion2.setAnswer(response2);
                                            if (DAOFactory.getFactory(FactoryType.MYSQL_DAO).getRecoveryQuestionDAO().create(recoveryQuestion1) == null ||
                                                    DAOFactory.getFactory(FactoryType.MYSQL_DAO).getRecoveryQuestionDAO().create(recoveryQuestion2) == null) {
                                                DAOFactory.getFactory(FactoryType.MYSQL_DAO).getRecoveryQuestionDAO().rollback();
                                                error.setText("Erreur critique: Contacter l'administrateur");

                                                System.out.print("CRASH");
                                            } else {
                                                DAOFactory.getFactory(FactoryType.MYSQL_DAO).getRecoveryQuestionDAO().commit();
                                            }
                                        }
                                    } else {
                                        usernameField.setText("Le nom d'utilisateur");
                                        erreur(usernameField);
                                    }

                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                    error.setText("Erreur critique: Contacter l'administrateur");
                                }
                            } else {
                                question2Field.setText("Réponse non valide");
                                erreur(question2Field);
                            }
                        } else {
                            question1Field.setText("Réponse non valide");
                            erreur(question1Field);
                        }
                    } else {
                        question2Field.setText("Les questions doivent être différentes");
                        erreur(question2Field);
                    }
                } else {
                    verificationField.setText("Le mot de passe ne correspond pas");
                    verificationField.setEchoChar((char) 0);
                    verificationField.setForeground(Color.red);
                    verificationField.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            verificationField.setForeground(null);
                            verificationField.setEchoChar((char) 8226);
                            verificationField.setText("");
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            verificationField.removeFocusListener(this);
                        }
                    });
                }
            } else {
                passwordField.setText("Le mot de passe est incorrect");
                passwordField.setForeground(Color.red);
                passwordField.setEchoChar((char) 0);
                passwordField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        passwordField.setEchoChar((char) 8226);
                        passwordField.setText("");
                        passwordField.setForeground(null);
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
            erreur(usernameField);
        }
    }

    private void erreur(JTextField field){

        field.setForeground(Color.red);
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                field.setForeground(null);
                field.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                field.removeFocusListener(this);
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
