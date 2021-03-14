import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
 
public class Core extends BaseActor
{
    public int health;
    
    public Core(float x, float y, Stage stage)
    {
        super(x,y,stage);
        setAnimator( new Animator("assets/images/the-core.png") );
        health = 10;
    }

    public Core(float x, float y, int health, Stage stage)
    {
        this(x, y, stage);
        this.health = health;
    }
}