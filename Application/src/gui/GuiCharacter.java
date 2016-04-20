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
 * Created by DoubleVV on 03/03/2016.
 */
public class GuiCharacter extends JComponent implements java.util.Observer, KeyListener {

    private ICharacter character;
    private final static String pathToTextureHaut = "Sprites/SpriteChar/CharHaut.png";
    private final static String pathToTextureBas = "Sprites/SpriteChar/CharBas.png";
    private final static String pathToTextureDroit = "Sprites/SpriteChar/CharDroit.png";
    private final static String pathToTextureGauche = "Sprites/SpriteChar/CharGauche.png";
    private Image texture;
    private GuiGame game;

    protected void draw(Graphics g){
        g.drawImage(texture, character.getPosition().getY()*64,character.getPosition().getX()*64, this);
    }

    public GuiCharacter(ICharacter ic,GuiGame _g){
        game = _g;
        character = ic;
        texture = new ImageIcon(pathToTextureBas).getImage();
        character.addObserver(this);
        setSize(64,64);
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
                texture = new ImageIcon(pathToTextureBas).getImage();
                game.repaint();
                break;
            case KeyEvent.VK_LEFT :
                character.move(Input.State.UP,true);
                texture = new ImageIcon(pathToTextureGauche).getImage();
                game.repaint();
                break;
            case KeyEvent.VK_UP :
                character.move(Input.State.LEFT,true);
                texture = new ImageIcon(pathToTextureHaut).getImage();

                game.repaint();
                break;
            case KeyEvent.VK_RIGHT :
                character.move(Input.State.DOWN,true);
                texture = new ImageIcon(pathToTextureDroit).getImage();
                game.repaint();
                break;
            default :
                break;
        }
    }
    @Override public void keyReleased(KeyEvent event){}
}

