import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.math.Vector2;

public class Boss extends BaseActor
{
    public int preset;
    public float time;

    // function to describe enemy location at any time
    public Function function;

    public float shootTimer;

    public Boss(int p, Stage stage)
    {
        super(1000,1000, stage);

        preset = p;

        setAnimator( new Animator("assets/ships/Boss.png") );
        setSize(80,80);
        time = 0;
        shootTimer = 0;

        
        if (preset == 1)
        {
            time = -2;
            function = new BossPath();
            setAnimator( new Animator("assets/ships/Boss.png") );
        }
    }

    public void act(float dt)
    {
        super.act(dt);
        
        time += dt;

        Vector2 position = function.evaluate(time);
        setPosition(position.x, position.y);
        if (preset!=1){
            // make enemy image face angle in direction of movement
            float movementAngle = function.getDirectionAngle(time);
            setRotation( movementAngle );
        }
        BaseActor player = BaseActor.getList(getStage(), "Player").get(0); 

        shootTimer += dt;

        // other possible conditions:

        // enemy waits for first shot:
        //  && time > 3

        // enemy must be above player:
        //  && this.getY() > player.getY()

        if ( shootTimer > 1 )
        {
            EnemyBullet eb = new EnemyBullet(0,0, getStage());
            eb.centerAt(this);

            Vector2 displacement = player.getPosition().sub( this.getPosition() );
            float angle = displacement.angle(); 

            eb.setRotation(angle + 90);
            eb.physics.setMotionAngle(angle);

            shootTimer = 0;
        }
    }
}

