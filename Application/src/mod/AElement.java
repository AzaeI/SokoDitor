package mod;

import ctrl.IElement;

/**
 * Created by Yohan on 10/03/2016.
 */
public abstract class AElement implements IElement {
    private String pathToTexture;

    public String getPathToTexture() {
        return pathToTexture;
    }

    public void setPathToTexture(String pathToTexture) {
        this.pathToTexture = pathToTexture;
    }
}
