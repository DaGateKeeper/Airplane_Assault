import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Clock extends BaseActor
{

    public Clock(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/item-clock.png"));
        setSize(32,32);
        setBoundaryPolygon(8);
        
    }

}