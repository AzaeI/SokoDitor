package launcher;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by elekhyr on 01/04/16.
 */
public class ComponentSettings {


    private static Font setFont(String name, int size){
        Font f = new Font("Calibri", Font.TRUETYPE_FONT, size);
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/"+name+".ttf"));
            f = f.deriveFont(Font.TRUETYPE_FONT, size);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    /*************************************************************************
     *                              GENERAL
     *************************************************************************/

    static final Color BACKGROUND_COLOR = Color.BLUE;
    static final Color FONT_COLOR = Color.cyan;
    static final Color BUTTON_BACKGROUND_COLOR = Color.GRAY;
    static final Font FONT = setFont("title", 72);

    static final Color FOREGROUND_COLOR = Color.white;
    static final int X_SIZE = 600;
    static final int Y_SIZE = 600;




    /*************************************************************************
     *                              MENU
     *************************************************************************/

    static final String MENU_TITLE  = "Menu";
    static final String MENU_BUTTON_TEXT = "Menu";
    static final Color MENU_BACKGROUND = Color.black;

    /*************************************************************************
     *                              PLAYER
     *************************************************************************/

    static final String PLAYER_TITLE = "Player";
    static final String PLAY_BUTTON_TEXT = "Jouer";


    /*************************************************************************
     *                              EDITOR
     *************************************************************************/

    static final String EDITOR_TITLE            = "Editor";
    static final String EDITOR_BUTTON_TEXT      = "Editer";
    static final String NEW_MAP_TEXT_BUTTON     = "Nouvelle Carte";
    static final String MODIFY_MAP_TEXT_BUTTON  = "Modifier Carte existante";


    /*************************************************************************
     *                              SETTINGS
     *************************************************************************/

    static final String SETTINGS_TITLE  = "Settings";
    static final String SETTINGS_BUTTON_TEXT = "Options";

    /*************************************************************************
     *                              LOGIN
     *************************************************************************/

    static final String LOGIN_TITLE  = "Log in";
    static final String LOGIN_BUTTON_TEXT = "Se connecter";


    /*************************************************************************
     *                              SIGNUP
     *************************************************************************/

    static final String SIGNUP_TITLE  = "Sign up";
    static final String SIGNUP_BUTTON_TEXT = "S'inscrire";


    /*************************************************************************
     *                              EXIT
     *************************************************************************/

    static final String EXIT_BUTTON_TEXT = "Quitter Sokoditor";

}
