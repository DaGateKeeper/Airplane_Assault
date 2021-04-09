import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
public class PlayerSelect extends BaseScreen
{

    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/water.jpg") );
        background.setSize(800,800);

        BaseActor ship1 = new BaseActor(0,0,mainStage);
        ship1.setAnimator(new Animator("assets/ships/player1a.png"));

        BaseActor ship2 = new BaseActor(0,0,mainStage);
        ship2.setAnimator(new Animator("assets/ships/player2.png"));

        BaseActor ship3 = new BaseActor(0,0,mainStage);
        ship3.setAnimator(new Animator("assets/ships/player3.png"));

        Label title = new Label("Ship Info", BaseGame.labelStyle);
        title.setFontScale(1.5f);

        Label SpeedyInfo = new Label("the Fastest ship avaliable \n  has the lowest health of the three \n  can only fire one shot at a time. \n   Press S for the Speedy Ship!  ",BaseGame.labelStyle);
        SpeedyInfo.setFontScale(0.5f);
        

        Label AverageInfo = new Label("Average Class Fighter of the Game \n standard amount of health and shields. \n can fire two shots at a time \n Press A for the Average Ship.", BaseGame.labelStyle);
        AverageInfo.setFontScale(0.5f);

        Label DefenseInfo = new Label("The Slowest Ship in the Game \n has the Highest Health \n Three shot spread fire. \n Press D for the Defensive Ship.",BaseGame.labelStyle);
        DefenseInfo.setFontScale(0.5f);
        

        
        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(ship1);
        uiTable.add(SpeedyInfo);
        uiTable.row();
        uiTable.add(ship2);
        uiTable.add(AverageInfo);
        uiTable.row();
        uiTable.add(ship3);
        uiTable.add(DefenseInfo);
        uiTable.row();
        
        uiTable.debugCell();

    }
    // this code under here is supposed to take the key and depending on what they choose 
    //it will go to the next screen with that ship as the main one.
    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            BaseGame.setActiveScreen( new LevelScreen() );      
        }
        if (Gdx.input.isKeyJustPressed(Keys.D)){
            BaseGame.setActiveScreen( new LevelScreen() );  
        }
        if (Gdx.input.isKeyJustPressed(Keys.A)){
            BaseGame.setActiveScreen( new LevelScreen() );  
        }
    } 
}