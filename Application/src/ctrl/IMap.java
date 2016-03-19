package ctrl;

import util.Position;

import java.util.List;

/**
 * Created by Yohan on 03/03/2016.
 */
public interface IMap {

    public int getHeight();
    public int getWidth();
    public char[][] getCharMap();
    public void addObserver(java.util.Observer obs);
    public Position getCharStart();
    public List<Position> getBoxStart();
}
