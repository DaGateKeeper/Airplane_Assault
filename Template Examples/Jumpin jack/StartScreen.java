import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class StartScreen extends BaseScreen
{
    public StartScreen()
    {
        super("");
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/background.png") );
        background.setSize(800,640);
        
        BaseActor ckoala = new BaseActor(0,0, mainStage);
        ckoala.setAnimator( new Animator("assets/koala-c.png") );
        ckoala.setSize(400,200);
        
        Label title = new Label(" Jumping  Jack", BaseGame.labelStyle);
        title.setFontScale(2);
        title.setColor( Color.GREEN );
        
        BaseActor instructions = new BaseActor(0,0, mainStage);
        instructions.setAnimator( new Animator("assets/button_press-s-to-start.png"));
        instructions.setSize(452, 88);
        uiTable.add(title);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.row();
        uiTable.add(instructions);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.row();
        uiTable.add(ckoala);
    }
    
    @Override
    public void initialize(String mapFileName) { }

    public void update(float deltaTime)
    {
         if (Gdx.input.isKeyJustPressed(Keys.S))
            BaseGame.setActiveScreen( new MenuScreen() ); 

    }
}