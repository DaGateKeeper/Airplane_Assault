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
        for (BaseActor ocean : BaseActor.getList(mainStage, "Ocean"))
            ocean.toBack();

        Label title = new Label("Choose Your\n Fighter", BaseGame.labelStyle);
        title.setFontScale(2);
        title.setColor( Color.BLUE );

        Label instructions = new Label("Press 'S' for Speedy", BaseGame.labelStyle);
        instructions.setColor( Color.CYAN );

        Label goal = new Label("Press 'D' for Defence", BaseGame.labelStyle);
        goal.setColor( Color.CYAN );

        Label goal2 = new Label("Press 'A' for Average", BaseGame.labelStyle);
        goal2.setColor( Color.CYAN );

        uiTable.add(title);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.add(instructions);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(goal);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(goal2);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();

        //uiTable.add(credits);
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