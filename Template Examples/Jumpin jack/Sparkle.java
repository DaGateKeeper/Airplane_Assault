import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Sparkle extends BaseActor
{
    public static enum SparkleColor
    {
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        VIOLET
    };
    
    public Sparkle(float x, float y, Stage stage, SparkleColor c)
    {
        super(x, y, stage);
        setAnimator(new Animator("assets/effects/sparkle-" + c.name().toLowerCase() + ".png", 8, 4, 0.015f, false));
        getColor().a = 0.8f;
        setSize(48, 48);
    }
    
    @Override
    public void act(float dt)
    {
        super.act(dt);
        if (animator.isAnimationFinished())
            remove();
    }
}