package launcher;

import gui.GuiEditor;
import gui.GuiGame;
import gui.GuiMap;
import mod.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static launcher.ComponentSettings.MENU_BUTTON_TEXT;

public class Player extends JPanel {

    private int affiche = 0;
    String[] levelNameList = new File("Levels").list();
    JButton buttonReturn = new JButton();
    JButton buttonPrec = new JButton("Maps Precedentes");
    JButton buttonSuiv = new JButton("Maps Suivantes");
    private short suivant = 0;
    public Player(JPanel cards) {

        CardLayout cl = (CardLayout) cards.getLayout();

        this.addPropertyChangeListener(evt -> {

            if(evt.getPropertyName().equals("visible") && evt.getNewValue().equals(true)){
                suivant = 0;
                affiche = 0;
                for(Component c : this.getComponents()){
                    if(c instanceof JButton)
                        this.remove(c);
                }
                levelNameList = new File("Levels").list();
                buttonReturn = new JButton();
                buttonPrec = new JButton("Maps Precedentes");
                buttonSuiv = new JButton("Maps Suivantes");
                ArrayList<JButton> maps = new ArrayList<>(levelNameList.length);

                ComponentSettings.initializeBackButton(buttonReturn);
                ComponentSettings.initializeDefaultButton(buttonPrec);
                ComponentSettings.initializeDefaultButton(buttonSuiv);

                int k = 0;
                for(int i = 0; i < levelNameList.length; i++) {
                    if (levelNameList[i].endsWith(".xml")) {
                        maps.add(new JButton(levelNameList[i].substring(0, levelNameList[i].length() - 4)));
                        maps.get(k).addActionListener(e -> new GuiGame(new Game(new mod.Map(((JButton)e.getSource()).getText())), ((JButton)e.getSource()).getText()));
                        k++;

                    }
                }

                for (JButton button : maps) {
                    ComponentSettings.initializeDefaultButton(button);
                }

                this.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                gbc.anchor = GridBagConstraints.WEST;
                int j=0;
                this.add(buttonReturn, gbc);
                j++;
                gbc.gridy = j;
                buttonPrec.setVisible(false);
                this.add(buttonPrec, gbc);
                j++;
                for (int i=0; i<maps.size(); i++) {
                    gbc.gridy = j;
                    gbc.anchor = GridBagConstraints.WEST;
                    this.add(maps.get(i), gbc);
                    j++;
                }

                gbc.gridy = j;
                this.add(buttonSuiv, gbc);
                if(maps.size() < 5)
                    buttonSuiv.setVisible(false);

                this.setBackground(ComponentSettings.MENU_BACKGROUND);

                buttonReturn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cl.show(cards, MENU_BUTTON_TEXT);
                    }
                });

                buttonPrec.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        affiche -= 4;
                        suivant--;
                        display_options(maps);
                    }
                });

                buttonSuiv.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        affiche += 4;
                        suivant++;
                        display_options(maps);
                    }

                });

                display_options(maps);
            }
        });

        this.setVisible(true);
    }

    public void setVisible(boolean b) {
        boolean visible = isVisible();
        super.setVisible(b);
        firePropertyChange("visible", visible, b);
    }

    void display_options(ArrayList<JButton> yohanLeNoob) { //Ou canlay Nom de variable plus parlant que "options" dans ce contexte
        for (int i = 0; i< yohanLeNoob.size(); i++) {
            yohanLeNoob.get(i).setVisible(false);
        }

        if(suivant != 0)
            buttonPrec.setVisible(true);
        else{

            buttonPrec.setVisible(false);
        }

        if((yohanLeNoob.size() - affiche-4) < 1 )
            buttonSuiv.setVisible(false);
        else {
            buttonSuiv.setVisible(true);
        }

        for(int i = 0; i< yohanLeNoob.size() && i < affiche ; i++){
            yohanLeNoob.get(i).setVisible(false);
        }

        for (int i = affiche; i< yohanLeNoob.size() && i < affiche+4; i++) {
            yohanLeNoob.get(i).setVisible(true);
        }
    }
}