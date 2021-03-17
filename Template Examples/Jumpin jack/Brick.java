import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
 
public class Brick extends Solid
{

    public Brick(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator( "assets/brick.png" ) );
    }

}