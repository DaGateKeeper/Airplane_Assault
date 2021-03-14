import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MenuScreen extends BaseScreen
{
    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/images/water.jpg") );
        background.setSize(800,600);

        

        Label title = new Label("    Sub\nSurvival", BaseGame.labelStyle);
        title.setFontScale(2);
        title.setColor( Color.BLUE );
        
       
        

        Label instructions = new Label("Press 'S' to Start", BaseGame.labelStyle);
        instructions.setColor( Color.CYAN );
        
        Label goal = new Label("Press 'D' for Powerups", BaseGame.labelStyle);
        goal.setColor( Color.CYAN );
        
        Label goal2 = new Label("Press 'A' for Instructions", BaseGame.labelStyle);
        goal2.setColor( Color.CYAN );
        
        Label credits = new Label("Press 'W' for Credits", BaseGame.labelStyle);
        credits.setColor( Color.CYAN );
        
        // Label credits = new Label("      By\n", BaseGame.labelStyle);
        //instructions.setColor( Color.CYAN );
        
        
        
        //Label controls = new Label("Arrow Keys Move Turtle", BaseGame.labelStyle2);
       // instructions.setColor( Color.CYAN);
        
        //Label credits = new Label("Made by Angelo Morales", BaseGame.labelStyle2);
        //instructions.setColor( Color.CYAN );

        uiTable.add(title);
        uiTable.row();
        uiTable.add().pad(20);
        uiTable.row();
        uiTable.add(instructions);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(goal);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(goal2);
        uiTable.row();
        uiTable.add().pad(10);
        uiTable.row();
        uiTable.add(credits);
        uiTable.row();
        //uiTable.add(credits);
        
    }

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S))
            BaseGame.setActiveScreen( new LevelScreen() );    
        if (Gdx.input.isKeyJustPressed(Keys.D))
            BaseGame.setActiveScreen( new InstructionScreen() );    
        if (Gdx.input.isKeyJustPressed(Keys.W))
            BaseGame.setActiveScreen( new CreditsScreen() );    
        if (Gdx.input.isKeyJustPressed(Keys.A))
            BaseGame.setActiveScreen( new ControlScreen() ); 
    } 
}