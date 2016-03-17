package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Yohan on 03/03/2016.
 */
public class Input implements KeyListener {

    public enum State{LEFT,DOWN,RIGHT,UP}

    @Override public void keyTyped(KeyEvent event) {}
    @Override public void keyPressed(KeyEvent event){
        switch (event.getKeyCode()){
            case KeyEvent.VK_DOWN :
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_LEFT :
                System.out.println("LEFT");
                break;
            case KeyEvent.VK_UP :
                System.out.println("UP");
                break;
            case KeyEvent.VK_RIGHT :
                System.out.println("RIGHT");
                break;
            default :
                break;
        }
    }
    @Override public void keyReleased(KeyEvent event){}
}
