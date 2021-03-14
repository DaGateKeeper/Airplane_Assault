
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Screen;

/**
 * Write a description of class Submarine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Submarine extends BaseActor
{
    public float shotTimer;
    public int normalAmmo;
    public int specialAmmo;
    public int weapon;

    public static Sound shotSFX = Gdx.audio.newSound(Gdx.files.internal("assets/audio/sfx/Missile-Launch.wav"));

    public Submarine(float x, float y, Stage stage)
    {
        super(x,y,stage);

        setAnimator( new Animator("assets/images/sub.png") );
        setBoundaryPolygon(10);

        physics = new Physics(2000, 600, 8000);

        shotTimer = 0;
        normalAmmo = 15;
        specialAmmo = 0;
        weapon = 0;
    }

    @Override
    public void act(float dt)
    {
        super.act(dt);
        shotTimer += dt;
        boundToWorld(800, 600);
    }

    public void fire(LevelScreen s)
    {
        switch(weapon)
        {
            // Piercing shot.
            case 1:
                if (shotTimer >= 1 && specialAmmo > 0)
                {
                    Piercing pierce = new Piercing(0, 0, s.mainStage);
                    pierce.centerAt(this);
                    s.pierceSFX.play();
                    specialAmmo--;
                    s.ammoLabel.setText("Piercing Ammo: " + specialAmmo);
                    shotTimer = 0;
                }
                break;
            // Bomb shot.
            case 2:
                if (shotTimer >= 1 && specialAmmo > 0)
                {
                    for(BaseActor e : getList(s.mainStage, "EnemySub"))
                    {
                        Explosion exp = new Explosion(0, 0, s.mainStage);
                        exp.centerAt(e);
                        e.remove();
                        s.score += 10;
                    }
                    specialAmmo--;
                    s.ammoLabel.setText("Bombs: " + specialAmmo);
                    shotTimer = 0;
                }
                break;
            // Rapid fire shots.
            case 3:
                if (shotTimer >= 0.2f && specialAmmo > 0)
                {
                    Laser laser = new Laser(0, 0, s.mainStage);
                    laser.centerAt(this);
                    shotSFX.play();
                    specialAmmo--;
                    s.ammoLabel.setText("Rapid Fire Ammo: " + specialAmmo);
                    shotTimer = 0;
                }
                break;
            // Normal shot.
            default:
                if (shotTimer >= 1 && normalAmmo > 0)
                {
                    Laser laser = new Laser(0, 0, s.mainStage);
                    laser.centerAt(this);
                    shotSFX.play();
                    normalAmmo--;
                    //s.ammoLabel.setText("Ammo: " + normalAmmo);
                    shotTimer = 0;
                }
                break;
        }

        // Resets weapon if out of ammo.
        if (specialAmmo <= 0)
        {
            weapon = 0;
            s.ammoLabel.setText("Ammo: " + normalAmmo);
        }
    }
}

