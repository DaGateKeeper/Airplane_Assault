
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class ItemScreen extends BaseScreen
{
    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/water.jpg") );
        // background.setSize(800,800);

        BaseActor bomb = new BaseActor(0,0, mainStage);
        bomb.setAnimator( new Animator ("assets/bomb-shot.png"));
        bomb.setSize(64,64);

        BaseActor piercing = new BaseActor(0,0, mainStage);
        piercing.setAnimator( new Animator ("assets/pierce-shot.png"));
        piercing.setSize(64,64);

        BaseActor BP = new BaseActor(0,0, mainStage);
        BP.setAnimator( new Animator ("assets/BP-item.png"));
        BP.setSize(64,64);

        Label title = new Label("Powerups", BaseGame.labelStyle);
        title.setFontScale(1.5f);
        title.setColor( Color.BLUE );

        Label back = new Label("Press 'S' to Return", BaseGame.labelStyle);
        back.setFontScale(0.5f);
        back.setColor( Color.CYAN );

        Label goal = new Label("Fly straight and kill the enimies.", BaseGame.labelStyle);
        goal.setFontScale(0.5f);
        goal.setColor( Color.CYAN );

        Label powerup2 = new Label("Powerup destroys all enemies ", BaseGame.labelStyle);
        powerup2.setFontScale(0.5f);
        powerup2.setColor( Color.CYAN );

        Label powerup3 = new Label("Powerup pierces enimies", BaseGame.labelStyle);
        powerup3.setFontScale(0.5f);
        powerup3.setColor( Color.CYAN );

        Label Secret = new Label("Collect 10 Blueprints to reveal a secret", BaseGame.labelStyle);
        Secret.setFontScale(0.5f);
        Secret.setColor( Color.CYAN );


        Label tip2 = new Label("However, if you want to see the secret:\n press 'L' in the menu.", BaseGame.labelStyle);
        tip2.setFontScale(0.5f);
        tip2.setColor( Color.CYAN );
 uiTable.add();
        uiTable.add(title);       
        uiTable.row();        uiTable.add();
        uiTable.add(goal);
        uiTable.row();
        
        uiTable.add(bomb);uiTable.add(powerup2);
        uiTable.row();

        uiTable.add(piercing);        uiTable.add(powerup3);
        uiTable.row();

        uiTable.add(BP);        uiTable.add(Secret);
        uiTable.row();
        uiTable.add();
        uiTable.add(back);
        uiTable.row();                
        uiTable.add();
        uiTable.add(tip2);
    }

    public void update(float deltaTime)
    {

        if (Gdx.input.isKeyJustPressed(Keys.S))
            BaseGame.setActiveScreen( new MenuScreen() );    
    }

}