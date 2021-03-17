import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class LockG extends Solid
{
    public LockG(float x, float y,Stage stage)
    {
        super(x,y,stage);
        
        setSize(48,64);
        setAnimator( new Animator("assets/key/lock-green.png") );
    }
}