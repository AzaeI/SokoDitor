package mod;

import ctrl.IBox;
import util.Input;
import util.Position;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Observable;

/**
 * Created by Yohan on 03/03/2016.
 */
public class Box extends Element implements IBox {

    private boolean isOnFinish = false;

    public Box(Position init,Game _game){
        game = _game;
        init(init);
//        System.out.println(currentpos.getX()+" , "+currentpos.getY());
    }

    public boolean getState(){
        return isOnFinish;
    }

    @Override protected void update(){
        getNeighbors();
        isOnFinish = false;
//        System.out.println(isOnFinish+" , "+map[currentpos.getX()][currentpos.getY()]);

        if (map[currentpos.getX()][currentpos.getY()] == '.') {
            isOnFinish = true;
//                System.out.println("aime des aires");
        }
        setChanged();notifyObservers();
        game.isWin();
//        System.out.println(currentpos.getX()+" , "+currentpos.getY());
    }

    public void setBoxes(List<Box> b){
        boxes = b;
    }
}
