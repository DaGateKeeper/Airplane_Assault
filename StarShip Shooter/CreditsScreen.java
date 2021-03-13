import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
public class CreditsScreen extends BaseScreen
{
    
    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/water.jpg") );
        background.setSize(800,800);
        for (BaseActor ocean : BaseActor.getList(mainStage, "Ocean"))
            ocean.toBack();

        Label intro = new Label("StarShip Shooter\nwas made by:", BaseGame.labelStyle);
        intro.setColor( Color.CYAN );

        Label CredName1 = new Label("John Kulins", BaseGame.labelStyle);
        CredName1.setColor( Color.CYAN );

        Label CredName2 = new Label("James Meurer", BaseGame.labelStyle);
        CredName2.setColor( Color.CYAN );
        
        Label HowToScreen = new Label("Press H for instructions", BaseGame.labelStyle);
        HowToScreen.setFontScaleX(0.5f);
        HowToScreen.setColor(Color.CYAN);
        //yes i know this is sloppy...this will be fixed later
        
        Label MenuScreen = new Label("Press M for the Main Menu",BaseGame.labelStyle);
        MenuScreen.setFontScaleX(0.5f);
        MenuScreen.setColor(Color.CYAN);
        
        Label StartScreen = new Label("Press S to Start the game!",BaseGame.labelStyle);
        StartScreen.setFontScaleX(0.5f);
        StartScreen.setColor(Color.CYAN);
        

        uiTable.add(intro);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(CredName1);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(CredName2);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.add().row();
        uiTable.add(HowToScreen);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(MenuScreen);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(StartScreen);
        //uiTable.add(credits);

    }

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            BaseGame.setActiveScreen( new  PlayerSelect() );        
        }
        if (Gdx.input.isKeyJustPressed(Keys.H)){
            BaseGame.setActiveScreen( new HowToScreen() );        
        }
        if (Gdx.input.isKeyJustPressed(Keys.M)){
            BaseGame.setActiveScreen( new MenuScreen() );        
        }
    } 
}