package launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Menu extends JPanel {

    private final static int SPACE_BETWEEN = 30;

    Menu(JPanel cards) {

        CardLayout cl = (CardLayout) cards.getLayout();

        //Initialisation des boutons
        JButton playButton      = new JButton(ComponentSettings.PLAY_BUTTON_TEXT);
        JButton editorButton    = new JButton(ComponentSettings.EDITOR_BUTTON_TEXT);
        JButton signupButton    = new JButton(ComponentSettings.SIGNUP_BUTTON_TEXT);
        JButton loginButton     = new JButton(ComponentSettings.LOGIN_BUTTON_TEXT);
        JButton settingsButton  = new JButton(ComponentSettings.SETTINGS_TITLE);
        JButton exitButton     = new JButton(ComponentSettings.EXIT_BUTTON_TEXT);

        //Tableau contenant tous les boutons
        JButton buttons[] = { playButton, editorButton, signupButton, loginButton, settingsButton, exitButton };

        //Coloration et insertion des boutons dans la grille
        for (JButton button : buttons) {
            button.setForeground(ComponentSettings.BUTTON_BACKGROUND_COLOR);
            button.setPreferredSize(new Dimension(500, 100));
            button.setFont(ComponentSettings.FONT);
            button.setHorizontalTextPosition(SwingConstants.LEFT);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setForeground(ComponentSettings.FOREGROUND_COLOR);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setForeground(ComponentSettings.BUTTON_BACKGROUND_COLOR);
                }
            });
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        /********************************************************
         *                      PLAY BUTTON
         ********************************************************/

        gbc.gridy = 0;
        //La taille en hauteur et en largeur
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(playButton, gbc);


        /********************************************************
         *                      EDITOR BUTTON
         ********************************************************/

        gbc.gridy = 1;
        //La taille en hauteur et en largeur
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(editorButton, gbc);


        /********************************************************
         *                      SIGNUP BUTTON
         ********************************************************/

        gbc.gridy = 2;
        //La taille en hauteur et en largeur
        this.add(signupButton, gbc);


        /********************************************************
         *                      LOGIN BUTTON
         ********************************************************/

        gbc.gridy = 3;
        //La taille en hauteur et en largeur
        this.add(loginButton, gbc);


        /********************************************************
         *                      SETTINGS BUTTON
         ********************************************************/

        gbc.gridy = 4;
        //La taille en hauteur et en largeur
        this.add(settingsButton, gbc);


        /********************************************************
         *                      EXIT BUTTON
         ********************************************************/

        gbc.gridy = 5;
        //La taille en hauteur et en largeur
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(exitButton, gbc);



        //Coloration du fond du Menu
        this.setBackground(ComponentSettings.MENU_BACKGROUND);


        //Actions Listeners
        settingsButton.addActionListener(e -> cl.show(cards, ComponentSettings.SETTINGS_TITLE));

        loginButton.addActionListener(e -> cl.show(cards, ComponentSettings.SETTINGS_TITLE));

        signupButton.addActionListener(e -> cl.show(cards, ComponentSettings.SIGNUP_TITLE));

        playButton.addActionListener(e -> cl.show(cards, ComponentSettings.PLAYER_TITLE));

        editorButton.addActionListener(e -> cl.show(cards, ComponentSettings.EDITOR_TITLE));

        exitButton.addActionListener(e -> System.exit(0));

        this.setVisible(true);
    }


    private void layoutInit(){

        JButton b1 = new JButton("Salut");
        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        b1.setContentAreaFilled(true);

        JButton b2 = new JButton("Salut >2");
        b2.setBorderPainted(false);
        b2.setFocusPainted(false);
        b2.setContentAreaFilled(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        //La taille en hauteur et en largeur
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(b1, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        //La taille en hauteur et en largeur
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(b2, gbc);
        //---------------------------------------------
        /*gbc.gridx = 1;
        content.add(cell2, gbc);
        //---------------------------------------------
        gbc.gridx = 2;
        content.add(cell3, gbc);
        //---------------------------------------------
        //Cette instruction informe le layout que c'est une fin de ligne
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 3;
        content.add(cell4, gbc);
        //---------------------------------------------
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        //Celle-ci indique que la cellule se réplique de façon verticale
        gbc.fill = GridBagConstraints.VERTICAL;
        content.add(cell5, gbc);
        //---------------------------------------------
        gbc.gridx = 1;
        gbc.gridheight = 1;
        //Celle-ci indique que la cellule se réplique de façon horizontale
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        content.add(cell6, gbc);
        //---------------------------------------------
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        content.add(cell7, gbc);
        //---------------------------------------------
        gbc.gridx = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        content.add(cell8, gbc);*/
    }
}
