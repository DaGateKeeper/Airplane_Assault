import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Key extends BaseActor
{
    public int KeyValue;
    
    public Key(float x, float y, int value, Stage stage)
    {
        super(x,y,stage);
        KeyValue=value;
        setSize(48,64);
        
        
            
        if(KeyValue==2){
            setAnimator( new Animator("assets/key/key-red.png") );
        }else if(KeyValue==4){
            setAnimator( new Animator("assets/key/key-blue.png") );
        }
    }

}