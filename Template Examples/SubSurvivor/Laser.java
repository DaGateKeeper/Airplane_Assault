import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Laser extends BaseActor
{

    public Laser(float x, float y, Stage stage)
    {
        super(x,y,stage);
        
        setAnimator( new Animator("assets/images/bullet.png") );
        
        setPhysics( new Physics( 0, 500, 0 ) );
        physics.setSpeed(physics.maximumSpeed);
    }

    public void act(float deltaTime)
    {
        super.act(deltaTime);
        if (!isOnStage())
            remove();
    }
}