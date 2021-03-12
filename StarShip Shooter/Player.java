import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Player extends BaseActor
{
    private int index2_;
    // Tracks how long the Koala has been invincible for after taking damage.
    public float invTimer;

    // How long the Koala can stay invincible.
    public float maxInvTime;
    public Player(float x, float y, Stage stage,int INDEXed)
    {
        super(x,y,stage);

        setAnimator( new Animator(Databases.getPlayerCopy(INDEXed).getfileName()) );
        setRotation( 90 );
        setBoundaryPolygon(8);

        setPhysics( new Physics(3000, Databases.getPlayerCopy(INDEXed).getSpeed(), 3000) );
        maxInvTime = 2;
        invTimer = maxInvTime;
    }

    public void takeDamage()
    {
        if (invTimer >= maxInvTime)
        {
            invTimer = 0;
            LevelScreen.PlayerHealth-=10;
            LevelScreen.playerLabel.setText("Health:"+LevelScreen.PlayerHealth);
        }
    }

    public void act(float dt)
    {
        // update animation and physics
        super.act(dt);

        // Causes the Koala to flash every 2 seconds while invincible.
        invTimer += dt;
        boolean blinkBool = false;
        if (invTimer < maxInvTime)
        {
            float blinkTimer = invTimer;
            blinkBool = true;
            while (blinkTimer >= 0.075f)
            {
                blinkTimer -= 0.075f;
                blinkBool = !blinkBool;
            }
        }
        if (blinkBool)
            getColor().a = 0.25f;
        else
            getColor().a = 1;

        for (BaseActor enemy : BaseActor.getList(getStage(), "Enemy"))
        {
            if (overlaps(enemy)){
                takeDamage();

                //Explosion exp = new Explosion(0, 0, mainStage);
                //exp.centerAt(enemy);

            }
        }

            
        boundToWorld(800,800);   
    }

}