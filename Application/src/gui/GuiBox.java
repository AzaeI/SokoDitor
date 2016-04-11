package gui;

import ctrl.IBox;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

/**
 * Created by DoubleVV on 03/03/2016.
 */
public class GuiBox extends JComponent implements java.util.Observer{

    private static final String pathToTextureOK = "Sprites/BoxOK.png";
    private static final String pathToTextureKO = "Sprites/BoxKO.png";

    private Image texture;
    private IBox iBox;

    public GuiBox(IBox ib){
        iBox = ib;
        texture = new ImageIcon(pathToTextureKO).getImage();
        iBox.addObserver(this);
        setSize(64,64);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (iBox.getState()){
            texture = new ImageIcon(pathToTextureOK).getImage();
        }
        else{
            texture = new ImageIcon(pathToTextureKO).getImage();
        }
    }

    protected void draw(Graphics g){
        g.drawImage(texture, iBox.getPosition().getY()*64,iBox.getPosition().getX()*64, this);
    }

    @Override protected void paintComponent(Graphics g){
        draw(g);
    }
}