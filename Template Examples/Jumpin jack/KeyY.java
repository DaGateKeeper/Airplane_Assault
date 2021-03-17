import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class KeyY extends BaseActor
{
    public KeyY(float x, float y, Stage stage)
    {
        super(x,y,stage);
        setSize(48,64);
        setAnimator( new Animator("assets/key/key-yellow.png") );
    }
}