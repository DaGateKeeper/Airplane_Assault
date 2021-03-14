
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
        background.setAnimator( new Animator("assets/images/water.jpg") );
        background.setSize(800,600);
        
      
       BaseActor subEnemy = new BaseActor(0,0, mainStage);
       subEnemy.setAnimator( new Animator ("assets/images/subenemy.png"));
       subEnemy.setSize(64,64);
       
       BaseActor subHeal = new BaseActor(0,0, mainStage);
       subHeal.setAnimator( new Animator ("assets/images/subheal.png"));
       subHeal.setSize(64,64);

       BaseActor heroShip  = new BaseActor(0,0, mainStage);
       heroShip.setAnimator( new Animator ("assets/images/sub.png"));
       heroShip.setSize(64,64);
       
       BaseActor blank  = new BaseActor(0,0, mainStage);
       blank.setAnimator( new Animator ("assets/images/blank-item.png"));
       blank.setSize(64,64);

        Label title = new Label("Controls and Rules", BaseGame.labelStyle);
        title.setFontScale(1.5f);
        title.setColor( Color.BLUE );

        Label back = new Label("Press 'S' to Return", BaseGame.labelStyle3);
        back.setFontScale(0.5f);
        back.setColor( Color.CYAN );
        
        Label controls = new Label("Press 'space' to Shoot Missiles and Launch Bombs", BaseGame.labelStyle3);
        controls.setFontScale(0.5f);
        controls.setColor( Color.CYAN );
        
         Label enemyLabel = new Label("Enemy ships will damage core", BaseGame.labelStyle3);
         enemyLabel.setFontScale(0.5f);
         enemyLabel.setColor( Color.CYAN );
        
         Label healLabel = new Label("Healing Ships repair the core, don't Shoot them!", BaseGame.labelStyle3);
         healLabel.setFontScale(0.5f);
         healLabel.setColor( Color.CYAN );
        
        
        
        //Label controls = new Label("Arrow Keys Move Turtle", BaseGame.labelStyle2);
       // instructions.setColor( Color.CYAN);
        
        //Label credits = new Label("Made by Angelo Morales", BaseGame.labelStyle2);
        //instructions.setColor( Color.CYAN );

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(heroShip);
        uiTable.add(controls);
       // uiTable.row();
        uiTable.row();
        uiTable.add(subEnemy);
        uiTable.add(enemyLabel);
        uiTable.row();
        uiTable.add(subHeal);
        uiTable.add(healLabel).pad(10);
        uiTable.row();
        uiTable.add(blank);
        uiTable.add(back).pad(10);
        uiTable.row();
       // uiTable.add(paddle);
       // uiTable.add(instructions).pad(5);
        uiTable.row();
        //uiTable.add(instructions);
        uiTable.row();
        //uiTable.add(credits);
        

    }

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S))
            BaseGame.setActiveScreen( new MenuScreen() );

    }
}