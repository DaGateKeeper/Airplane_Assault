import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.math.MathUtils;
/**
 * Powerups class which helps to create all of our powerups in the game
 * for the score 
 *
 * James Meurer
 * @version (a version number or a date)
 */
public class PowerUps extends BaseActor
{
    public String imageName;
    public PowerUps(float x ,float y, Stage s)
    {
        super(x, y ,s);
        String[] imageNames = { "x2" ,"x4","x6","x8","x10", "health"};
        int randomIndex = MathUtils.random(0, imageNames.length -1);
        this.imageName = imageNames [ randomIndex ];
        String fileName = "assets/powerups/" + imageName + ".png";
        this.setAnimator(new Animator(fileName));

        this.setSize(50,50);
        this.setPhysics( new Physics(0,100,0));
        this.physics.setSpeed(100);
        this.physics.setMotionAngle(270);

        this.setScale(0.01f);
        this.addAction(
            Actions.scaleTo(1,1,0.5f)
        );

    }

    public void act(float deltaTime)
    {
        super.act(deltaTime);

        // destroy powerup if passes below bottom edge of screen
        if ( this.getY() + this.getHeight() < 0 )
            this.remove();
    }
}
