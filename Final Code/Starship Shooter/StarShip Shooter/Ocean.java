import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Ocean extends BaseActor
{

    public Ocean(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator( "assets/water.jpg" ) );
        setSize(800,800);
        setPhysics( new Physics(0, 100, 0) );
        physics.setSpeed(100);
        physics.setMotionAngle(270);
    }

    public void act(float dt)
    {
        super.act(dt);
        
        // once ocean moves off-screen, move past other instance
        if ( !isOnScreen(800,800) )
            setY( getY() + 1600 );
    }
}
