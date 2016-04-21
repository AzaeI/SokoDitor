package launcher;


import storage.bean.Feedback;
import storage.bean.Level;
import storage.bean.User;
import storage.dao.DAO;
import storage.dao.factory.DAOFactory;
import storage.dao.factory.FactoryType;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

class Downloader extends JPanel {

    Downloader(JPanel cards) {


        ArrayList<String> map = new ArrayList<>();
        ArrayList<String> user = new ArrayList<>();
        ArrayList<Integer> score = new ArrayList<>();
        ArrayList<JButton> download = new ArrayList<>();

        ArrayList<JButton> rating = new ArrayList<>();
        ArrayList<JButton> feedback = new ArrayList<>();
        ArrayList<JButton> playerFeedback = new ArrayList<>();

        ArrayList<Level> levels = DAOFactory.getFactory(FactoryType.MYSQL_DAO).getLevelDAO().list(new Level());
        User u = new User();
        int i = 0;
        for (Level l : levels) {
            map.add(l.getName());
            u.setId(l.getUser());
            user.add(DAOFactory.getFactory(FactoryType.MYSQL_DAO).getUserDAO().get(u).getUsername());
            score.add(l.getRank());

            if (MainFrame.isConnected()) {
                rating.add(new JButton("Votre avis"));
                rating.get(i).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
            }


            feedback.add(new JButton("avis"));

            feedback.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea feedText = new TextArea();
                    feedText.setPreferredSize(new Dimension(200, 60));
                    add(feedText);
                }
            });


            playerFeedback.add(new JButton("avis des joueurs"));
            download.add(new JButton("Download"));
            download.get(i).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    Level level = DAOFactory.getFactory(FactoryType.MYSQL_DAO).getLevelDAO().get(l);
                    OutputStream outStream;
                    File file;
                    try {
                        if (level == null)
                            throw new Exception();

                        file = new File("Levels/" + level.getName());

                        // if file doesnt exists, then create it
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        byte[] buffer = new byte[level.getFile().available()];
                        level.getFile().read(buffer);

                        outStream = new FileOutputStream(file);
                        outStream.write(buffer);

                        outStream.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
            i++;
        }
        Container listCont = new Container();
        JPanel listPane = new JPanel();

        JScrollPane scrollPane = new JScrollPane();

        listCont.setLayout(new GridLayout(map.size(), 4));

        for (int k = 0; k < map.size(); k++) {
            listCont.add(new JLabel(map.get(k)));
            listCont.add(new JLabel(user.get(k)));
            listCont.add(new JLabel(String.valueOf(score.get(k))));
            listCont.add(download.get(k));
            listCont.add(feedback.get(k));
            listCont.add(playerFeedback.get(k));

        }

        scrollPane.setViewportView(listCont);

        JPanel title = new JPanel(new GridLayout());
        title.add(new JLabel("Titre"));
        title.add(new JLabel("Créateur"));
        title.add(new JLabel("Score"));
        title.add(new JLabel("Down")); // à remplacer par une image
        title.add(new JLabel("Votre Avis")); // à remplacer par une image
        title.add(new JLabel("Avis des joueur")); // à remplacer par une image

        scrollPane.setPreferredSize(new Dimension(300, 300));

        listPane.setLayout(new GridBagLayout());
        GridBagConstraints listgbc = new GridBagConstraints();

        listgbc.gridy = 0;
        listgbc.gridheight = 1;
        listgbc.gridwidth = 1;
        listPane.add(title, listgbc);

        listgbc.gridy = 1;
        listgbc.gridheight = 1;
        listgbc.gridwidth = 1;
        listPane.add(scrollPane, listgbc);


        CardLayout cl = (CardLayout) cards.getLayout();

        //Initialisation des boutons
        JButton returnButton = new JButton();
        ComponentSettings.initializeBackButton(returnButton);


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        /********************************************************
         *                      RETURN BUTTON
         ********************************************************/

        gbc.gridy = 0;
        //La taille en hauteur et en largeur
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(returnButton, gbc);


        gbc.gridy = 1;
        this.add(listPane, gbc);


        //Coloration du fond du Menu
        this.setBackground(ComponentSettings.MENU_BACKGROUND);

        //Actions Listeners
        returnButton.addActionListener(e -> cl.show(cards, ComponentSettings.MENU_TITLE));

        this.setVisible(true);
    }


    private void layoutInit() {

        JButton b1 = new JButton("Salut");
        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        b1.setContentAreaFilled(true);

        JButton b2 = new JButton("Salut >2");
        b2.setBorderPainted(false);
        b2.setFocusPainted(false);
        b2.setContentAreaFilled(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        //La taille en hauteur et en largeur
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(b1, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        //La taille en hauteur et en largeur
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(b2, gbc);
        //---------------------------------------------
        /*gbc.gridx = 1;
        content.add(cell2, gbc);
        //---------------------------------------------
        gbc.gridx = 2;
        content.add(cell3, gbc);
        //---------------------------------------------
        //Cette instruction informe le layout que c'est une fin de ligne
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 3;
        content.add(cell4, gbc);
        //---------------------------------------------
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        //Celle-ci indique que la cellule se réplique de façon verticale
        gbc.fill = GridBagConstraints.VERTICAL;
        content.add(cell5, gbc);
        //---------------------------------------------
        gbc.gridx = 1;
        gbc.gridheight = 1;
        //Celle-ci indique que la cellule se réplique de façon horizontale
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        content.add(cell6, gbc);
        //---------------------------------------------
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        content.add(cell7, gbc);
        //---------------------------------------------
        gbc.gridx = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        content.add(cell8, gbc);*/
    }

}
