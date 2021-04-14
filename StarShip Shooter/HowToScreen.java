import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
/**
 * Write a description of class HowToScreen here.
 *
 * James Meurer and John Kulins
 * 4/9/2021
 */
public class HowToScreen extends BaseScreen
{
    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/water.jpg") );
        background.setSize(800,800);

        for(BaseActor ocean : BaseActor.getList(mainStage, "Ocean"))
            ocean.toBack();

        Label intro = new Label("How to Play!: ", BaseGame.labelStyle);

        Label instruct1 = new Label("Use the arrow keys to move", BaseGame.labelStyle2);
        instruct1.setFontScale(0.5f);
        instruct1.setColor( Color.WHITE );

        Label instruct2 = new Label("use space button to fire", BaseGame.labelStyle2);
        instruct2.setFontScale(0.5f);
        instruct2.setColor( Color.WHITE );

        Label instruct3 = new Label("Most of all have fun!",BaseGame.labelStyle2);
        instruct3.setFontScale(0.5f);
        instruct3.setColor( Color.WHITE );

        Label StartScreen = new Label("Press the S key to start the game!",BaseGame.labelStyle2);
        StartScreen.setFontScale(0.5f);
        StartScreen.setColor(Color.RED);

        Label CreditsScreen = new Label("Press the W key for the credits screen",BaseGame.labelStyle2);
        CreditsScreen.setFontScale(0.5f);
        CreditsScreen.setColor(Color.RED);

        Label MainMenu = new Label("Press the D key to return to the main menu", BaseGame.labelStyle2);
        MainMenu.setFontScale(0.5f);
        MainMenu.setColor(Color.RED);
        
        Label PowerUpScreen = new Label("Press A to learn more about the powerups!",BaseGame.labelStyle2);
        PowerUpScreen.setFontScale(0.5f);
        PowerUpScreen.setColor(Color.RED);

        uiTable.add(intro);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(instruct1);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(instruct2);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(instruct3);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(StartScreen);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(CreditsScreen);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(MainMenu);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(PowerUpScreen);
        uiTable.debugCell();
    }

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            BaseGame.setActiveScreen( new PlayerSelect() );        
        }
        if (Gdx.input.isKeyJustPressed(Keys.W)){
            BaseGame.setActiveScreen( new CreditsScreen() );        
        }
        if(Gdx.input.isKeyJustPressed(Keys.D))
        {
            BaseGame.setActiveScreen(new MenuScreen() );
        }
        if(Gdx.input.isKeyJustPressed(Keys.A))
        {
            BaseGame.setActiveScreen(new PowerUpScreen() );
        }
    } 
}
