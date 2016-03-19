package mod;

import ctrl.ICharacter;
import util.Position;

import java.util.List;

/**
 * Created by Yohan on 10/03/2016.
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

//    public void move(Input.State input){
        /*switch (input){
            case DOWN :
                if(neighborRight != '#'){
                    if(neighborRight == '$'){
                        if(map[currentpos.getX()][currentpos.getY()+2] != '#'
                                || map[currentpos.getX()][currentpos.getY()+2] != '$'){
                            currentpos.setY(currentpos.getY() + 1);
                            update();
                        }
                    }
//                    currentpos.setY(currentpos.getY() + 1);
//                    update();
//                    System.out.println(currentpos.getX()+" , "+currentpos.getY());
////                    setChanged();notifyObservers();
//                    return true;
                }
                break;
            case LEFT :
                if(neighborTop != '#') {
                    if (neighborTop == '$'){
                        if(map[currentpos.getX()-2][currentpos.getY()] != '#'
                                || map[currentpos.getX()-2][currentpos.getY()] != '$'){
                            currentpos.setX(currentpos.getX()-1);
                            update();
                        }
                    }
//                    currentpos.setX(currentpos.getX() - 1);
//                    update();
//                    System.out.println(currentpos.getX()+" , "+currentpos.getY());
////                    setChanged();notifyObservers();
//                    return true;
                }
                break;
            case UP :
                if(neighborLeft != '#') {
                    if (neighborLeft == '$'){
                        if(map[currentpos.getX()][currentpos.getY()-2] != '#'
                                || map[currentpos.getX()][currentpos.getY()-2] != '$'){
                            currentpos.setY(currentpos.getY()-1);
                            update();
                        }
                    }
//                    currentpos.setY(currentpos.getY() - 1);
//                    update();
//                    System.out.println(currentpos.getX()+" , "+currentpos.getY());
////                    setChanged();notifyObservers();
//                    return true;
                }
                break;
            case RIGHT :
                if(neighborBot != '#') {
                    if (neighborBot == '$'){
                        if(map[currentpos.getX()+2][currentpos.getY()] != '#'
                                || map[currentpos.getX()+2][currentpos.getY()] != '$'){
                            currentpos.setX(currentpos.getX()+1);
                            update();
                        }
                    }
//                    currentpos.setX(currentpos.getX() + 1);
//                    update();
//                    System.out.println(currentpos.getX()+" , "+currentpos.getY());
////                    setChanged();notifyObservers();
//                    return true;
                }
                break;
        }*/
//        switch (input){
//            case DOWN:
//                if()
//                {
//
//                }
//                break;
//            case LEFT:
//                break;
//            case UP:
//                break;
//            case RIGHT:
//                break;
//        }
//    }
//}
