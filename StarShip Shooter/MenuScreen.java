import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
public class MenuScreen extends BaseScreen
{

    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/water.jpg") );
        background.setSize(800,800);
        for (BaseActor ocean : BaseActor.getList(mainStage, "Ocean"))
            ocean.toBack();

        Label title = new Label(" Space\nAssault", BaseGame.labelStyle);
        title.setFontScale(2);
        title.setColor( Color.WHITE);

        Label instructions = new Label("Press 'S' to Start", BaseGame.labelStyle2);
        instructions.setColor( Color.WHITE );
        instructions.setFontScale(1.5f);

        Label goal2 = new Label("Press 'H' for Instructions", BaseGame.labelStyle2);
        goal2.setColor( Color.WHITE );
        goal2.setFontScale(1.5f);

        Label credits = new Label("Press 'W' for Credits", BaseGame.labelStyle2);
        credits.setColor( Color.WHITE);
        credits.setFontScale(1.5f);

        uiTable.add(title);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.add(instructions);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(goal2);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(credits);
        uiTable.row();
        //uiTable.add(credits);
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
        if(Gdx.input.isKeyJustPressed(Keys.H)){
            BaseGame.setActiveScreen( new HowToScreen() );
        }
        //if(Gdx.input.isKeyJustPressed(Keys.F)){
        //BaseGame.setActiveScreen(new HighScoreScreen() );
        //adding this in for later.. we will be needing this for people to see the high scores
    }
}
