package luncher;

import gui.GuiMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Play extends JPanel {

    final static String text_button_back = new String("Retourner en arriere");
    final static File LevelsList = new File("Levels");


    public Play() {
        final Play itself = this;
        int nbLevelsDispo = LevelsList.list().length ;
        String[] listLevelsComponent;
        this.setLayout(new GridLayout(5,0));

        //Initialisation des boutons
        JButton button_back = new JButton(text_button_back);

        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itself.setVisible(false);
                Screens.SetScreen(Screens.mainMenu);
            }
        });

        //Tableau contenant tout les boutons
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        buttons.add(button_back);

        listLevelsComponent = LevelsList.list();
        for(int i = 0; i < listLevelsComponent.length;i++) {
            if (listLevelsComponent[i].endsWith(".xml") == true) {
                String name = listLevelsComponent[i].substring(0, listLevelsComponent[i].length() - 4);
                buttons.add(new JButton(name));
                buttons.get(i+1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        itself.setVisible(false);
                        GuiMap map = new GuiMap(name);
                        Screens.SetScreen(Screens.mainMenu);
                    }
                });
            }
        }


        //Coloration des boutons
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setBackground(Color.white);
            buttons.get(i).setForeground(Color.GREEN);
            buttons.get(i).setFont(Screens.font);
        }

        //Coloration du fond du panel
        this.setBackground(Color.darkGray);

        //Ajout des boutons du fond du panel
        for(int i = 0; i < buttons.size();i++){
            this.add(buttons.get(i));
        }

        //End
        this.setVisible(true);
    }
}