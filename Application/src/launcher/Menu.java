package launcher;

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class Menu extends JPanel {

    JButton playButton          = new JButton(ComponentSettings.PLAY_BUTTON_TEXT);
    JButton editorButton        = new JButton(ComponentSettings.EDITOR_BUTTON_TEXT);
    JButton downloaderButton    = new JButton(ComponentSettings.DOWNLOADER_BUTTON_TEXT);
    JButton signupButton        = new JButton(ComponentSettings.SIGNUP_BUTTON_TEXT);
    JButton loginButton         = new JButton(ComponentSettings.LOGIN_BUTTON_TEXT);
    JButton settingsButton      = new JButton(ComponentSettings.SETTINGS_TITLE);
    JButton exitButton          = new JButton(ComponentSettings.EXIT_BUTTON_TEXT);
    JButton disconnectionButton = new JButton("Deconnexion");
    JPanel cards;

    Menu(JPanel cards) {
        this.cards = cards;
        CardLayout cl = (CardLayout) cards.getLayout();

        //Tableau contenant tous les boutons
        JButton buttons[] = { playButton, editorButton, downloaderButton, signupButton, loginButton, settingsButton, exitButton, disconnectionButton };

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


        int j = 0;

        /********************************************************
         *                      PLAY BUTTON
         ********************************************************/

        gbc.gridy = j++;
        this.add(playButton, gbc);


        /********************************************************
         *                      EDITOR BUTTON
         ********************************************************/

        gbc.gridy = j++;

        this.add(editorButton, gbc);

        /********************************************************
         *                      DOWNLOADER BUTTON
         ********************************************************/

        gbc.gridy = j++;
        this.add(downloaderButton, gbc);

        /********************************************************
         *                      LOGIN BUTTON
         ********************************************************/

        gbc.gridy = j++;
        //La taille en hauteur et en largeur
        this.add(loginButton, gbc);
        disconnectionButton.setVisible(false);
        this.add(disconnectionButton, gbc);


        /********************************************************
         *                      SETTINGS BUTTON
         ********************************************************/

        gbc.gridy = j++;
        //La taille en hauteur et en largeur
        this.add(settingsButton, gbc);


        /********************************************************
         *                      EXIT BUTTON
         ********************************************************/

        gbc.gridy = j;
        this.add(exitButton, gbc);



        //Coloration du fond du Menu
        this.setBackground(ComponentSettings.MENU_BACKGROUND);


        //Actions Listeners
        settingsButton.addActionListener(e -> cl.show(cards, ComponentSettings.SETTINGS_TITLE));

        loginButton.addActionListener(e -> cl.show(cards, ComponentSettings.LOGIN_TITLE));

        playButton.addActionListener(e -> cl.show(cards, ComponentSettings.PLAYER_TITLE));

        editorButton.addActionListener(e -> cl.show(cards, ComponentSettings.EDITOR_TITLE));

        downloaderButton.addActionListener(e -> cl.show(cards, ComponentSettings.DOWNLOADER_TITLE));

        exitButton.addActionListener(e -> System.exit(0));

        disconnectionButton.addActionListener(e -> {
            if(MainFrame.isConnected()){

                MainFrame.setConnected();
                this.setVisible(false);
                this.setVisible(true);
            }
        });
        this.addPropertyChangeListener(evt -> {

            if(evt.getPropertyName().equals("visible") && evt.getNewValue().equals(true)){
                if(MainFrame.isConnected()){
                    loginButton.setVisible(false);
                    disconnectionButton.setVisible(true);
                }
                else{
                    loginButton.setVisible(true);
                    disconnectionButton.setVisible(false);
                }
            }
        });

        this.setVisible(true);
    }

    public void setVisible(boolean b) {
        boolean visible = isVisible();
        super.setVisible(b);
        firePropertyChange("visible", visible, b);
    }
}
