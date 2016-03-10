package mod;

import ctrl.AElement;

/**
 * Created by Yohan on 03/03/2016.
 */
public class Box extends AElement {

    private String pathToTextureOK;

    Box(){
        setPathToTexture("Sprites/BoxKO.png");
        pathToTextureOK = "Sprites/BoxOK.png";
    }

    public String getPathToTextureOK() {
        return pathToTextureOK;
    }
}
