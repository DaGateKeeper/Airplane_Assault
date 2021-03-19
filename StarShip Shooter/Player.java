import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Player extends BaseActor
{
    private int index2_;
    // Tracks how long the ship has been invincible for after taking damage. Code was scrapped from an old game. possibly some inconsistancies
    public float invTimer;

    // How long the ship can stay invincible.might be able to be reworked as a sheild recharger? I cant say. 
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

    public void takeDamageP()
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
                takeDamageP();

                //Explosion exp = new Explosion(0, 0, mainStage);
                //exp.centerAt(enemy);

            }
        }for (BaseActor enemyb : BaseActor.getList(getStage(), "EnemyBullet"))
        {
            if (overlaps(enemyb)){
                takeDamageP();

                //Explosion exp = new Explosion(0, 0, mainStage);
                //exp.centerAt(enemy);

            }
        }
        for (BaseActor boss : BaseActor.getList(getStage(), "Boss"))
        {
            if (overlaps(boss)){
                takeDamageP();
            }
        }

        boundToWorld(800,800);   
    }

}