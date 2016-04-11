package mod;

import ctrl.ICharacter;
import util.Position;

import java.util.List;

/**
 * Created by DoubleVV on 10/03/2016.
 */
public class Character extends Element implements ICharacter {

//    private char neighborLeft;
//    private char neighborRight;
//    private char neighborTop;
//    private char neighborBot;

    public Character(char[][] _map, Position init, List<Box> _boxes) {

        map = _map;
        boxes = _boxes;
        init(init);
    }
}