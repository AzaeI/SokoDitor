package mod;

import util.Position;
import util.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Yohan on 10/03/2016.
 */
public abstract class Element extends Observable {

    //commentaire pour le push
    protected static char[][] map;

    protected Element neighborLeft;
    protected Element neighborRight;
    protected Element neighborTop;
    protected Element neighborBot;

    protected List<Box> boxes = new ArrayList<>();

    protected Game game;

    protected Position initialpos;
    protected Position currentpos;

    public Position getPosition(){
        return currentpos;
    }

    public void reset(){
        currentpos = initialpos;
    }

    protected void init(Position p){
        initialpos = p;
        currentpos = p;
        getNeighbors();
    }

    protected void update(){
        getNeighbors();
        for(Box b : boxes){
            b.getNeighbors();
        }
        setChanged();notifyObservers();
    }

    public boolean move(Input.State input, boolean pushing){
        System.out.println(map[currentpos.getX()][currentpos.getY()]);
        switch (input){
            case DOWN:
                if(map[currentpos.getX()][currentpos.getY()+1] != '#')
                {
                    if(neighborRight == null) {
                        currentpos.setY(currentpos.getY() + 1);
                        update();
                        return true;
                    }
                    else if(pushing){
                        if(neighborRight.move(input,false)){
                            currentpos.setY(currentpos.getY() + 1);
                            update();
                            return true;
                        }
                    }
                }
                break;
            case LEFT:
                if(map[currentpos.getX()-1][currentpos.getY()] != '#')
                {
                    if(neighborTop == null) {
                        currentpos.setX(currentpos.getX() - 1);
                        update();
                        return true;
                    }
                    else if(pushing){
                        if(neighborTop.move(input,false)){
                            currentpos.setX(currentpos.getX() - 1);
                            update();
                            return true;
                        }
                    }
                }
                break;
            case UP:
                if(map[currentpos.getX()][currentpos.getY()-1] != '#')
                {
                    if(neighborLeft == null) {
                        currentpos.setY(currentpos.getY() - 1);
                        update();
                        return true;
                    }
                    else if(pushing){
                        if(neighborLeft.move(input,false)){
                            currentpos.setY(currentpos.getY() - 1);
                            update();
                            return true;
                        }
                    }
                }
                break;
            case RIGHT:
                if(map[currentpos.getX()+1][currentpos.getY()] != '#')
                {
                    if(neighborBot == null) {
                        currentpos.setX(currentpos.getX() + 1);
                        update();
                        return true;
                    }
                    else if(pushing){
                        if(neighborBot.move(input,false)){
                            currentpos.setX(currentpos.getX() + 1);
                            update();
                            return true;
                        }
                    }
                }
                break;
        }
        return false;
    }

    protected void getNeighbors(){
        boolean bot = false;
        boolean left = false;
        boolean top = false;
        boolean right = false;

        for (Element b : boxes){
            if (!(bot)){
                if (b.currentpos.getX() == currentpos.getX() + 1 && b.currentpos.getY() == currentpos.getY()) {
                    neighborBot = b;
                    bot = true;
                }
            }
            if (!(left)){
                if (b.currentpos.getX() == currentpos.getX() && b.currentpos.getY() == currentpos.getY()-1) {
                    neighborLeft = b;
                    left = true;
                }
            }
            if (!(top)){
                if (b.currentpos.getX() == currentpos.getX() - 1 && b.currentpos.getY() == currentpos.getY()) {
                    neighborTop = b;
                    top = true;
                }
            }
            if (!(right)){
                if (b.currentpos.getX() == currentpos.getX() && b.currentpos.getY() == currentpos.getY()+1) {
                    neighborRight = b;
                    right = true;
                }
            }
        }

        if(!(bot)){
            neighborBot = null;
        }
        if(!(left)){
            neighborLeft = null;
        }
        if(!(top)){
            neighborTop = null;
        }
        if(!(right)){
            neighborRight = null;
        }
    }
}
