package gui;

import ctrl.ICharacter;
import ctrl.IElement;
import util.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

/**
 * Created by Yohan on 03/03/2016.
 */
public class GuiCharacter extends JComponent implements java.util.Observer, KeyListener{

    private ICharacter character;
    private final static String pathToTexture = "Sprites/Chara.png";
    private Image texture;
    private GuiGame game;

    protected void draw(Graphics g){
        g.drawImage(texture, character.getPosition().getY()*64,character.getPosition().getX()*64, this);
        System.out.println("Personnage dessinée");
    }

    public GuiCharacter(ICharacter ic,GuiGame _g){
        game = _g;
        character = ic;
        texture = new ImageIcon(pathToTexture).getImage();
        character.addObserver(this);
        setSize(64,64);
        System.out.println("Box crée");
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void update(Observable o, Object arg) {}

    @Override public void keyTyped(KeyEvent event) {}
    @Override public void keyPressed(KeyEvent event){
        switch (event.getKeyCode()){
            case KeyEvent.VK_DOWN :
                character.move(Input.State.RIGHT,true);
                System.out.println(character.getPosition().getX()+" , "+character.getPosition().getY());
                game.repaint();
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_LEFT :
                character.move(Input.State.UP,true);
                game.repaint();
                System.out.println("LEFT");
                break;
            case KeyEvent.VK_UP :
                character.move(Input.State.LEFT,true);
                game.repaint();
                System.out.println("UP");
                break;
            case KeyEvent.VK_RIGHT :
                character.move(Input.State.DOWN,true);
                game.repaint();
                System.out.println("RIGHT");
                break;
            default :
                break;
        }
    }
    @Override public void keyReleased(KeyEvent event){}
}
