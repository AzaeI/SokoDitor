package mod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import ctrl.IMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.Position;

/**
 * Created by Yohan on 03/03/2016.
 */
public class Map extends Observable implements IMap {

    private int id;
    private int width;
    private int height;
    private int original;
    private String title;
    private String author;
    private String date;
    private char[][] map;
    private List<Position> boxStart = new ArrayList<>();
    private Position charStart;

    public Map(char[][] _map,int _width, int _height){
        map = _map;
        height = _height;
        width = _width;
    }

    public Map(String name) {
        final String path = "Levels/"+name+".xml";

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(path));
            final Element racine = document.getDocumentElement();
            final NodeList Level = racine.getChildNodes();
            final int nbLevel = Level.getLength();

            boolean isFind = false;
            int indice = 0;

            for (int i = 0; i<nbLevel && !isFind; i++) {
                if (Level.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    indice = i;
                    isFind = true;
                }
            }
            final Element L = (Element) Level.item(indice);

//            this.id = Integer.parseInt(L.getAttribute("Id"));
            this.width = Integer.parseInt(L.getAttribute("Width"));
            this.height = Integer.parseInt(L.getAttribute("Height"));
            this.title = L.getAttribute("Title");
//            this.original = Integer.parseInt(L.getAttribute("Original"));
            this.author = L.getAttribute("Author");
            this.date = L.getAttribute("Date");

            map = new char[height][width];
            for(int j = 0; j < height;j++){
                String s = "L" + (j+1) ;
                String nom = L.getElementsByTagName(s).item(0).getTextContent();
                for (int k=0; k < nom.length();k++){
                    map[j][k] = nom.charAt(k);
                    if(map[j][k] == '@'){
                        charStart = new Position(j,k);
                    }
                    else if(map[j][k] == '$'){
                        boxStart.add(new Position(j,k));
                    }
                }
                for (int k = nom.length(); k < width;k++){
                    map[j][k] = ';';
                }
            }
//            CreateObjectMap();
//            tostring();
//            PrintMap();
        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void PrintMap(){
        for (int i = 0; i<height;i++){
            for (int j = 0; j<width;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
//    private void CreateObjectMap(){
//        mapObject = new AElement[height][width];
//        for (int i = 0; i<height;i++){
//            for (int j = 0; j<width;j++){
//                switch (map[i][j]){
//                    case '#':
//                        mapObject[i][j] = new Wall();
//                        break;
//                    case ',':
//                        mapObject[i][j] = new Floor();
//                        break;
//                    case '@':
//                        mapObject[i][j] = new Character();
//                        break;
//                    case '.':
//                        mapObject[i][j] = new Goal();
//                        break;
//                    case '$':
//                        mapObject[i][j] = new Floor();
//                        break;
//                    case ';':
//                        mapObject[i][j] = new Vide();
//                        break;
//                }
//            }
//        }
//    }

    public char[][] getCharMap(){
        return map;
    }

    public void tostring() {
        System.out.println("Map créée : "+ title);
        System.out.println("Auteur : "+ author);
        System.out.println("Date : "+ date);
        System.out.println("Hauteur : "+ height);
        System.out.println("Largeur : "+ width);
    }

    public Position getCharStart(){
        return charStart;
    }

    public List<Position> getBoxStart(){
        for(Position p : boxStart){
//            System.out.println("Box : "+ p.getX()+ " : "+ p.getY());
        }
        return boxStart;
    }

    public String getTitle(){
        return title;
    }
}