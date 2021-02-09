import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

public class Explosion extends BaseActor
{
    public static Sound eSFX = Gdx.audio.newSound(Gdx.files.internal("assets/Explode.wav"));
    public static Sound hSFX = Gdx.audio.newSound(Gdx.files.internal("assets/HITMARKER.wav"));
    public Explosion(float x, float y, Stage stage, int PLAY)
    {
        super(x,y,stage);
        setAnimator( new Animator("assets/explosion.png", 6, 6, 0.02f, false) );
        if (PLAY==1)
        eSFX.play();
        else if(PLAY==0)
        hSFX.play();
    }
    
    public void act(float deltaTime)
    {
        super.act(deltaTime);
        
        if ( this.animator.isAnimationFinished() )
            remove();
    }

}
