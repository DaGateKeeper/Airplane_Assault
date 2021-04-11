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
    static double Health;// a variable to track the health
    public Boss(int p, Stage stage)
    {
        super(1000,1000, stage);

        preset = p;

        setAnimator( new Animator(Databases.getBossStats(preset).getfileName()) );
        setSize(80,80);
        time = 0;
        //shootTimer = 0;
        if (preset == 0)
        {
            time = -2;
            function = new BossPath();
            setAnimator( new Animator("assets/ships/Boss.png") );
            Health=Databases.getBossStats(preset).getHealth();
        }else
        if (preset == 1)
        {
            time = -2;
            function = new BossPath2();
            setAnimator( new Animator("assets/ships/BossLasers.png") );
            Health=Databases.getBossStats(preset).getHealth();

        }
    }

    public void takeDamage()
    {
        Health-= LevelScreen.DamPlay;
    }

    public void act(float dt)
    {
        super.act(dt);

        time += dt;

        Vector2 position = function.evaluate(time);
        setPosition(position.x, position.y);
        
        BaseActor player = BaseActor.getList(getStage(), "Player").get(0); 

        shootTimer += dt;

        if ( shootTimer > 5 && preset==1)
        {

            EnemyBullet eb = new EnemyBullet(0,0, getStage());
            eb.centerAt(this);
            eb.moveBy(50,0);
            eb.physics.setMotionAngle(270);

            if (shootTimer >=7)
                shootTimer = 0;
        } 
        else if( shootTimer > 5){
            EnemyBullet eb = new EnemyBullet(0,0, getStage());
            eb.centerAt(this);

            Vector2 displacement = player.getPosition().sub( this.getPosition() );
            float angle = displacement.angle(); 
   
            eb.setRotation(angle + 90);
            eb.physics.setMotionAngle(angle);

            shootTimer = 0;   
        }

        for (BaseActor pb : BaseActor.getList(getStage(), "PlayerBullet"))
        {// checks for collisions and if ih detects it executes taking damage
            if (overlaps(pb)){
                takeDamage();//takes damage and then removes bullet. 
                pb.remove();//this is important becasue 
                //then when you hit the hull it does no damage
            }

        }
    }
}

