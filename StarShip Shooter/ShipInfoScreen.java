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

        Label SpeedyInfo = new Label("The fastest ship in the game/n but has the lowest health./n can only fire one shot at a time",BaseGame.labelStyle);
        SpeedyInfo.setFontScale(0.5f);
        SpeedyInfo.setColor(Color.CYAN);

        Label AverageInfo = new Label("The Average ship no Strength's no weaknesses /n fires two shots at a time but has no middle shot", BaseGame.labelStyle);
        AverageInfo.setFontScale(0.5f);
        AverageInfo.setColor(Color.CYAN);

        Label DefenseInfo = new Label("The Defensive ship the highest health but the lowest speed /n fires three shots but the shot is spread.",BaseGame.labelStyle);
        DefenseInfo.setFontScale(0.5f);
        DefenseInfo.setColor(Color.CYAN);

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(SpeedyInfo);
        uiTable.row();
        uiTable.add(AverageInfo);
        uiTable.row();
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