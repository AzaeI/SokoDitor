package gui;


import mod.AElement;
import mod.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

/**
 * Created by Yohan on 17/03/2016.
 */
public class GuiChoiceElmt extends JFrame implements Callable {

    private int nbSprite;
    private String nameSpriteEdit;
    private int resolution = 128;
    private GuiChoiceElmt itself;

    private AElement elmtToSubmit;


    protected GuiChoiceElmt(GuiEditor g){
        nbSprite = g.getNameSprite().size();
        nameSpriteEdit = g.getNameSpriteEdit();
        itself = this;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int ScreenHeight = (int) screenSize.getHeight();
        int ScreenWidth = (int) screenSize.getWidth();

        setSize(new Dimension(resolution*4,resolution*((nbSprite/4)+1)));
        setLocation((ScreenWidth*2)/5,(ScreenHeight*2)/5);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,3));

        for (int i = 0; i < nbSprite; i++){
            ButtonEdit a = null;
            ImageIcon img = new ImageIcon(nameSpriteEdit + "/" +g.getNameSprite().get(i) + ".png");
            switch (g.getNameSprite().get(i)){
               /* case "BoxKO":
                    a = new ButtonEdit(new Box(),g.getNameSprite().get(i));
                    break;
                case "Chara":
                    a = new ButtonEdit(new Character(),g.getNameSprite().get(i));
                    break;
                case "Goal":
                    a = new ButtonEdit(new Goal(),g.getNameSprite().get(i));
                    break;
                case "Ground":
                    a = new ButtonEdit(new Floor(),g.getNameSprite().get(i));
                    break;
                case "Vide":
                    a = new ButtonEdit(new Vide(),g.getNameSprite().get(i));
                    break;
                case "Wall":
                    a = new ButtonEdit(new Wall(),g.getNameSprite().get(i));
                    break;*/
            }
            JPanel b = new JPanel();
            a.setIcon(img);
            b.add(a);
            a.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    elmtToSubmit = ((ButtonEdit)e.getSource()).getElmt();
                    itself.dispose();
                }
            });
            mainPanel.add(b);
        }
        this.add(mainPanel);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public Object call() throws Exception {
        return elmtToSubmit;
    }
}
