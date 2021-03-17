
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Flag extends BaseActor
{

    public Flag(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/flag.png",1,2,.2f,true) );
        setSize(32,32);
    }

}