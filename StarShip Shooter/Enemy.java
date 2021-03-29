import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends BaseActor
{
    public int preset;
    public float time;

    // function to describe enemy location at any time
    public Function function;

    public float shootTimer;

    public Enemy(int p, Stage stage)
    {
        super(1000,1000, stage);

        preset = p;

        setAnimator( new Animator("assets/ships/enemy1.png") );
        setSize(80,80);
        time = 0;
        shootTimer = 0;

        if (preset == 1)
        {
            function = new LinearPath();
            setAnimator( new Animator("assets/ships/Enemy1.png") );
        }
        else if (preset == 2)
        {
            function = new CircularPath();
            setAnimator( new Animator("assets/ships/Enemy2.png") );

        }
        else if (preset == 3)
        {
            function = new SinePath();
            setAnimator( new Animator("assets/ships/Enemy3.png") );
        }
        else if (preset == 4)
        {
            time = -2;
            function = new HourglassPath();
            setAnimator( new Animator("assets/ships/Enemy4.png") );
        }
    }

    public void act(float dt)
    {
        super.act(dt);

        time += dt;

        Vector2 position = function.evaluate(time);
        setPosition(position.x, position.y);

        // make enemy image face angle in direction of movement
        float movementAngle = function.getDirectionAngle(time);
        setRotation( movementAngle );

        BaseActor player = BaseActor.getList(getStage(), "Player").get(0); 

        shootTimer += dt;

        // other possible conditions:

        // enemy waits for first shot:
        //  && time > 3

        // enemy must be above player:
        //  && this.getY() > player.getY()

        if ( shootTimer > 1)
        {
            //EnemyBullet eb = new EnemyBullet(0,0, getStage());
           // eb.centerAt(this);

            Vector2 displacement = player.getPosition().sub( this.getPosition() );
            float angle = displacement.angle(); 

            //eb.setRotation(angle + 90);
            //eb.physics.setMotionAngle(angle);

            shootTimer = 0;
        }
    }
}

