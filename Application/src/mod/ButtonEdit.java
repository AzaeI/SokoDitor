package mod;

import ctrl.AElement;

import javax.swing.*;

/**
 * Created by Yohan on 16/03/2016.
 */
public class ButtonEdit extends JButton {

    private AElement elmt;
    private int x;
    private int y;

    public ButtonEdit(AElement e, int x, int y){
        elmt = e;
        this.x = x;
        this.y = y;
        ImageIcon img = new ImageIcon(elmt.getPathToTexture());
        setIcon(img);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setElmt(AElement elmt) {
        this.elmt = elmt;
        updateTexture();
    }
    private void updateTexture(){
        ImageIcon img = new ImageIcon(elmt.getPathToTexture());
        setIcon(img);
    }
}
