package mod;

import ctrl.IEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * Created by DoubleVV on 06/04/2016.
 */


/**
 *  # : Wall
 *  , : Floor
 *  @ : Character
 *  . : Goal
 *  $ : Box
 **/

public class Editor implements IEditor {

    private char[][] map;

    private int width;
    private int height;

    private final static char[] tile = {'#',',','@','.','$'};

    public Editor(){
        width = 20;
        height = 20;
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                map[i][j]=',';
            }
        }
    }

    public void changeTile(int x, int y, char newTile){
        map[x][y] = newTile;
    }

    public void reset(){
        width = 20;
        height = 20;
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                map[i][j]=',';
            }
        }
    }

    public Game validation(){
        return new Game(new Map(map, width, height));
    }

    public void save(){

    }

    public void load(){

    }

    public char tile(int x, int y){
        return map[x][y];
    }

}
