import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
/**
 * Write a description of class HowToScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
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

        Label instruct1 = new Label("Use the arrow keys to move", BaseGame.labelStyle);
        instruct1.setFontScale(0.5f);
        instruct1.setColor( Color.CYAN );

        Label instruct2 = new Label("use space button to fire", BaseGame.labelStyle);
        instruct2.setFontScale(0.5f);
        instruct2.setColor( Color.CYAN );

        Label instruct3 = new Label ("Collect parts to upgrade the ship!", BaseGame.labelStyle);
        instruct3.setFontScale(0.5f);
        instruct3.setColor( Color.CYAN );

        Label instruct4 = new Label("Most of all have fun!",BaseGame.labelStyle);
        instruct4.setFontScale(0.5f);
        instruct4.setColor( Color.CYAN );

        Label NextScreen = new Label("Press the N key to learn\n more about the ships!",BaseGame.labelStyle);
        NextScreen.setFontScale(0.5f);
        NextScreen.setColor(Color.CYAN);

        Label StartScreen = new Label("Press the S key to start the game!",BaseGame.labelStyle);
        StartScreen.setFontScale(0.5f);
        StartScreen.setColor(Color.CYAN);

        Label CreditsScreen = new Label("Press the W key for the credits screen",BaseGame.labelStyle);
        CreditsScreen.setFontScale(0.5f);
        CreditsScreen.setColor(Color.CYAN);

        Label MainMenu = new Label("Press the M key to return to the main menu", BaseGame.labelStyle);
        MainMenu.setFontScale(0.5f);
        MainMenu.setColor(Color.CYAN);

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
        uiTable.add(instruct3);
        uiTable.row();
        uiTable.add(instruct4);
        uiTable.row();
        uiTable.row();
        uiTable.add(NextScreen);
        uiTable.row();
        uiTable.row();
        uiTable.add(StartScreen);
        uiTable.row();
        uiTable.row();
        uiTable.add(CreditsScreen);
        uiTable.row();
        uiTable.row();
        uiTable.add(MainMenu);
    }

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            BaseGame.setActiveScreen( new PlayerSelect() );        
        }
        if (Gdx.input.isKeyJustPressed(Keys.W)){
            BaseGame.setActiveScreen( new CreditsScreen() );        
        }
        if(Gdx.input.isKeyJustPressed(Keys.M))
        {
            BaseGame.setActiveScreen(new MenuScreen() );
        }
        //if(Gdx.input.isKeyJustPressed(Keys.N))
        //{
        //    BaseGame.setActiveScreen(new ShipInfoScreen());
        //}
    } 
}
