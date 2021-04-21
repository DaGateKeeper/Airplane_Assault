import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Planet extends BaseActor
{

    public Planet(Stage stage)
    {
        super((float)(600 * Math.random() + 100), 900, stage);
        
        String[] fileNames = {"planet1", "planet2", "planet3"};
        int randomIndex = (int)( fileNames.length * Math.random() );
        String fileName = fileNames[randomIndex];
        
        setAnimator( new Animator( "assets/" + fileName + ".png" ) );

        float scale = (float)( 0.50 * Math.random() + 0.50 );
        setScale(scale);
        
        float angle = (float)( -20 + 40 * Math.random() );
        setRotation(angle);
        
        setPhysics( new Physics(0, 600, 0) );
        physics.setSpeed(100);
        physics.setMotionAngle(270);
    }

    public void act(float dt)
    {
        super.act(dt);
        
        // once it moves off-screen, past the bottom edge, remove
        if ( !isOnScreen(800,800) && getY() < 0 )
            remove();
    }
}
