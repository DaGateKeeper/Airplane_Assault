import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Sparkle extends BaseActor
{

    public Sparkle(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator( "assets/sparkle-blue.png", 8, 4, 0.05f, false ) );
        
        setSize(100,100);
    }

    public void act(float deltaTime)
    {
        // NEVER FORGET THIS!!!!
        super.act(deltaTime);
        
        if ( animator.isAnimationFinished() )
            remove();
    }
}