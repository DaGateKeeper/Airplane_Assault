
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Locks extends BaseActor
{
    public int LockValue;
    
    public Locks(float x, float y, int value, Stage stage)
    {
        super(x,y,stage);
        LockValue=value;
        setSize(48,64);
        
        if(LockValue==1){
            setAnimator( new Animator("assets/key/lock-yellow.png") );
        }else if(LockValue==2){
            setAnimator( new Animator("assets/key/lock-red.png") );
        }else if(LockValue==3){
            setAnimator( new Animator("assets/key/lock-green.png") );
        }else if(LockValue==4){
            setAnimator( new Animator("assets/key/lock-blue.png") );
        }
    }

}