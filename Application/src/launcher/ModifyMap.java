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
    public ModifyMap(JPanel cards) {

        CardLayout cl = (CardLayout) cards.getLayout();

        JButton buttonReturn = new JButton(ComponentSettings.RETURN_BUTTON_TEXT);

        String[] levelNameList = new File("Levels").list();

        JButton maps[] = new JButton[levelNameList.length+1];
        maps[0] = buttonReturn;

        for(int i = 0; i < levelNameList.length; i++) {
            if (levelNameList[i].endsWith(".xml")) {
                maps[i+1] = new JButton(levelNameList[i].substring(0, levelNameList[i].length() - 4));
                maps[i+1].addActionListener(e -> new GuiEditor( "Levels/"+((JButton)e.getSource()).getText() + ".xml") );
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
    }
}
