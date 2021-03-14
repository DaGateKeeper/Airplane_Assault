import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Rock extends BaseActor
{

    public Rock(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/rock.png") );
        
        setPhysics( new Physics(0, 50, 0) );
        
        physics.setSpeed(50);
        
        float angle = 360 * (float)Math.random();
        setRotation(angle);
        physics.setMotionAngle(angle);
        
        float size = (float)( 50 + 50 * Math.random() );
        
        setSize( size, size );
        
        this.setBoundaryPolygon(8);
    }
    
    public void act(float deltaTime)
    {
        super.act(deltaTime);
        
        wrap(800, 600);
        
    }

}