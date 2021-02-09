import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Player extends BaseActor
{
    public float shotTimer;
    public int normalAmmo;
    public int specialAmmo;
    public int weapon;
    public int health;
    public Player(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/player-gold.png") );

        setRotation( 90 );

        setBoundaryPolygon(8);

        setPhysics( new Physics(3000, 300, 3000) );
        shotTimer = 0;
        normalAmmo = 15;
        specialAmmo = 0;
        weapon = 0;
        health = 200;
    }

    public Player(float x, float y, int health, Stage stage)
    {
        this(x, y, stage);
        this.health = health;
    }

    public void act(float dt)
    {
        super.act(dt);

        boundToWorld(800,800);   
    }

    public void fire(LevelScreen s)
    {
        switch(weapon)
        {
            // Piercing shot.
            case 1:
            if (shotTimer >= 0 && specialAmmo > 0)
            {
                Piercing pierce = new Piercing(0, 0, s.mainStage);
                pierce.centerAt(this);
                s.pierceSFX.play();
                specialAmmo--;

                shotTimer = 0;
            }
            break;
            // Bomb shot.
            case 2:
            if (shotTimer >= 0 && specialAmmo > 0)
            {
                for(BaseActor e : getList(s.mainStage, "Enemy"))
                {
                    Explosion exp = new Explosion(0, 0, s.mainStage,1);
                    exp.centerAt(e);
                    e.remove();
                    s.score += 10;
                }
                specialAmmo--;

                shotTimer = 0;
            }
            break;

            // Normal shot.
            default:
            if (shotTimer >= 0 && normalAmmo > 0)
            {
                PlayerBullet pb = new PlayerBullet(0,0, s.mainStage);
                pb.centerAt(this);
                //shotSFX.play();

                shotTimer = 0;
            }
            break;
        }

        // Resets weapon if out of ammo.
        if (specialAmmo <= 0)
        {
            weapon = 0;

        }
    }
}