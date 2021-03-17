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
    public CreditsScreen()
    {
        super("");
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/background.png") );
        background.setSize(800,640);
        
        
         
        Label title = new Label("Credits", BaseGame.labelStyle);
        title.setFontScale(2);
        title.setColor( Color.GREEN );
        
        Label credit1 = new Label("Angelo Morales", BaseGame.labelStyle);
        credit1.setColor( Color.BLUE );
        
        Label credit2 = new Label("Emily Gallagher", BaseGame.labelStyle);
        credit2.setColor( Color.BLUE );
        
        Label credit3 = new Label("Bryan Coene", BaseGame.labelStyle);
        credit3.setColor( Color.BLUE );
        
        Label credit4 = new Label("John Kulins", BaseGame.labelStyle);
        credit4.setColor( Color.BLUE );
        
        BaseActor ret = new BaseActor(0,0, mainStage);
        ret.setAnimator( new Animator("assets/button_press-s-to-return.png"));
        ret.setSize(452, 88);
        
        uiTable.add(title);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.add(credit1);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.add(credit2);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.add(credit3);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.add(credit4);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(ret);
        
    }
    
    @Override
    public void initialize(String mapFileName) { }

    public void update(float deltaTime)
    {
       if (Gdx.input.isKeyJustPressed(Keys.S))
            BaseGame.setActiveScreen( new MenuScreen() ); 
    

    }
}