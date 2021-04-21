import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
public class PowerUpScreen extends BaseScreen
{

    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/space.jpg") );
        background.setSize(800,800);

        BaseActor multiplyer = new BaseActor(0,0,mainStage);
        multiplyer.setAnimator(new Animator("assets/powerups/x2.png"));
        multiplyer.setSize(50,52);

        BaseActor bomb = new BaseActor(0,0,mainStage);
        bomb.setAnimator(new Animator("assets/powerups/bomb.png"));
        bomb.setSize(50,52);

        BaseActor shieldrepair = new BaseActor(0,0,mainStage);
        shieldrepair.setAnimator(new Animator("assets/powerups/shieldrepair.png"));
        shieldrepair.setSize(50,52);
        
        BaseActor pierceshot = new BaseActor(0,0,mainStage);
        pierceshot.setAnimator(new Animator("assets/powerups/pierceshot.png"));
        pierceshot.setSize(50,52);
        
        BaseActor health = new BaseActor(0,0,mainStage);
        health.setAnimator(new Animator("assets/powerups/health.png"));
        health.setSize(50,52);
        
        Label title = new Label("Powerup Info!", BaseGame.labelStyle);
        title.setFontScale(0.5f);

        Label healthinfo= new Label("Collect these drops to repair your ship's health!",BaseGame.labelStyle2);
        
        healthinfo.setFontScale(0.5f);

        Label shieldinfo= new Label("Collect these drops to repair your ship's shield!", BaseGame.labelStyle2);
        
        shieldinfo.setFontScale(0.5f);

        Label multiplyerinfo = new Label("Collect these drops to multiply your score, \n the effect ends after a certain amount of enemies killed",BaseGame.labelStyle2);
        
        multiplyerinfo.setFontScale(0.5f);

        Label pierceshotinfo = new Label("Collect these drops to gain a pierce shot, \n the shot continues to go through enemies till it leaves the field",BaseGame.labelStyle2);
        
        pierceshotinfo.setFontScale(0.5f);

        Label BombInfo= new Label("Collect these powerups in order to clear the screen of enemies! \n this won't work for the boss though be careful!",BaseGame.labelStyle2);
      
        BombInfo.setFontScale(0.5f);

        Label Blank = new Label(" ", BaseGame.labelStyle2);
        
        Label StartInfo= new Label("Press S to Start!",BaseGame.labelStyle2);
        StartInfo.setColor(Color.RED);
        StartInfo.setFontScale(0.5f);
        
        Label MainMenu = new Label("Press the D key to return to the main menu", BaseGame.labelStyle2);
        MainMenu.setFontScale(0.5f);
        MainMenu.setColor(Color.RED);
        
        Label HowToScreen= new Label("Press A to return to the how to screen", BaseGame.labelStyle2);
        HowToScreen.setFontScale(0.5f);
        HowToScreen.setColor(Color.RED);
        

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(multiplyer,multiplyerinfo);
        uiTable.row();
        uiTable.add(health,healthinfo);
        uiTable.row();
        uiTable.add(shieldrepair,shieldinfo);
        uiTable.row();
        uiTable.add(pierceshot ,pierceshotinfo);
        uiTable.row();
        uiTable.add(bomb,BombInfo);
        uiTable.row();
        uiTable.row();
        uiTable.add(Blank, StartInfo);
        uiTable.row();
        uiTable.add(Blank, MainMenu);
        uiTable.row();
        uiTable.add(Blank, HowToScreen);
        
        

        uiTable.debugCell();

    }

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            BaseGame.setActiveScreen( new LevelScreen() );      
        }
        if (Gdx.input.isKeyJustPressed(Keys.D)){
            BaseGame.setActiveScreen(new MenuScreen() );  
        }
        if (Gdx.input.isKeyJustPressed(Keys.A)){
            BaseGame.setActiveScreen( new LevelScreen() );  
        }
    } 

}
