package mod;

import ctrl.AElement;

import javax.swing.*;

/**
 * Created by Yohan on 16/03/2016.
 */
public class ButtonEdit extends JButton {

    private AElement elmt;
    String s;

    public ButtonEdit(AElement e){
        elmt = e;
        ImageIcon img = new ImageIcon(elmt.getPathToTexture());
        setIcon(img);
    }
    public ButtonEdit(AElement e,String s){
        elmt = e;
        ImageIcon img = new ImageIcon(elmt.getPathToTexture());
        setIcon(img);
        this.s = s;

    }

    public void setElmt(AElement elmt) {
        this.elmt = elmt;
        updateTexture();
    }
    private void updateTexture(){
        ImageIcon img = new ImageIcon(elmt.getPathToTexture());
        setIcon(img);
    }

    public AElement getElmt() {
        return elmt;
    }
}
