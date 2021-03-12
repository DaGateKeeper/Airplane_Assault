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

        Label instruct1 = new Label("Use arrow keys to move", BaseGame.labelStyle);
        instruct1.setColor( Color.CYAN );

        Label instruct2 = new Label("use space to fire", BaseGame.labelStyle);
        instruct2.setColor( Color.CYAN );

        Label instruct3 = new Label ("Collect upgrade parts!", BaseGame.labelStyle);
        instruct2.setColor( Color.CYAN );

        Label instruct4 = new Label("Have fun!",BaseGame.labelStyle);
        instruct2.setColor( Color.CYAN );

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
    }

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            BaseGame.setActiveScreen( new PlayerSelect() );        
        }
        if (Gdx.input.isKeyJustPressed(Keys.W)){
            BaseGame.setActiveScreen( new CreditsScreen() );        
        }
        //if(Gdx.input.isKeyJustPressed(Keys.N))
        //{
        //    BaseGame.setActiveScreen(new PowerUpScreen());
        //}
    } 
}
