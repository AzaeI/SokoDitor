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

        this.setLayout(new GridLayout(5,0));

        //Initialisation des boutons
        JButton menuButton = new JButton(MENU_BUTTON_TEXT);
        JButton newMapButton = new JButton(NEW_MAP_TEXT_BUTTON);
        JButton modifyMapButton = new JButton(MODIFY_MAP_TEXT_BUTTON);

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cards, MENU_BUTTON_TEXT);
            }
        });
        newMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new GuiEditor(null);
            }
        });
        modifyMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cards,"Modifier Map");
            }
        });


        buttons.add(menuButton);
        buttons.add(newMapButton);
        buttons.add(modifyMapButton);

        initColor();

        buttons.forEach(this::add);

    }

    private void initColor(){
        //Coloration des boutons
        for (int i = 0; i < buttons.size(); i++) {
           // buttons.get(i).setBackground(MainFrame.soko_button_background);
           // buttons.get(i).setForeground(MainFrame.soko_foreground);
            //buttons.get(i).setFont(MainFrame.font);
        }

        //Coloration du fond du panel
        //this.setBackground(MainFrame.soko_menu_background);
    }
}