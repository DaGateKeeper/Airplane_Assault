import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.math.MathUtils;

//class that extends the infiniteScroller which spawns the explosions

public class Explosion extends BaseActor
{
    public Explosion(float x, float y, Stage s)
    {

        super(x, y, s);
        this.setAnimator( new Animator("assets/explosion.png", 6,6, 0.05f, false) );
        
        this.setPhysics( new Physics(0,1000,0) );
        this.physics.setSpeed(75);
        this.physics.setMotionAngle(270);
    }

    public void act(float deltaTime)
    {
        super.act(deltaTime);
        // remove effect from screen when done.
        if ( this.animator.isAnimationFinished() )
            this.remove();
    }
}