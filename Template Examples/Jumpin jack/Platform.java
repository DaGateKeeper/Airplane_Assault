import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Platform extends Solid
{

    public Platform(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/log-bridge.png") );
    }

}