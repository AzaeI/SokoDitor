package gui;

import mod.AElement;
import mod.ButtonEdit;
import mod.Map;
import mod.Vide;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showMessageDialog;
import static jdk.nashorn.internal.runtime.JSType.isNumber;

/**
 * Created by lab on 13/03/16.
 */
public class GuiEditor  extends JFrame{

    private static boolean isaEditorOpen = false;

    private static GuiChoiceElmt oldChoiceElmt;

    public static GuiChoiceElmt getOldChoiceElmt() {
        return oldChoiceElmt;
    }

    public static void setOldChoiceElmt(GuiChoiceElmt oldChoiceElmt) {
        GuiEditor.oldChoiceElmt = oldChoiceElmt;
    }

    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem help = new JMenuItem("Aide");
    private JMenuItem back = new JMenuItem("Retour au menu");

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
    private ArrayList<String> nameSprite = new ArrayList<>();

    private int hauteur = 15;
    private int largeur = 15;

    private GridLayout grid = new GridLayout(hauteur,largeur);
    private ActionListener listennerChoiceElemt;

    private GuiEditor itself;

    public GuiEditor(String path){
        this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                isaEditorOpen = false;
                dispose();
            }
        });
        if (!isaEditorOpen){
            itself = this;
            isaEditorOpen = true;
            setResizable(false);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int height = (int) screenSize.getHeight();
            int width = (int) screenSize.getWidth();
            setSize(new Dimension((width*4)/5,(height*4)/5));
            setLayout(new BorderLayout());
            setLocation(width/6,height/10);

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

            initActionListenner();

            if (path == null){
                initGrille(); //init
            }else{
                Map m = new Map (path);
                char ObjectMap[][] = m.getCharMap();
                hauteur = m.getHeight();
                largeur =  m.getWidth();
                grid = new GridLayout(hauteur,largeur);
                mapEditPannel.setLayout(grid);
                mapGenerate = new ButtonEdit[m.getHeight()][m.getWidth()];
                for (int i = 0; i < m.getHeight();i++){
                    for (int j = 0; j < m.getWidth(); j++){
                        mapGenerate[i][j] = new ButtonEdit(ObjectMap[i][j]);
                        mapGenerate[i][j].addActionListener(listennerChoiceElemt);
                        mapEditPannel.add(mapGenerate[i][j]);
                    }
                }
            }

            menuBar.add(back);
            menuBar.add(help);
            setJMenuBar(menuBar);
            setVisible(true);

        }else{
            showMessageDialog(null, "Vous ne pouvez pas Editer 2 map en même temps !");
        }
    }
    public void printGridLayout(){
        for (int i = 0; i < hauteur;i++ ){
            for (int j = 0; j < largeur; j++){
//                System.out.print(mapGenerate[i][j].getElmt().getClass().toString()+" - ");
            }
            System.out.println();
        }

    }
    private void initActionListenner(){
        textFieldHauteurmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String test = textFieldHauteurmap.getText();
                if (isNumber(test)){
                    hauteur = parseInt(test);
                    if (hauteur > 25) showMessageDialog(null, "Veuillez entrer un nombre < 25");
                    else updateGrille();
                }else{
                    showMessageDialog(null, "Veuillez entrer un nombre !");
                }
            }
        });
        textFieldLargeurmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String test = textFieldLargeurmap.getText();
                if (isNumber(test)){
                    largeur = parseInt(test);
                    if (largeur > 25) showMessageDialog(null, "Veuillez entrer un nombre < 25");
                    else{
                        updateGrille();
                    }
                }else{
                    showMessageDialog(null, "Veuillez entrer un nombre !");
                }
            }
        });
        listennerChoiceElemt = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiChoiceElmt f = new GuiChoiceElmt(itself, (ButtonEdit)e.getSource());
            }
        };
        buttonGenerer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                String nameMap = textFieldNomMap.getText();
                if (nameMap.isEmpty()){
                    showMessageDialog(null, "Rentrer un nom de Level Valide");
                    return;
                }
                try {
                    final DocumentBuilder builder = factory.newDocumentBuilder();
                    final Document document = builder.newDocument();
                    final Element racine = document.createElement("SokobanLevels");
                    final Element level = document.createElement("Level");
                    level.setAttribute("Width", largeur + "");
                    level.setAttribute("Height", hauteur + "");
                    level.setAttribute("Title", nameMap);


                    boolean haveAnChar = false;
                    for (int i = 0; i < hauteur; i++){
                        Element LCourrant = document.createElement("L"+(i+1));
                        for (int j = 0; j < largeur; j++){
                            String ligneLv = "";
                            switch (mapGenerate[i][j].getElmt()){
                                case ';' :
                                    ligneLv+= ';';
                                    break;
                                case '$' :
                                    ligneLv+= '$';
                                    break;
                                case '@':
                                    if (!haveAnChar){
                                        haveAnChar = true;
                                        ligneLv+= '@';
                                    }
                                    else{
                                        showMessageDialog(null, "Il ne peut y avoir qu'un seul Personnage...");
                                        return;
                                    }
                                    break;
                                case ',' :
                                    ligneLv+= ',';
                                    break;
                                case '.' :
                                    ligneLv+= '.';
                                    break;
                                case '#' :
                                    ligneLv+= '#';
                                    break;
                            }
                            LCourrant.appendChild(document.createTextNode(ligneLv));
                        }
                        level.appendChild(LCourrant);
                    }
                    if (!haveAnChar){
                        showMessageDialog(null, "Il dois y avoir au moins un personnage...");
                        return;
                    }

                    racine.appendChild(level);
                    document.appendChild(racine);

                    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    final Transformer transformer = transformerFactory.newTransformer();
                    final DOMSource source = new DOMSource(document);

                    File f = new File("Levels/"+nameMap+".xml");
                    //test si fichier présent
                    String[] levelNameList = new File("Levels").list();
                    for(int i = 0; i < levelNameList.length; i++) {
                        if (levelNameList[i].endsWith(".xml")) {
                            if (levelNameList[i].substring(0, levelNameList[i].length() - 4).equals(nameMap)){
                                int n = JOptionPane.showConfirmDialog(
                                        itself,
                                        "Le nom de cette carte est déjà utilisé,\n"+"Voulez vous l'écraser ?",
                                        "Attention",
                                        JOptionPane.YES_NO_OPTION);
                                System.out.println("option : "+n);
                                //0-> oui 1->non
                                if (n == 1) return;
                            }
                        }
                    }

                    final StreamResult sortie = new StreamResult(f);

                    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
                    transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                    transformer.transform(source, sortie);
                    itself.dispose();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                } catch (TransformerConfigurationException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Pour valider la hauteur ou la largeur de la grille,\nil suffit d'appuyer sur entrée, une fois la valeur mise.");
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispatchEvent(new WindowEvent(itself, WindowEvent.WINDOW_CLOSING));
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
                nameSprite.add(name);
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
        grid = new GridLayout(hauteur,largeur);
        mapEditPannel.setLayout(grid);
        mapGenerate = new ButtonEdit[hauteur][largeur];
        for (int i = 0; i < hauteur;i++){
            for (int j = 0; j < largeur; j++){
                char v = ';';
                mapGenerate[i][j] = new ButtonEdit(v);
                mapGenerate[i][j].addActionListener(listennerChoiceElemt);
                mapEditPannel.add(mapGenerate[i][j]);
            }
        }
    }
    private void updateGrille(){
        grid.setColumns(largeur);
        grid.setRows(hauteur);
        mapGenerate = new ButtonEdit[hauteur][largeur];
        mapEditPannel.removeAll();
        for (int i = 0; i < hauteur;i++){
            for (int j = 0; j < largeur; j++){
                char v = ';';
                mapGenerate[i][j] = new ButtonEdit(v);
                mapGenerate[i][j].addActionListener(listennerChoiceElemt);
                mapEditPannel.add(mapGenerate[i][j]);
            }
        }
        mapEditPannel.updateUI();
    }
    private void getInfo(){
//        System.out.println();
//        System.out.println("hauteur : " + hauteur);
//        System.out.println("Largeur : " + largeur);
//        System.out.println();
//        System.out.println("hauteur grid : " + grid.getRows());
//        System.out.println("largeur grid : " + grid.getColumns());

//        for (int i = 0; i < mapGenerate.length;i++){
//            for (int j = 0; j < mapGenerate[i].length;j++){
//                System.out.print(j+".");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println();
    }

    public ArrayList<String> getNameSprite() {
        return nameSprite;
    }
    public String getNameSpriteEdit() {
        return nameSpriteEdit;
    }
}
