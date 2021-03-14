import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Powerup extends BaseActor
{

    public Powerup(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator( "assets/powerup.png" ) );
        setBoundaryPolygon(8);
    }

}