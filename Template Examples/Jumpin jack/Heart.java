import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Heart extends BaseActor
{

    public Heart(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/heart.png") );
        setSize(32,32);
    }

}