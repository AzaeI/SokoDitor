package launcher;

import gui.GuiEditor;
import gui.GuiMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static launcher.ComponentSettings.*;

public class Editor extends JPanel {

    private ArrayList<JButton> buttons = new ArrayList<JButton>();

    public Editor(JPanel cards) {

        CardLayout cl = (CardLayout) cards.getLayout();

        JButton buttonReturn            = new JButton(ComponentSettings.RETURN_BUTTON_TEXT);
        JButton buttonNewMap            = new JButton(ComponentSettings.NEW_MAP_TEXT_BUTTON);
        JButton buttonEditMap           = new JButton(ComponentSettings.MODIFY_MAP_TEXT_BUTTON);

        JButton buttons[] = { buttonReturn, buttonNewMap, buttonEditMap};

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

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cards, MENU_BUTTON_TEXT);
            }
        });
        buttonNewMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new GuiEditor(null);
            }
        });
        buttonEditMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cards,"Modifier Map");
            }
        });


    }
}