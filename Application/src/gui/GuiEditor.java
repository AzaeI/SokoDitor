package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by lab on 13/03/16.
 */
public class GuiEditor  extends JFrame{

    private static boolean isaEditorOpen = false;
    private JPanel mainEditPannel = new JPanel();
    private JPanel mapEditPannel = new JPanel();
    private JPanel Paramedit = new JPanel();
    private JPanel spritesPannel = new JPanel();
    private JLabel nomMap = new JLabel("Nom de la carte : ",SwingConstants.RIGHT);
    private JLabel hauteurMap = new JLabel("Hauteur : ",SwingConstants.RIGHT);
    private JLabel largeurMap = new JLabel("Largeur : ",SwingConstants.RIGHT);
    private JTextField textFieldNomMap = new JTextField(15);
    private JTextField textFieldHauteurmap = new JTextField(15);
    private JTextField textFieldLargeurmap = new JTextField(15);
    //private JButton buttonhauteurMap = new JButton("Appliquer");
    //private JButton buttonLargeurMap = new JButton("Appliquer");
    private JButton buttonGenerer = new JButton("Générer Carte");



    public GuiEditor(){
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                isaEditorOpen = false;
                dispose();
            }
        });
        if (!isaEditorOpen){
            isaEditorOpen = true;
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int height = (int) screenSize.getHeight();
            int width = (int) screenSize.getWidth();
            setSize(new Dimension((width*4)/5,(height*4)/5));


            int parrentHeight = (height*4)/5;
            int parrentWidth  = (width*4)/5;

            mainEditPannel.setSize(new Dimension(parrentWidth/4,parrentHeight));
            mainEditPannel.setBackground(Color.yellow);
            mapEditPannel.setSize(new Dimension((parrentWidth*3)/4,parrentHeight));
            mapEditPannel.setBackground(Color.blue);

            this.add(mainEditPannel);
            this.add(mapEditPannel);


            int parrentPannelHeight = parrentHeight;
            int parrentPannelWidth = parrentWidth;

            Paramedit.setPreferredSize(new Dimension(parrentPannelWidth,parrentPannelHeight/4));
            Paramedit.setBackground(Color.CYAN);
            Paramedit.setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.black));
            mainEditPannel.add(Paramedit);


            textFieldHauteurmap.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                }
            });
            textFieldLargeurmap.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                }
            });
            buttonGenerer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                }
            });

            initPannelEditionParama();

            spritesPannel.setPreferredSize(new Dimension(parrentPannelWidth,(parrentHeight*3)/4));
            spritesPannel.setBackground(Color.red);
            mainEditPannel.add(spritesPannel);

            setVisible(true);
        }else{
            showMessageDialog(null, "Vous ne pouvez pas Editer 2 map en même temps !");
        }
    }

    private void initPannelEditionParama(){
        Paramedit.setLayout(new GridLayout(4,0));

        JPanel nomMapPannel = new JPanel();
        nomMapPannel.setLayout(new GridLayout(1,1));
        nomMapPannel.add(nomMap);
        nomMapPannel.add(textFieldNomMap);

        Paramedit.add(nomMapPannel);

        JPanel hauteurMapPannel = new JPanel();
        hauteurMapPannel.setLayout(new GridLayout(1,1));
        hauteurMapPannel.add(hauteurMap);
        hauteurMapPannel.add(textFieldHauteurmap);

        Paramedit.add(hauteurMapPannel);

        JPanel largeurMapPannel = new JPanel();
        largeurMapPannel.setLayout(new GridLayout(1,1));
        largeurMapPannel.add(largeurMap);
        largeurMapPannel.add(textFieldLargeurmap);

        Paramedit.add(largeurMapPannel);

        Paramedit.add(buttonGenerer);

    }

}
