import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Write a description of class HealSub here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HealSub extends BaseActor
{
    public HealSub(float x, float y, Stage stage)
    {
        super(x, y, stage);
        setAnimator(new Animator("assets/images/subheal.png"));
        setPhysics(new Physics(0, 100, 0));
        physics.setSpeed(physics.maximumSpeed);
        setRotation(0);
        physics.setMotionAngle(180);
        this.setBoundaryPolygon(8);
    }
}
