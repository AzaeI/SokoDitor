package mod;

import ctrl.IGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by DoubleVV on 07/04/2016.
 */
public class Game extends Observable implements IGame {

    private Map map;
    private List<Box> boxes = new ArrayList<>();
    private Character character;
    private boolean state;

    public Game(Map m){
        map = m;
        state = false;
    }

    public void setCharacter(Character c){
        character = c;
    }

    public void setBoxes(List<Box> b){
        boxes = b;
    }

    public void addBoxes(Box b){
        boxes.add(b);
    }

    public Map getMap() {
        return map;
    }

    public Character getCharacter() {
        return character;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public Game getGame(){
        return this;
    }

    public boolean getState(){
        return state;
    }

    public void isWin(){
        state = true;
        for(Box b : boxes){
            if(!(b.getState()))
                state = false;
        }
        setChanged();notifyObservers();
    }

    public void reset(){
        for(Box b : boxes){
            b.reset();
        }
        character.reset();
        setChanged();notifyObservers();
    }

    public String getName(){
        return map.getTitle();
    }
}
