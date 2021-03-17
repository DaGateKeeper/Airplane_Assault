import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;

public class MenuScreen extends BaseScreen
{
    public static ArrayList<LevelScreen> roomList;
    
    public MenuScreen()
    {
        super("");
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/background.png") );
        background.setSize(800,640);
        
        BaseActor start = new BaseActor(0,0, mainStage);
        start.setAnimator( new Animator("assets/button_press-s-to-start.png"));
        start.setSize(452, 88);
        
        BaseActor info = new BaseActor(0,0, mainStage);
        info.setAnimator( new Animator("assets/button_press-d-for-info.png"));
        info.setSize(452, 88);
        
        
        BaseActor credits = new BaseActor(0,0, mainStage);
        credits.setAnimator( new Animator("assets/button_press-w-for-credits.png"));
        credits.setSize(452, 88);
        
        
        uiTable.add(start);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.row();
        uiTable.add(info);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.row();
        uiTable.add(credits);
        
        
        
    }
    
    @Override
    public void initialize(String mapFileName) {
    }

    public void update(float deltaTime)
    {
        roomList = new ArrayList<LevelScreen>();
        roomList.add( new LevelScreen("assets/room0.map") );
        roomList.add( new LevelScreen("assets/room1.map") );
        roomList.add( new LevelScreen("assets/room2.map") );
        
        if (Gdx.input.isKeyJustPressed(Keys.S))
            BaseGame.setActiveScreen( roomList.get(0) ); 
        if (Gdx.input.isKeyJustPressed(Keys.D))
            BaseGame.setActiveScreen( new InfoScreen() ); 
        if(Gdx.input.isKeyJustPressed(Keys.W))
            BaseGame.setActiveScreen( new CreditsScreen());

    }
}