package launcher;

import storage.dao.factory.DAOFactory;
import storage.dao.factory.FactoryType;

import javax.swing.*;
import java.awt.*;

class Downloader extends JPanel {

    Downloader(JPanel cards) {

        CardLayout cl = (CardLayout) cards.getLayout();

        //Initialisation des boutons
        JButton returnButton = new JButton(ComponentSettings.RETURN_BUTTON_TEXT);

        //Tableau contenant tous les boutons
        JButton buttons[] = {returnButton};

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
         *                      RETURN BUTTON
         ********************************************************/

        gbc.gridy = 0;
        //La taille en hauteur et en largeur
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(returnButton, gbc);

        //Coloration du fond du Menu
        this.setBackground(ComponentSettings.MENU_BACKGROUND);

        //Actions Listeners
        returnButton.addActionListener(e -> cl.show(cards, ComponentSettings.MENU_TITLE));

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
