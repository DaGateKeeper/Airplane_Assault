import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Player extends BaseActor
{

    public Player(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/ships/player1.png") );
        setRotation( 90 );
        setBoundaryPolygon(8);
        
        setPhysics( new Physics(3000, 300, 3000) );
        
    }
    
    public void act(float dt)
    {
        super.act(dt);
        
        boundToWorld(800,800);   
    }

}