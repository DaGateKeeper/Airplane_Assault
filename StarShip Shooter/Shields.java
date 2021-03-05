import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Shields extends BaseActor
{

    public Shields(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/shields.png") );
        
        setBoundaryPolygon(16);
    }

    public void act(float deltaTime)
    {
        super.act(deltaTime);
    }
}