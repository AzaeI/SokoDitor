package ctrl;

import util.Input;
import util.Position;

/**
 * Created by DoubleVV on 19/03/2016.
 */
public interface IBox {

    public boolean getState();
    public boolean move(Input.State input, boolean pushing);
    public Position getPosition();
    public void reset();
    public void addObserver(java.util.Observer obs);
}
