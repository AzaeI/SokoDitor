package mod;

import ctrl.ICharacter;
import gui.GuiGame;
import util.Input;
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

    public int getScore() {
        return score;
    }

    private int score = 0;

    public Character(char[][] _map, Position init, List<Box> _boxes) {
        map = _map;
        boxes = _boxes;
        init(init);
    }

    @Override
    public boolean move(Input.State input, boolean pushing) {
        if (super.move(input, pushing)) {
            score++;
            return true;
        }
        return false;
    }

    @Override
    protected void init(Position p) {
        super.init(p);
        score = 0;
    }


}