import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Door extends BaseActor
{
    public int targetRoom;
    
    public Door(float x, float y, int target, Stage stage)
    {
        super(x,y,stage);
        targetRoom = target;
        setAnimator( new Animator("assets/door.png") );
        setSize(48,64);
    }

}