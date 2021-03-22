import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
//really it is simple all we need to do is make a second bullet center it at the player 
//and then change the angle of motion to get it to go in that direction
public class PlayerBullet extends BaseActor
{
    public PlayerBullet(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/laser-gold.png") );
        
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
