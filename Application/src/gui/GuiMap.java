package gui;

import ctrl.AElement;
import mod.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.TableUI;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Yohan on 03/03/2016.
 */
public class GuiMap extends JFrame{

    private int Hauteur;
    private int Largeur;
    private Map map;
    private AElement ObjectMap[][];
    private final GuiMap itself = this;

    private static boolean isaMapOpen = false;
    private int resolution = 64;

    public GuiMap(String path){
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                isaMapOpen = false;
                dispose();
            }
        });
        if(!isaMapOpen){
            isaMapOpen = true;
            map = new Map("Levels/"+path+".xml");
            //System.out.println("Levels/"+path+".xml Open");
            Hauteur = map.getHeight();
            Largeur = map.getWidth();

            ObjectMap = map.getMapObject();

            this.setLayout(new GridLayout(Hauteur,Largeur));

            for(int i = 0; i < Hauteur; i++){
                for (int j = 0; j < Largeur; j++){
                    JLabel b = new JLabel();
                    String pathToText = ObjectMap[i][j].getPathToTexture();
                    ImageIcon img = new ImageIcon(pathToText);
                    b.setIcon(img);
                    b.setSize(new Dimension(resolution,resolution));
                    this.add(b);
                }
            }
            setResizable(false);
            setSize(new Dimension(resolution*Largeur,resolution*Hauteur));
            setVisible(true);
        }else {
            showMessageDialog(null, "Vous ne pouvez pas lancer deux parties en même temps !");
        }

    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }
}
