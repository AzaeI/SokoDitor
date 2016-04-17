package launcher;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args){
        try {
            new MainFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}