package launcher;

import gui.GuiEditor;
import gui.GuiMap;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static launcher.ComponentSettings.MENU_BUTTON_TEXT;

/**
 * Created by Yohan on 07/04/2016.
 */
public class ModifyMap extends JPanel {
    private ArrayList<JButton> buttons = new ArrayList<JButton>();

    public ModifyMap(JPanel cards) {

        CardLayout cl               = (CardLayout) cards.getLayout();
        JButton backToMenuButton    = new JButton(MENU_BUTTON_TEXT);

        this.setLayout(new GridLayout(5,0));

        backToMenuButton.addActionListener(e -> cl.show(cards, MENU_BUTTON_TEXT));

        buttons.add(backToMenuButton);

        String[] levelNameList = new File("Levels").list();

        for(int i = 0; i < levelNameList.length; i++) {
            if (levelNameList[i].endsWith(".xml")) {
                buttons.add(new JButton(levelNameList[i].substring(0, levelNameList[i].length() - 4)));
                buttons.get(i+1).addActionListener(e -> new GuiEditor( "Levels/"+((JButton)e.getSource()).getText() + ".xml") );
            }
        }

        buttons.forEach(this::add);

        initColor();

    }

    private void initColor(){
        //Coloration des boutons
        for (int i = 0; i < buttons.size(); i++) {
           /* buttons.get(i).setBackground(MainFrame.soko_button_background);
            buttons.get(i).setForeground(MainFrame.soko_foreground);
            buttons.get(i).setFont(MainFrame.font);*/
        }

        //Coloration du fond du panel
        //this.setBackground(MainFrame.soko_menu_background);
    }
}
