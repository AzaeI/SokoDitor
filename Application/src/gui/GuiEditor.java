package gui;

import ctrl.AElement;
import mod.ButtonEdit;
import mod.Vide;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showMessageDialog;
import static jdk.nashorn.internal.runtime.JSType.isNumber;

/**
 * Created by lab on 13/03/16.
 */
public class GuiEditor  extends JFrame{

    private static boolean isaEditorOpen = false;

    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem help = new JMenuItem("Aide");

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

    private JButton buttonGenerer = new JButton("Générer Carte");

    final static String nameSpriteEdit = "Sprites/SpritEdit";
    final static File ListSpritEdit = new File(nameSpriteEdit);

    private ButtonEdit[][] mapGenerate;

    private String[] listSprite;

    private int hauteur = 5;
    private int largeur = 5;

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
            setLayout(new BorderLayout());

            int parrentHeight = (height*4)/5;
            int parrentWidth  = (width*4)/5;

            mainEditPannel.setPreferredSize(new Dimension((parrentWidth/4)+28,parrentHeight));
            mainEditPannel.setBackground(Color.yellow);
//            mapEditPannel.setPreferredSize(new Dimension((parrentWidth*3)/4,parrentHeight));
            mapEditPannel.setBackground(Color.blue);

            this.add(BorderLayout.LINE_START,mainEditPannel);
            this.add(BorderLayout.CENTER,mapEditPannel);


            /*          PARAM           */
//            int parrentPannelHeight = parrentHeight;
//            int parrentPannelWidth = parrentWidth;

            mainEditPannel.setLayout(new BorderLayout());
//            Paramedit.setPreferredSize(new Dimension(parrentPannelWidth,parrentPannelHeight/4));
            Paramedit.setBackground(Color.CYAN);
            Paramedit.setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.black));

            mainEditPannel.add(BorderLayout.LINE_START,Paramedit);

            initPannelEditionParama();

            /*          SPRITES           */
//            spritesPannel.setPreferredSize(new Dimension(parrentPannelWidth,(parrentPannelHeight*3)/4));
            spritesPannel.setBackground(Color.red);
            spritesPannel.setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.black));
            listSprite = ListSpritEdit.list();
            spritesPannel.setLayout(new GridLayout(listSprite.length,2));

            initSprites(); //get name Sprites
            mainEditPannel.add(BorderLayout.PAGE_END,spritesPannel);

            /*          GRILLE          */
            initGrille();
            initActionListenner();

            menuBar.add(help);
            setJMenuBar(menuBar);
            setResizable(false);
            setVisible(true);
        }else{
            showMessageDialog(null, "Vous ne pouvez pas Editer 2 map en même temps !");
        }
    }
    private void initActionListenner(){
        textFieldHauteurmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String test = textFieldHauteurmap.getText();
                if (isNumber(test)){
                    hauteur = parseInt(test);
                    updateGrille();
                }else{
                    showMessageDialog(null, "Veuillez entrer un nombre !");
                }
            }
        });
        textFieldLargeurmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String test = textFieldHauteurmap.getText();
                if (isNumber(test)){
                    largeur = parseInt(test);
                    updateGrille();
                }else{
                    showMessageDialog(null, "Veuillez entrer un nombre !");
                }
            }
        });
        buttonGenerer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiHelp g = new GuiHelp();
            }
        });
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
    private void initSprites(){
        for(int i = 0; i < listSprite.length;i++) {
            if (listSprite[i].endsWith(".png") == true) {
                String name = listSprite[i].substring(0, listSprite[i].length() - 4);
                ImageIcon img = new ImageIcon(nameSpriteEdit+"/"+name+".png");
                JLabel d = new JLabel();
                d.setIcon(img);
                JPanel a = new JPanel();
                a.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
                a.add(d);
                spritesPannel.add(a);
                JPanel c = new JPanel();
                c.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
                c.add(new JLabel(name));
                spritesPannel.add(c);
            }
        }
    }
    private void initGrille(){
        mapEditPannel.setLayout(new GridLayout(5,5));
        mapGenerate = new ButtonEdit[5][5];
        for (int i = 0; i < 5;i++){ // Parcours longeur
            for (int j = 0; j < 5; j++){ // Parcours Largeur
                AElement v = new Vide();
                mapGenerate[i][j] = new ButtonEdit(v,j,i);
                mapEditPannel.add(mapGenerate[i][j]);
            }
        }
    }
    private void updateGrille(){
        mapEditPannel.setLayout(null);
        mapEditPannel.setLayout(new GridLayout(hauteur,largeur));
        mapGenerate = null;
        mapGenerate = new ButtonEdit[hauteur][largeur];
        for (int i = 0; i < hauteur;i++){ // Parcours longeur
            for (int j = 0; j < largeur; j++){ // Parcours largeur
                AElement v = new Vide();
                mapGenerate[i][j] = new ButtonEdit(v,j,i);
                mapEditPannel.add(mapGenerate[i][j]);
            }
        }
    }
}
