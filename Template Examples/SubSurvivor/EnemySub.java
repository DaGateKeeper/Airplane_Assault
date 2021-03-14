import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

///////////////////thing
public class EnemySub extends BaseActor 
{
//mainly taken from the alien fromSPACE ROCKS
    public EnemySub(float x, float y, Stage stage)
    {
       super(x,y,stage);

       setAnimator( new Animator("assets/images/subenemy.png") );
        
       setPhysics( new Physics(0, 100, 0) );
       physics.setSpeed(physics.maximumSpeed);
        
       setRotation(0);
       physics.setMotionAngle(180);
       
       this.setBoundaryPolygon(8);
    }
    
    @Override
    public void act(float dt)
    {
        super.act(dt);
    }
}