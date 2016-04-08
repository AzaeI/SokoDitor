package gui;

import ctrl.IMap;
import mod.AElement;
import mod.Map;
import util.Input;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by DoubleVV on 03/03/2016.
 */
public class GuiMap extends JComponent implements java.util.Observer{

    /**
     *  # : Wall
     *  , : Floor
     *  @ : Character
     *  . : Goal
     *  $ : Box
     **/
    private IMap map;

    private static boolean isaMapOpen = false;

    private static final String FLOOR = "Sprites/Ground.png";
    private static final String WALL = "Sprites/Wall.png";
    private static final String EMPTY = "Sprites/Vide.png";
    private static final String GOAL = "Sprites/Goal.png";
    private static final String BOX = "Sprites/Ground.png";
    private static final String START = "Sprites/Ground.png";

    public GuiMap(IMap _map){

        /*this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                isaMapOpen = false;
                dispose();
            }
        });*/

        map = _map;
        map.addObserver(this);
    }

    public void setResolution(int resolution) {
//        this.resolution = resolution;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Map Mis a jour");
        repaint();
    }

    protected void draw(Graphics g){
//        g.drawImage(texture, iBox.getPosition().getX()*64,iBox.getPosition().getY()*64, null);

        for(int i = 0; i < map.getHeight(); i++){
            for(int j = 0; j<map.getWidth(); j++){
                switch(map.getCharMap()[i][j]){
                    case '#':
                        g.drawImage(new ImageIcon(WALL).getImage(), j*64, i*64, this);
                        break;
                    case ',':
                        g.drawImage(new ImageIcon(FLOOR).getImage(), j*64, i*64, this);
                        break;
                    case '@':
                        g.drawImage(new ImageIcon(START).getImage(), j*64, i*64, this);
                        break;
                    case '.':
                        g.drawImage(new ImageIcon(GOAL).getImage(), j*64, i*64, this);
                        break;
                    case '$':
                        g.drawImage(new ImageIcon(BOX).getImage(), j*64, i*64, this);
                        break;
                    case ';':
                        g.drawImage(new ImageIcon(EMPTY).getImage(), j*64, i*64, this);
                        break;
                }
            }
        }
        System.out.println("Map dessinée");
    }

    @Override protected void paintComponent(Graphics g){
        draw(g);
    }
}
