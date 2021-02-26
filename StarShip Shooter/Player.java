import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Player extends BaseActor
{
    private int index2_;
    public Player(float x, float y, Stage stage,int INDEXed)
    {
        super(x,y,stage);

        setAnimator( new Animator(Databases.getPlayerCopy(INDEXed).getfileName()) );
        setRotation( 90 );
        setBoundaryPolygon(8);
        
        setPhysics( new Physics(3000, Databases.getPlayerCopy(INDEXed).getSpeed(), 3000) );
    }
    
    public void act(float dt)
    {
        super.act(dt);
        
        boundToWorld(800,800);   
    }

    
}