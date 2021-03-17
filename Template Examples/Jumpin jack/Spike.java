import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Spike extends Solid
{
    public Spike(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/spikesNew.png") );
        setSize(32, 35);
    }
}
