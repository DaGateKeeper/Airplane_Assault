import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Solid extends BaseActor
{
    public boolean enabled;
    
    public Solid(float x, float y, Stage stage)
    {
        super(x,y,stage);

        enabled = true;
    }

    public void setEnabled(boolean b)
    {
        enabled = b;
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
    
}