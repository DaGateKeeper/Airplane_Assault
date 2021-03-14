import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class GameOver extends BaseActor
{

    public GameOver(float x, float y, Stage stage)
    {
        super(x,y,stage);
        setAnimator( new Animator("assets/images/gameover.png") );
    }

}