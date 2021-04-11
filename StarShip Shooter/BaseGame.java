import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 *  Created when program is launched; 
 *  manages the screens that appear during the game.
 */
public abstract class BaseGame extends Game
{
    /**
     *  Stores reference to game; used when calling setActiveScreen method.
     */
    private static BaseGame game;

    public static LabelStyle labelStyle; // BitmapFont + Color

    public static LabelStyle labelStyle2;

    /**
     *  Called when game is initialized; stores global reference to game object.
     */
    public BaseGame() 
    {        
        game = this;
    }

    /**
     *  Called when game is initialized, 
     *  after Gdx.input and other objects have been initialized.
     */
    public void create() 
    {        
        // parameters for generating a custom bitmap font
        FreeTypeFontGenerator fontGenerator = 
            new FreeTypeFontGenerator(Gdx.files.internal("assets/skirmisher.ttf"));
        FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
        fontParameters.size = 48;
        fontParameters.color = Color.WHITE;
        fontParameters.borderWidth = 4;
        fontParameters.borderColor = Color.BLACK;
        fontParameters.borderStraight = true;
        fontParameters.minFilter = TextureFilter.Linear;
        fontParameters.magFilter = TextureFilter.Linear;

        BitmapFont customFont = fontGenerator.generateFont(fontParameters);

        labelStyle = new LabelStyle();
        labelStyle.font = customFont;

        FreeTypeFontGenerator fontGenerator2 = 
            new FreeTypeFontGenerator(Gdx.files.internal("assets/fox.ttf"));
        FreeTypeFontParameter fontParameters2 = new FreeTypeFontParameter();
        fontParameters2.size = 48;
        fontParameters2.color = Color.WHITE;
        fontParameters2.borderWidth = 4;
        fontParameters2.borderColor = Color.BLACK;
        fontParameters2.borderStraight = true;
        fontParameters2.minFilter = TextureFilter.Linear;
        fontParameters2.magFilter = TextureFilter.Linear;

        BitmapFont customFont2 = fontGenerator2.generateFont(fontParameters2);

        labelStyle2 = new LabelStyle();
        labelStyle2.font = customFont2;
    }

    /**
     *  Used to switch screens while game is running.
     *  Method is static to simplify usage.
     */
    public static void setActiveScreen(BaseScreen s)
    {
        game.setScreen(s);
    }

}
