
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class ControlScreen extends BaseScreen
{
    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/water.jpg") );
        background.setSize(800,800);

        BaseActor subEnemy = new BaseActor(0,0, mainStage);
        subEnemy.setAnimator( new Animator ("assets/enemy-plane.png"));
        subEnemy.setSize(64,64);

        BaseActor heroShip  = new BaseActor(0,0, mainStage);
        heroShip.setAnimator( new Animator ("assets/player-gold.png"));
        heroShip.setSize(64,64);

        Label title = new Label("Controls and Rules", BaseGame.labelStyle);
        title.setFontScale(1.5f);
        title.setColor( Color.BLUE );

        Label back = new Label("Press 'S' to Return", BaseGame.labelStyle);
        back.setFontScale(0.5f);
        back.setColor( Color.CYAN );

        Label controls = new Label("Press 'space' to Shoot\nMovement is Arrows", BaseGame.labelStyle);
        controls.setFontScale(0.5f);
        controls.setColor( Color.CYAN );
        Label Kill = new Label("<---These are the enemies. these fly straight at you.", BaseGame.labelStyle);
        Kill.setFontScale(0.5f);
        Kill.setColor( Color.CYAN );

        Label Health = new Label("If the enemy shoots you,\nyou lose 10 health.\nYou have 200.", BaseGame.labelStyle);
        Health.setFontScale(0.5f);
        Health.setColor( Color.CYAN );

        Label EnemyType = new Label("Red enemies move in circles.\nGreen enemies move in an 's' path.\nViolet enemies move in an hourglass path.", BaseGame.labelStyle);
        EnemyType.setFontScale(0.5f);
        EnemyType.setColor( Color.CYAN );

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(heroShip);
        uiTable.add(controls);
        // uiTable.row();
        uiTable.row();
        uiTable.add(subEnemy);
        uiTable.add(Kill);
        uiTable.row();
        uiTable.add();
        uiTable.add(Health);
        uiTable.row();
        uiTable.add();
        uiTable.add(EnemyType);
        uiTable.row();
        uiTable.add();
        uiTable.add(back);
    }

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S))
            BaseGame.setActiveScreen( new MenuScreen() );

    }
}