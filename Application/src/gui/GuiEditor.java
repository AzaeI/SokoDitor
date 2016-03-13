package gui;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by lab on 13/03/16.
 */
public class GuiEditor  extends JFrame{

    private static boolean isaEditorOpen = false;


    GuiEditor (){
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                isaEditorOpen = false;
                dispose();
            }
        });
        if (!isaEditorOpen){
            isaEditorOpen = true;

        }else{
            showMessageDialog(null, "Vous ne pouvez pas Editer 2 map en même temps !");
        }
    }
}
