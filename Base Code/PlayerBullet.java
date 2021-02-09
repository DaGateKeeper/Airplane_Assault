import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class PlayerBullet extends BaseActor
{
    public PlayerBullet(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/bullet-gold.png") );
        
        setPhysics( new Physics(0,450,0) );
        
        physics.setSpeed(450);
        setRotation(90);
        physics.setMotionAngle(90);
        setSize(30,15);
        setBoundaryRectangle();
    }

    public void act(float dt)
    {
        super.act(dt); 
        
        if ( !isOnScreen(800,800) )
            remove(); 
    }
}