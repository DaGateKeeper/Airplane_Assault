import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class CreditsScreen extends BaseScreen
{
    public void initialize()
    {
         BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/images/water.jpg") );
        background.setSize(800,600);

        

        Label title = new Label("Credits", BaseGame.labelStyle);
        title.setFontScale(2);
        title.setColor( Color.BLUE );

      
    
        Label credits1 = new Label("Angelo Morales", BaseGame.labelStyle);
        credits1.setColor( Color.CYAN );
        
        Label credits2 = new Label("Emily Gallagher", BaseGame.labelStyle);
        credits2.setColor( Color.CYAN );
        
        Label credits3 = new Label("Brian Coene", BaseGame.labelStyle);
        credits3.setColor( Color.CYAN );
        
        Label credits4 = new Label("John Kulins", BaseGame.labelStyle);
        credits4.setColor( Color.CYAN );

        Label back = new Label("Press 'S' to Return", BaseGame.labelStyle);
        back.setColor( Color.CYAN );

        uiTable.add(title);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.add(credits1);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(credits2);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(credits3);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(credits4);
        uiTable.row();
        uiTable.add(back);
        
        

    }

    public void update(float deltaTime)
    {
      if (Gdx.input.isKeyJustPressed(Keys.S))
            BaseGame.setActiveScreen( new MenuScreen() );    
    }
}