package ctrl;

import mod.Box;
import mod.Character;
import mod.Game;
import mod.Map;

import java.util.List;
import java.util.Observer;

/**
 * Created by DoubleVV on 03/04/2016.
 */
public interface IGame {

    public void isWin();
    public void reset();
    public void addBoxes(Box b);
    public Map getMap();
    public Character getCharacter();
    public List<Box> getBoxes();
    public Game getGame();
    public void setCharacter(Character c);
    public boolean getState();
    public void addObserver(Observer obs);
}
