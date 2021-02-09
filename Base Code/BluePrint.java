import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
//taken from BRICK BREAK
public class BluePrint extends BaseActor
{
    public String BluePrintName;
    
    public BluePrint(float x, float y, Stage stage)
    {
        super(x,y,stage);
        //this is where we will put the item names for easy replacement
        String[] BluePrintNames = {  "BP-item" };
        
        int randomIndex = (int)(BluePrintNames.length * Math.random());
        BluePrintName = BluePrintNames[randomIndex];
        
        String fileName = "assets/" + BluePrintName + ".png";
        
        setAnimator( new Animator(fileName) );
        
        setSize(64,64);
        setBoundaryRectangle();
        physics = new Physics(0, 100, 0);
        physics.setSpeed( 100 );
        physics.setMotionAngle( -90 );
    }

    public void act(float dt)
    {
        super.act(dt);
        if (!isOnStage())
            remove();
    }
}