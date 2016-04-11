package launcher;

import gui.GuiEditor;
import gui.GuiMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static launcher.ComponentSettings.MENU_BUTTON_TEXT;

/**
 * Created by Yohan on 07/04/2016.
 */
public class ModifyMap extends JPanel {
    private int affiche = 1;

    public ModifyMap(JPanel cards) {

        CardLayout cl = (CardLayout) cards.getLayout();

        JButton buttonReturn = new JButton(ComponentSettings.RETURN_BUTTON_TEXT);
        JButton buttonPrec = new JButton("Maps Precedentes");
        JButton buttonSuiv = new JButton("Maps Suivantes");

        String[] levelNameList = new File("Levels").list();

        JButton maps[] = new JButton[levelNameList.length+3];
        maps[0] = buttonReturn;
        maps[1] = buttonPrec;
        maps[maps.length-1] = buttonSuiv;

        for(int i = 0; i < levelNameList.length; i++) {
            if (levelNameList[i].endsWith(".xml")) {
                maps[i+2] = new JButton(levelNameList[i].substring(0, levelNameList[i].length() - 4));
                maps[i+2].addActionListener(e -> new GuiEditor( "Levels/"+((JButton)e.getSource()).getText() + ".xml") );
            }
        }


        for (JButton button : maps) {
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

        for (int i=0; i<maps.length; i++) {
            gbc.gridy = i;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            this.add(maps[i], gbc);
        }

        this.setBackground(ComponentSettings.MENU_BACKGROUND);

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cards, ComponentSettings.EDITOR_TITLE);
            }
        });

        buttonPrec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                affiche -= 4;
                display_options(maps);
            }
        });

        buttonSuiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                affiche += 4;
                display_options(maps);
            }
        });

        display_options(maps);
    }

    void display_options(JButton[] options) {
        for (int i = 1; i< options.length; i++) {
            options[i].setVisible(false);
        }

        for (int i = affiche+1; i< options.length && i < affiche+2+3; i++) {
            options[i].setVisible(true);
        }

        if(affiche == 1)
            options[1].setVisible(false);
        else
            options[1].setVisible(true);
        if(affiche+3 >= options.length-3)
            options[options.length-1].setVisible(false);
        else
            options[options.length-1].setVisible(true);
    }
}
