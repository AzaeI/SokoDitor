package luncher;

import gui.GuiMap;

import javax.swing.*;
import javax.swing.text.StringContent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import util.*;

public class Play extends JPanel {

    final static String text_button_back = new String("Retourner en arriere");
    final static File LevelsList = new File("Levels");
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private String[] listLevelsComponent;
    private ArrayList<HashMap<String,Integer>> nameMapsKey = new ArrayList<HashMap<String,Integer>>();
    private ArrayList<String> nameMaps = new ArrayList<String>();


    public Play() {
        final Play itself = this;
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

        buttons.add(button_back);
        listLevelsComponent = LevelsList.list();

        for(int i = 0; i < listLevelsComponent.length;i++) {
            if (listLevelsComponent[i].endsWith(".xml") == true) {
                HashMap<String,Integer> a = new HashMap<String,Integer>();
                String name = listLevelsComponent[i].substring(0, listLevelsComponent[i].length() - 4);
                a.put(listLevelsComponent[i].substring(0, listLevelsComponent[i].length() - 4),i);
                nameMapsKey.add(a);
                nameMaps.add(name);
                buttons.add(new JButton(name));
                buttons.get(i+1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        itself.setVisible(false);
                        GuiMap map = new GuiMap(((JButton)e.getSource()).getText());
                        Screens.SetScreen(Screens.mainMenu);
                    }
                });
            }
        }


        //Ajout des boutons du fond du panel
        for(int i = 0; i < buttons.size();i++){
            this.add(buttons.get(i));
        }

        //Colorations des éléments
        initColor();

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