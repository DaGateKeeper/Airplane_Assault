import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Coin extends BaseActor
{

    public Coin(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/coin.png", 1,6, 0.1f, true) );
        setSize(32,32);
        setBoundaryPolygon(8);
        
    }

}