package gui;

import ctrl.IGame;
import mod.*;
import mod.Character;

import javax.swing.*;
import javax.swing.Box;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by DoubleVV on 07/04/2016.
 */
public class GuiGame extends JComponent implements Observer {

    private GuiMap gMap;
    private List<GuiBox> guiBoxes = new ArrayList<>();
    private GuiCharacter player;
    private IGame iGame;

    private long tempsDebut;
    private long tempsFin;

    JFrame window = new JFrame("Sokoban");
    private JButton quit = new JButton("Revenir au menu principal");

    private JLabel score = new JLabel();
    private JLabel time = new JLabel();
    private JLabel label_score = new JLabel("Score : ");
    private JLabel label_time = new JLabel("Time : ");

    static boolean isOpen = false;

    public static int resolution = 64;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gMap.draw(g);
        for (GuiBox p : guiBoxes) {
            p.draw(g);
        }
        player.draw(g);
    }

    public GuiGame(IGame _ig) {

        tempsDebut = System.currentTimeMillis();

        iGame = _ig;

        iGame.addObserver(this);

        /*{
            @Override public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            isOpen = false;
            dispose();*/
//        }};

        if (!(isOpen)) {
            initialize();

            player.setFocusable(true);
            window.addKeyListener(player);
            window.add(this);
            window.setSize(new Dimension(iGame.getMap().getWidth() * resolution, (iGame.getMap().getHeight() * resolution) + 32));
//            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setResizable(false);
            window.setFocusable(true);
            window.setVisible(true);
        } else {
            showMessageDialog(null, "Vous ne pouvez pas lancer deux parties en même temps !");
        }
    }

    private void initialize() {
        gMap = new GuiMap(iGame.getMap());

        for (int i = 0; i < iGame.getMap().getBoxStart().size(); i++) {
            iGame.addBoxes(new mod.Box(iGame.getMap().getBoxStart().get(i), iGame.getGame()));
            guiBoxes.add(new GuiBox(iGame.getBoxes().get(i)));
        }
        for (mod.Box b : iGame.getBoxes()) {
            b.setBoxes(iGame.getBoxes());
        }

        iGame.setCharacter(new Character(iGame.getMap().getCharMap(), iGame.getMap().getCharStart(), iGame.getBoxes()));

        player = new GuiCharacter(iGame.getCharacter(), this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (iGame.getState()) {

            JDialog endGame = new JDialog();
            window.setFocusable(false);
            endGame.setResizable(false);
            endGame.setSize(300, 300);
            endGame.setTitle("C'est gagné !!");
            endGame.setLocationRelativeTo(null);

            endGame.setLayout(new GridLayout(3, 2));

            tempsFin = System.currentTimeMillis();
            float seconds = (tempsFin - tempsDebut) / 1000F;

            score.setText("TEST");
            time.setText(String.valueOf(seconds) + " secondes");

            endGame.add(label_score);
            endGame.add(score);
            endGame.add(label_time);
            endGame.add(time);
            endGame.add(quit);


            endGame.setVisible(true);
            quit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    endGame.dispose();
                    window.dispose();
                }
            });
        }
    }
}

