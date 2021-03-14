
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class InstructionScreen extends BaseScreen
{
    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/images/water.jpg") );
        background.setSize(800,600);

       BaseActor tenMore = new BaseActor(0,0, mainStage);
       tenMore.setAnimator( new Animator ("assets/images/extra-ammo-15.png"));
       tenMore.setSize(64,64);
       
       BaseActor core = new BaseActor(0,0, mainStage);
       core.setAnimator( new Animator ("assets/images/the-core.png"));
       core.setSize(64,64);
       
       BaseActor bomb = new BaseActor(0,0, mainStage);
       bomb.setAnimator( new Animator ("assets/images/bomb-shot.png"));
       bomb.setSize(64,64);
       
       BaseActor piercing = new BaseActor(0,0, mainStage);
       piercing.setAnimator( new Animator ("assets/images/pierce-shot.png"));
       piercing.setSize(64,64);
       
       BaseActor rapidFire = new BaseActor(0,0, mainStage);
       rapidFire.setAnimator( new Animator ("assets/images/rapid-fire.png"));
       rapidFire.setSize(64,64);
       
       BaseActor normalShot  = new BaseActor(0,0, mainStage);
       normalShot.setAnimator( new Animator ("assets/images/normal-shot.png"));
       normalShot.setSize(64,64);

       BaseActor heroShip  = new BaseActor(0,0, mainStage);
       heroShip.setAnimator( new Animator ("assets/images/sub.png"));
       heroShip.setSize(64,64);

        Label title = new Label("Powerups", BaseGame.labelStyle);
        title.setFontScale(1.5f);
        title.setColor( Color.BLUE );

        Label back = new Label("Press 'S' to Return", BaseGame.labelStyle2);
        back.setFontScale(0.5f);
        back.setColor( Color.CYAN );
        
        Label goal = new Label("Defend the Core!", BaseGame.labelStyle2);
        goal.setFontScale(0.5f);
        goal.setColor( Color.CYAN );
        
         Label powerup1 = new Label("Powerup gives +15 Ammo", BaseGame.labelStyle2);
         powerup1.setFontScale(0.5f);
         powerup1.setColor( Color.CYAN );
        
         Label powerup2 = new Label("Powerup destroys all Subs but gains no points", BaseGame.labelStyle2);
         powerup2.setFontScale(0.5f);
         powerup2.setColor( Color.CYAN );
        
        Label powerup3 = new Label("Powerup pierces Subs", BaseGame.labelStyle2);
        powerup3.setFontScale(0.5f);
        powerup3.setColor( Color.CYAN );
        
        Label powerup4 = new Label("Powerup allows Rapid-shots", BaseGame.labelStyle2);
        powerup4.setFontScale(0.5f);
        powerup4.setColor( Color.CYAN );
        
        Label powerup5 = new Label("Powerup returns shot back to normal", BaseGame.labelStyle2);
        powerup5.setFontScale(0.5f);
        powerup5.setColor( Color.CYAN );
        
        
        //Label controls = new Label("Arrow Keys Move Turtle", BaseGame.labelStyle2);
       // instructions.setColor( Color.CYAN);
        
        //Label credits = new Label("Made by Angelo Morales", BaseGame.labelStyle2);
        //instructions.setColor( Color.CYAN );

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(core);
        uiTable.add(goal);
       // uiTable.row();
        uiTable.row();
        uiTable.add(tenMore);
        uiTable.add(powerup1);
        uiTable.row();
        uiTable.add(bomb);
        uiTable.add(powerup2).pad(10);
        uiTable.row();
        uiTable.add(piercing);
        uiTable.add(powerup3).pad(10);
        uiTable.row();
        uiTable.add(rapidFire);
        uiTable.add(powerup4).pad(10);
        uiTable.row();
        uiTable.add(normalShot);
        uiTable.add(powerup5).pad(10);
        uiTable.row();
        uiTable.add(heroShip);
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