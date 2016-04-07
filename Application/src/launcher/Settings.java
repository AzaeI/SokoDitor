package launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Settings extends JPanel {

    private JOptionPane pane = new JOptionPane();

    //Boite de fileChooser de recuperation de ficher et son/ses filtre(s)
    JFileChooser fileChooser = new JFileChooser();
    MonFiltre filterPNG = new MonFiltre(new String[]{"png"}, "Fichers PNG de taille 64 sur 64 (*.png)");

    // récuperation du this
    JPanel itself = this;

    public Settings(JPanel cards) {

        CardLayout cl = (CardLayout) cards.getLayout();

        //Initialisation des boutons
        JButton buttonReturnM             = new JButton(ComponentSettings.RETURN_BUTTON_TEXT);
        JButton buttonReturn             = new JButton(ComponentSettings.RETURN_BUTTON_TEXT);
        JButton buttonReset             = new JButton(ComponentSettings.SETTINGS_RESET);
        JButton buttonSound             = new JButton(ComponentSettings.SETTINGS_SOUND);
        JButton buttonSoundMute         = new JButton(ComponentSettings.SETTINGS_SOUND_MUTE);
        JButton buttonTextures          = new JButton(ComponentSettings.SETTINGS_TEXTURES);
        JButton buttonTexturesCaisse    = new JButton(ComponentSettings.SETTINGS_TEXTURES_CAISSE);
        JButton buttonTexturesCaisseOK  = new JButton(ComponentSettings.SETTINGS_TEXTURES_CAISSE_OK);
        JButton buttonTexturesPerso     = new JButton(ComponentSettings.SETTINGS_TEXTURES_PERSO);
        JButton buttonTexturesDock      = new JButton(ComponentSettings.SETTINGS_TEXTURES_DOCK);
        JButton buttonTexturesSol       = new JButton(ComponentSettings.SETTINGS_TEXTURES_SOL);
        JButton buttonTexturesWall      = new JButton(ComponentSettings.SETTINGS_TEXTURES_WALL);
        JButton buttonUser              = new JButton(ComponentSettings.SETTINGS_USER);
        buttonUser.setEnabled(false);

        JButton buttons[] = { buttonReturnM, buttonReset, buttonSound, buttonTextures, buttonUser, buttonReturn, buttonSoundMute, buttonTexturesCaisse, buttonTexturesCaisseOK, buttonTexturesPerso, buttonTexturesDock, buttonTexturesSol, buttonTexturesWall};
        JButton buttonsReturn[] = { buttonReturn};
        JButton buttonsGenerals[] = { buttonReturnM, buttonReset, buttonSound, buttonTextures, buttonUser };
        JButton buttonsSound[] = { buttonSoundMute};
        JButton buttonsTextures[] = { buttonTexturesCaisse, buttonTexturesCaisseOK, buttonTexturesPerso, buttonTexturesDock, buttonTexturesSol, buttonTexturesWall};

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

        for (int i=0; i<buttons.length; i++) {
            gbc.gridy = i;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            this.add(buttons[i], gbc);
        }

        this.setBackground(ComponentSettings.MENU_BACKGROUND);

        display_options(buttonsGenerals, true);
        display_options(buttonsReturn, false);
        display_options(buttonsSound, false);
        display_options(buttonsTextures, false);

        buttonReturnM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display_options(buttons, false);
                display_options(buttonsGenerals, true);
                itself.setVisible(false);
                // MainFrame.SetScreen(MainFrame.mainMenu);
            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display_options(buttonsReturn, false);
                display_options(buttonsGenerals, true);
                display_options(buttonsSound, false);
                display_options(buttonsTextures, false);
            }
        });

        buttonSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display_options(buttonsReturn, true);
                display_options(buttonsGenerals, false);
                display_options(buttonsSound, true);
                display_options(buttonsTextures, false);
            }
        });

        buttonTextures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display_options(buttonsReturn, true);
                display_options(buttonsGenerals, false);
                display_options(buttonsSound, false);
                display_options(buttonsTextures, true);
            }
        });

        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.addChoosableFileFilter(filterPNG);

        final Settings itself = this;

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        buttonSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        //Textures

        buttonTexturesPerso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File lol = get_file();
                // put the return somewhere usefull
            }
        });
        buttonTexturesWall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_file();
                // put the return somewhere usefull
            }
        });
        buttonTexturesCaisse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_file();
                // put the return somewhere usefull
            }
        });
        buttonTexturesCaisseOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_file();
                // put the return somewhere usefull
            }
        });
        buttonTexturesDock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_file();
                // put the return somewhere usefull
            }
        });
        buttonTexturesSol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_file();
                // put the return somewhere usefull
            }
        });

        this.setVisible(true);
    }

    void display_options(JButton[] options, boolean display) {
        for (int i = 0; i < options.length; i++) {
            options[i].setVisible(display);
            itself.setVisible(true);
        }
    }

    File get_file() {
        BufferedImage bimg = null;
        fileChooser.showOpenDialog(null);
        System.out.println("Fichier choisi : " + fileChooser.getSelectedFile());
        File file = fileChooser.getSelectedFile();


        try {
            bimg = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = bimg.getWidth();
        int height = bimg.getHeight();

        System.out.println("width : " + width);
        System.out.println("height : " + height);

        if (width == 64 && height == 64) {
            return fileChooser.getSelectedFile();
        } else {
            pane.showMessageDialog(null, "Votre image ne peut pas etre accepté car sa taille est differente de 64 pixels sur 64 pixels", "Image refusée", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}