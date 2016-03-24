package luncher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Settings extends JPanel {

    JOptionPane pane = new JOptionPane();

    //Boite de fileChooser de recuperation de ficher et son/ses filtre(s)
    JFileChooser fileChooser = new JFileChooser();
    MonFiltre filterPNG = new MonFiltre(new String[]{"png"}, "Fichers PNG de taille 64 sur 64 (*.png)");

    // récuperation du this
    JPanel itself = this;

    //Espace entre les boutons
    final static int SPACE_BETWEEN = 1;

    // Initialisations des textes utilisés

    final static String text_button_tab_textures = new String("Choisir les textures");
    final static String text_button_tab_sound = new String("Changer les options sonores");
    final static String text_button_tab_user = new String("Modifier son compte utilisateur");

    final static String text_button_back = new String("Retourner en arriere");
    final static String text_button_default = new String("Remmetre les valeurs par defaut");

    final static String text_button_sound = new String("Desactiver le son");

    final static String text_button_change_chara = new String("Changer de personage");
    final static String text_button_change_wall = new String("Changer la texture des murs");
    final static String text_button_change_box = new String("Changer la texture des caisses");
    final static String text_button_change_box_final = new String("Changer la texture des caisses en place");
    final static String text_button_change_final = new String("Changer la texture des emplacements pour caisses");
    final static String text_button_change_ground = new String("Changer la texture du sol");

    final static String text_button_new_mdp = new String("Changer de mot de passe : ");

    public Settings() {

        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.addChoosableFileFilter(filterPNG);

        final Settings itself = this;

        //Initialisation des onglets

        JButton button_tab_textures = new JButton(text_button_tab_textures);
        JButton button_tab_sound = new JButton(text_button_tab_sound);
        JButton button_tab_user = new JButton(text_button_tab_user);

        //Initialisation des boutons

        JButton button_back = new JButton(text_button_back);
        JButton button_default = new JButton(text_button_default);

        JButton button_sound = new JButton(text_button_sound);

        JButton button_change_chara = new JButton(text_button_change_chara);
        JButton button_change_wall = new JButton(text_button_change_wall);
        JButton button_change_box = new JButton(text_button_change_box);
        JButton button_change_box_final = new JButton(text_button_change_box_final);
        JButton button_change_final = new JButton(text_button_change_final);
        JButton button_change_ground = new JButton(text_button_change_ground);

        JButton button_new_mdp = new JButton(text_button_new_mdp);

        // Space
        JCheckBox space1 = new JCheckBox();
        JCheckBox space2 = new JCheckBox();
        set_invisible(space1);
        set_invisible(space2);


        //Tableaux
        JButton buttons[] = {button_back, button_default, button_tab_sound, button_tab_textures, button_tab_user, button_sound, button_change_chara, button_change_wall, button_change_box, button_change_box_final, button_change_final, button_change_ground, button_new_mdp};

        final JButton tab_north[] = {button_back, button_default};
        final JButton tab_center[] = {button_tab_sound, button_tab_textures, button_tab_user};

        final JButton tab_sound[] = {button_sound};
        final JButton tab_textures[] = {button_change_box, button_change_box_final, button_change_chara, button_change_final, button_change_ground, button_change_wall};
        final JButton tab_user[] = {button_new_mdp};

        int maximum_options = 0;
        if (tab_sound.length > tab_textures.length) maximum_options = tab_sound.length;
        if (tab_textures.length > tab_user.length) maximum_options = tab_sound.length;
        if (tab_user.length > tab_sound.length) maximum_options = tab_sound.length;
        maximum_options = maximum_options + tab_north.length + tab_center.length;

        //Actions des boutons

        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itself.setVisible(false);
                Screens.SetScreen(Screens.mainMenu);
            }
        });

        button_tab_sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display_options(tab_sound, true);
                display_options(tab_textures, false);
                display_options(tab_user, false);
            }
        });

        button_tab_textures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display_options(tab_sound, false);
                display_options(tab_textures, true);
                display_options(tab_user, false);
            }
        });

        button_tab_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display_options(tab_sound, false);
                display_options(tab_textures, false);
                display_options(tab_user, true);
            }
        });

        button_default.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        button_sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        //Textures

        button_change_chara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File lol = get_file();
                // put the return somewhere usefull
            }
        });
        button_change_wall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_file();
                // put the return somewhere usefull
            }
        });
        button_change_box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_file();
                // put the return somewhere usefull
            }
        });
        button_change_box_final.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_file();
                // put the return somewhere usefull
            }
        });
        button_change_final.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_file();
                // put the return somewhere usefull
            }
        });
        button_change_ground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_file();
                // put the return somewhere usefull
            }
        });

        button_new_mdp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        // On cache les buttons dans les onglets
        display_options(tab_sound, false);
        display_options(tab_textures, false);
        display_options(tab_user, false);

        //Coloration des boutons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(Screens.soko_button_background);
            buttons[i].setForeground(Screens.soko_foreground);
            buttons[i].setFont(Screens.font);
        }

        //Ajout et positionnement des boutons
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add_multiple(tab_north);
        add(space1);
        add_multiple(tab_center);
        add(space2);
        add_multiple(tab_sound);
        add_multiple(tab_textures);
        add_multiple(tab_user);
        //Coloration du fond du panel
        this.setBackground(Screens.soko_menu_background);

        //Coloration des boutons de catégories
        button_tab_sound.setBackground(Screens.soko_background);
        button_tab_textures.setBackground(Screens.soko_background);
        button_tab_user.setBackground(Screens.soko_background);

        //End
        this.setVisible(true);
    }

    void display_options(JButton[] options, boolean display) {
        for (int i = 0; i < options.length; i++) {
            options[i].setVisible(display);
            itself.setVisible(true);
        }
    }

    void add_multiple(JButton[] options) {
        for (int i = 0; i < options.length; i++) {
            this.add(options[i]);
        }
    }

    void set_invisible(JCheckBox box) {
        box.setEnabled(false);
        box.setForeground(Screens.soko_menu_background);
        box.setBackground(Screens.soko_menu_background);
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