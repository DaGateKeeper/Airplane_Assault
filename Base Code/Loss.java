import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import com.badlogic.gdx.audio.Sound;

public class Loss extends BaseScreen
{   
    public Sound Dead;
            public Sound startup;
    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/water.jpg") );
        background.setSize(800,800);
        startup = Gdx.audio.newSound(Gdx.files.internal("assets/startupEagle.wav"));
        // BaseActor title = new BaseActor(150, 300, mainStage);
        // title.setAnimator( new Animator("assets/starfish-collector.png") );

        // BaseActor instructions = new BaseActor(150, 100, mainStage);
        // instructions.setAnimator( new Animator("assets/message-start.png") );

        Label title = new Label("Dead", BaseGame.labelStyle);
        title.setFontScale(2);
        title.setColor( Color.WHITE );

        Label instructions = new Label("Press 'S' to Play Again", BaseGame.labelStyle);
        instructions.setColor( Color.WHITE );

        Label Exit = new Label("Press 'X' to Exit", BaseGame.labelStyle);
        Exit.setColor( Color.RED );

        uiTable.add(title);
        uiTable.row();
        uiTable.add().pad(32);
        uiTable.row();
        uiTable.add(instructions);
        uiTable.row();
        uiTable.add(Exit);
    } 
    
    public void update(float deltaTime)
    {
        
                if (Gdx.input.isKeyPressed(Keys.S)){
            BaseGame.setActiveScreen( new LevelScreen() );
                    startup.play();        
        }
            
        if (Gdx.input.isKeyPressed(Keys.X))
            Gdx.app.exit();
    }
}