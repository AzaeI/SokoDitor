package mod;

import ctrl.AElement;
import util.Input;
import util.Position;

/**
 * Created by Yohan on 03/03/2016.
 */
public class Box extends AElement {

    private boolean isOnFinish = false;

    private String pathToTextureOK;

    public Box(){
        setPathToTexture("Sprites/BoxKO.png");
        pathToTextureOK = "Sprites/BoxOK.png";
    }

    public String getPathToTextureOK() {
        return pathToTextureOK;
    }

    private char [][] map;

    private char neighborLeft;
    private char neighborRight;
    private char neighborTop;
    private char neighborBot;

    private Position initialpos;
    private Position currentpos;

    public boolean getState(){
        return isOnFinish;
    }

    private void getNeighbors(){
        neighborBot = map[currentpos.getX()+1][currentpos.getY()];
        neighborLeft = map[currentpos.getX()][currentpos.getY()-1];
        neighborTop = map[currentpos.getX()-1][currentpos.getY()];
        neighborRight = map[currentpos.getX()][currentpos.getY()+1];
    }

    public void init(Position p){
        initialpos = p;
        currentpos = p;
        getNeighbors();
    }

    public void Move(Input.State input){
        switch (input){
            case DOWN :
                if(neighborBot != '#') {
                    currentpos.setY(currentpos.getY() + 1);
                    getNeighbors();
                }
                break;
            case LEFT :
                if(neighborBot != '#') {
                    currentpos.setX(currentpos.getX() - 1);
                    getNeighbors();
                }
                break;
            case UP :
                if(neighborBot != '#') {
                    currentpos.setY(currentpos.getY() - 1);
                    getNeighbors();
                }
                break;
            case RIGHT :
                if(neighborBot != '#') {
                    currentpos.setX(currentpos.getX() + 1);
                    getNeighbors();
                }
                break;
        }
    }
}
