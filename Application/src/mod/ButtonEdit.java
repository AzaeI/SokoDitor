package mod;

import javax.swing.*;

/**
 * Created by Yohan on 16/03/2016.
 */
public class ButtonEdit extends JButton {

    private char elmt;
    String s;

    private static final String FLOOR = "Sprites/Ground.png";
    private static final String WALL = "Sprites/Wall.png";
    private static final String EMPTY = "Sprites/Vide.png";
    private static final String GOAL = "Sprites/Goal.png";
    private static final String BOX = "Sprites/BoxKO.png";
    private static final String START = "Sprites/Chara.png";

    public ButtonEdit(char e){
        elmt = e;
        ImageIcon img = new ImageIcon();
        switch(e){
            case '#':
                img = new ImageIcon(WALL);
                break;
            case ',':
                img = new ImageIcon(FLOOR);
                break;
            case '@':
                img = new ImageIcon(START);
                break;
            case '.':
                img = new ImageIcon(GOAL);
                break;
            case '$':
                img = new ImageIcon(BOX);
                break;
            case ';':
                img = new ImageIcon(EMPTY);
                break;
        }
        setIcon(img);
    }
    public ButtonEdit(char e,String s){
        elmt = e;
        ImageIcon img = new ImageIcon();
        switch(e){
            case '#':
                img = new ImageIcon(WALL);
                break;
            case ',':
                img = new ImageIcon(FLOOR);
                break;
            case '@':
                img = new ImageIcon(START);
                break;
            case '.':
                img = new ImageIcon(GOAL);
                break;
            case '$':
                img = new ImageIcon(BOX);
                break;
            case ';':
                img = new ImageIcon(EMPTY);
                break;
        }
        setIcon(img);
        this.s = s;

    }

    public void setElmt(char elmt) {
        this.elmt = elmt;
        updateTexture();
    }
    public void updateTexture(){
        ImageIcon img = new ImageIcon();
        switch(elmt){
            case '#':
                img = new ImageIcon(WALL);
                break;
            case ',':
                img = new ImageIcon(FLOOR);
                break;
            case '@':
                img = new ImageIcon(START);
                break;
            case '.':
                img = new ImageIcon(GOAL);
                break;
            case '$':
                img = new ImageIcon(BOX);
                break;
            case ';':
                img = new ImageIcon(EMPTY);
                break;
        }
        setIcon(img);
    }

    public char getElmt() {
        return elmt;
    }
}
