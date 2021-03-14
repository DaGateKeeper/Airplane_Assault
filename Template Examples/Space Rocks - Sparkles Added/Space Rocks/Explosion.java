import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Explosion extends BaseActor
{

    public Explosion(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/explosion.png", 6,6, 0.02f, false) );
    }
    
    public void act(float deltaTime)
    {
        super.act(deltaTime);
        
        if ( this.animator.isAnimationFinished() )
            remove();
    }

}