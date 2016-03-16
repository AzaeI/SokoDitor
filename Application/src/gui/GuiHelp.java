package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yohan on 16/03/2016.
 */
public class GuiHelp extends JFrame {

    GuiHelp(){

        JPanel mainP = new JPanel();
        mainP.setLayout(new GridLayout(1,0));
        JLabel text1 = new JLabel("Pour valider la hauteur ou la largeur de la grille,\nil suiffit d'appuyer sur entrée, une fois la valeur mise.");

        mainP.add(text1);

        add(mainP);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
