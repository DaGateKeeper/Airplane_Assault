import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

public class Explosion extends BaseActor
{
    public static Sound eSFX = Gdx.audio.newSound(Gdx.files.internal("assets/audio/sfx/Explode.wav"));

    public Explosion(float x, float y, Stage stage)
    {
        super(x,y,stage);
        setAnimator( new Animator("assets/images/explosion.png", 5, 8, 0.02f, false) );
        eSFX.play();
    }
    
    public void act(float deltaTime)
    {
        super.act(deltaTime);
        
        if ( this.animator.isAnimationFinished() )
            remove();
    }

}
