import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Thruster extends BaseActor
{

    public Thruster(float x, float y, Stage stage)
    {
        super(x,y,stage);
        
        setAnimator( new Animator("assets/fire.png") );
        setSize(64, 36);
    }

}