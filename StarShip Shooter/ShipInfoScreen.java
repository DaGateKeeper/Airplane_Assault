import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
public class ShipInfoScreen extends BaseScreen
{

    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/water.jpg") );
        background.setSize(800,640);

        Label title = new Label("Ship Info", BaseGame.labelStyle);
        title.setFontScale(1.5f);
        title.setColor(Color.CYAN);

        BaseActor ship1 = new BaseActor(0,0,mainStage);
        ship1.setAnimator(new Animator("assets/ships/player1a.png"));
        ship1.setSize(36,52);
        
        BaseActor ship2 = new BaseActor(0,0,mainStage);
        ship2.setAnimator(new Animator("assets/ships/player2.png"));
        ship2.setSize(36,52);
        
        BaseActor ship3 = new BaseActor(0,0,mainStage);
        ship3.setAnimator(new Animator("assets/ships/player3.png"));
        ship3.setSize(36,52);
        
        Label SpeedyInfo = new Label("Fastest ship, lowest health. Only able to fire one shot!",BaseGame.labelStyle);
        SpeedyInfo.setFontScale(0.5f);
        SpeedyInfo.setColor(Color.CYAN);

        Label AverageInfo = new Label("Average speed Average Health,fires two shots at a time but has no middle shot", BaseGame.labelStyle);
        AverageInfo.setFontScale(0.5f);
        AverageInfo.setColor(Color.CYAN);

        Label DefenseInfo = new Label("Lowest speed Highest Health. Three shot spread fire.",BaseGame.labelStyle);
        DefenseInfo.setFontScale(0.5f);
        DefenseInfo.setColor(Color.CYAN);

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

    }

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            BaseGame.setActiveScreen( new PlayerSelect() );        
        }
        if (Gdx.input.isKeyJustPressed(Keys.W)){
            BaseGame.setActiveScreen( new CreditsScreen() );        
        }
        if(Gdx.input.isKeyJustPressed(Keys.H)){
            BaseGame.setActiveScreen( new HowToScreen() );
        }
        //if(Gdx.input.isKeyJustPressed(Keys.F)){
        //BaseGame.setActiveScreen(new HighScoreScreen() );
        //adding this in for later.. we will be needing this for people to see the high scores
    }
}