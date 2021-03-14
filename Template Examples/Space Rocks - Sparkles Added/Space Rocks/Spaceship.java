import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Spaceship extends BaseActor
{

    public Spaceship(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/spaceship-violet.png"));
        
        // acceleration, max speed, deceleration
        setPhysics( new Physics(100, 200, 0) );
        
        setScale(0.5f);
        setBoundaryPolygon(8);
        
    }
    
    public void act(float deltaTime)
    {
        super.act(deltaTime);
        
        wrap(800,600);
    }

}