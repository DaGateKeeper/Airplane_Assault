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
        background.setAnimator( new Animator("assets/space.jpg") );
        background.setSize(800,800);
        for (BaseActor space : BaseActor.getList(mainStage, "Space"))
            space.toBack();

        Label title = new Label(" Space\nAssault", BaseGame.labelStyle);
        title.setFontScale(2);
        title.setColor( Color.WHITE);

        Label instructions = new Label("Press 'S' to Start", BaseGame.labelStyle2);
        instructions.setColor( Color.WHITE );
        instructions.setFontScale(1f);

        Label goal2 = new Label("Press 'E' for Instructions", BaseGame.labelStyle2);
        goal2.setColor( Color.WHITE );
        goal2.setFontScale(1f);

        Label credits = new Label("Press 'D' for Credits", BaseGame.labelStyle2);
        credits.setColor( Color.WHITE);
        credits.setFontScale(1f);

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
    }

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            BaseGame.setActiveScreen( new PlayerSelect() );        
        }
        if (Gdx.input.isKeyJustPressed(Keys.D)){
            BaseGame.setActiveScreen( new CreditsScreen() );        
        }
        if(Gdx.input.isKeyJustPressed(Keys.E)){
            BaseGame.setActiveScreen( new HowToScreen() );
        }

    }
}
