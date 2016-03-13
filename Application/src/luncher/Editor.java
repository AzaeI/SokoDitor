package luncher;

import gui.GuiMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Editor extends JPanel {

    final static String text_button_back = new String("Retourner en arriere");
    final static String text_button_new = new String("Nouvelle Carte");
    final static String text_button_modify = new String("Modifier Carte existante");

    private ArrayList<JButton> buttons = new ArrayList<JButton>();


    public Editor() {
        final Editor itself = this;
        this.setLayout(new GridLayout(5,0));

        //Initialisation des boutons
        JButton button_back = new JButton(text_button_back);
        JButton button_new = new JButton(text_button_new);
        JButton button_modify = new JButton(text_button_modify);

        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itself.setVisible(false);
                Screens.SetScreen(Screens.mainMenu);
            }
        });
        button_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                itself.setVisible(false);
                Screens.SetScreen(Screens.mainMenu);
            }
        });

        //Tableau contenant tout les boutons
        buttons.add(button_back);
        buttons.add(button_new);
        buttons.add(button_modify);




        initColor();

        //Ajout des boutons du fond du panel
        for(int i = 0; i < buttons.size();i++){
            this.add(buttons.get(i));
        }

        //End
        this.setVisible(true);
    }

    private void initColor(){
        //Coloration des boutons
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setBackground(Screens.soko_button_background);
            buttons.get(i).setForeground(Screens.soko_foreground);
            buttons.get(i).setFont(Screens.font);
        }

        //Coloration du fond du panel
        this.setBackground(Screens.soko_menu_background);
    }
}