import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class KeyB extends BaseActor
{
    public KeyB(float x, float y, Stage stage)
    {
        super(x,y,stage);
        setSize(48,64);
        setAnimator( new Animator("assets/key/key-blue.png") );
    }
}