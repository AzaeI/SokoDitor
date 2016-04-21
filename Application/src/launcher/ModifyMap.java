package launcher;

import gui.GuiEditor;
import gui.GuiMap;
import storage.bean.Level;
import storage.dao.factory.DAOFactory;
import storage.dao.factory.FactoryType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static launcher.ComponentSettings.MENU_BUTTON_TEXT;
import static launcher.ComponentSettings.initializeUploadButton;

/**
 * Created by Yohan on 07/04/2016.
 */
public class ModifyMap extends JPanel {
    private int affiche = 0;
    private int suivant = 0;

    JButton buttonPrec = new JButton("Maps Precedentes");
    JButton buttonSuiv = new JButton("Maps Suivantes");

    String[] levelNameList = new File("Levels").list();
    JButton uploaders[] = new JButton[levelNameList.length];
    JButton buttonReturn = new JButton();

    JButton uploader;
    public ModifyMap(JPanel cards) {

        CardLayout cl = (CardLayout) cards.getLayout();



        this.addPropertyChangeListener(evt -> {

            if(evt.getPropertyName().equals("visible") && evt.getNewValue().equals(true)){
                suivant = 0;
                affiche = 0;
                for(Component c : this.getComponents()){
                    if(c instanceof JButton)
                        this.remove(c);
                }
                uploader = new JButton();
                buttonPrec = new JButton("Maps Precedentes");
                buttonSuiv = new JButton("Maps Suivantes");

                levelNameList = new File("Levels").list();
                uploaders = new JButton[levelNameList.length];

                for(int i = 0; i < uploaders.length; i++){
                    uploader = new JButton();
                    uploader.setName(levelNameList[i]);
                    initializeUploadButton(uploader);
                    uploader.setVisible(false);
                    uploaders[i] = (uploader);
                }

                ArrayList<JButton> maps = new ArrayList<>(levelNameList.length);
                ComponentSettings.initializeBackButton(buttonReturn);
                ComponentSettings.initializeDefaultButton(buttonPrec);
                ComponentSettings.initializeDefaultButton(buttonSuiv);

                int k = 0;
                for(int i = 0; i < levelNameList.length; i++) {
                    if (levelNameList[i].endsWith(".xml")) {
                        maps.add(new JButton(levelNameList[i].substring(0, levelNameList[i].length() - 4)));
                        maps.get(k).addActionListener(e -> new GuiEditor(((JButton)e.getSource()).getText()));
                        k++;

                    }
                }

                for (JButton button : maps) {
                    ComponentSettings.initializeDefaultButton(button);
                }

                this.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                gbc.gridheight = 1;
                gbc.gridwidth = 1;

                gbc.anchor = GridBagConstraints.WEST;
                int j=0;
                this.add(buttonReturn, gbc);
                j++;
                gbc.gridy = j;
                buttonPrec.setVisible(false);
                this.add(buttonPrec, gbc);
                j++;
                for (int i=0; i<maps.size(); i++) {
                    gbc.gridy = j;
                    gbc.anchor = GridBagConstraints.WEST;
                    this.add(maps.get(i), gbc);
                    gbc.anchor = GridBagConstraints.EAST;
                    this.add(uploaders[i], gbc);
                    j++;
                }

                gbc.gridy = j;
                this.add(buttonSuiv, gbc);
                if(maps.size() < 5)
                    buttonSuiv.setVisible(false);

                this.setBackground(ComponentSettings.MENU_BACKGROUND);

                buttonReturn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cl.show(cards, ComponentSettings.EDITOR_TITLE);
                    }
                });

                buttonPrec.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        affiche -= 4;
                        suivant--;
                        display_options(maps);
                    }
                });

                buttonSuiv.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        affiche += 4;
                        suivant++;
                        display_options(maps);
                    }
                });

                display_options(maps);
                if(MainFrame.isConnected()){

                    for(int i = 0; i < uploaders.length; i++){

                        File f = new File("Levels/"+uploaders[i].getName());
                        Level level = new Level();
                        level.setRank(0);
                        level.setIs_genuine(true);
                        level.setName(uploaders[i].getName());
                        level.setUser(MainFrame.getUser().getId());
                        try {
                            level.setFile(new FileInputStream(f));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }


                        uploaders[i].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {


                                if(DAOFactory.getFactory(FactoryType.MYSQL_DAO).getLevelDAO().create(level) != null){
                                    DAOFactory.getFactory(FactoryType.MYSQL_DAO).getFeedBackDAO().commit();
                                }
                                else {
                                    DAOFactory.getFactory(FactoryType.MYSQL_DAO).getFeedBackDAO().rollback();
                                }
                            }
                        });
                        uploaders[i].setVisible(true);


                    }

                }
                else{
                    for (JButton u : uploaders){
                        u.setVisible(false);
                    }
                }
            }
        });

        this.setVisible(true);
    }

    public void setVisible(boolean b) {
        boolean visible = isVisible();
        super.setVisible(b);
        firePropertyChange("visible", visible, b);
    }

    void display_options(ArrayList<JButton> yohanLeNoob) { //Ou canlay Nom de variable plus parlant que "options" dans ce contexte
        for (int i = 0; i< yohanLeNoob.size(); i++) {
            yohanLeNoob.get(i).setVisible(false);
            uploaders[i].setVisible(false);
        }

        if(suivant != 0)
            buttonPrec.setVisible(true);
        else{

            buttonPrec.setVisible(false);
        }

        if((yohanLeNoob.size() - affiche-4) < 1 )
            buttonSuiv.setVisible(false);
        else {
            buttonSuiv.setVisible(true);
        }

        for(int i = 0; i< yohanLeNoob.size() && i < affiche ; i++){
            yohanLeNoob.get(i).setVisible(false);
            if(MainFrame.isConnected())
                uploaders[i].setVisible(false);
        }

        for (int i = affiche; i< yohanLeNoob.size() && i < affiche+4; i++) {
            yohanLeNoob.get(i).setVisible(true);
            if(MainFrame.isConnected())
                uploaders[i].setVisible(true);
        }


    }
}
