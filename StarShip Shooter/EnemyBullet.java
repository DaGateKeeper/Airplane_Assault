import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class EnemyBullet extends BaseActor
{
    public EnemyBullet(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/laser-red.png") );
        
        setPhysics( new Physics(0,450,0) );
        
        physics.setSpeed(400);
        
        setSize(15,30);
        setBoundaryRectangle();
    }

    public void act(float dt)
    {
        super.act(dt); 
        
        if ( !isOnScreen(800,800) )
            remove(); 
    }
}