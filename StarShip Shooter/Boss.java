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
            function = new BossPath();//back and forth
            setAnimator( new Animator("assets/ships/Boss.png") );
            Health=Databases.getBossStats(preset).getHealth();
        }else
        if (preset == 1)
        {
            time = -2;
            function = new BossPath2();//back and forth but better
            setAnimator( new Animator("assets/ships/BossLasers.png") );
            Health=Databases.getBossStats(preset).getHealth();

        }else
        if (preset == 2)
        {
            time = -2;
            function = new BossPath3();//circle?
            setAnimator( new Animator("assets/ships/Boss2.png") );
            Health=Databases.getBossStats(preset).getHealth();

        }else
        if (preset == 3)
        {
            time = -2;
            function = new BossPath2();//was 4 but this is too big plus i have a better ideafor this one.
            setAnimator( new Animator("assets/ships/Boss3.png") );
            Health=Databases.getBossStats(preset).getHealth();

        }else
        if (preset == 4)
        {
            time = -2;
            function = new BossPath();
            setAnimator( new Animator("assets/ships/Boss2.png") );
            Health=Databases.getBossStats(preset).getHealth();

        }
        else
        if (preset == 5)
        {
            time = -2;
            function = new BossPath();
            setAnimator( new Animator("assets/ships/Boss3.png") );
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

        if ( preset==1)//laser
        {
            if(shootTimer>5){
                EnemyBullet eb = new EnemyBullet(0,0, getStage());
                eb.centerAt(this);
                eb.moveBy(50,0);
                eb.physics.setMotionAngle(270);

                if (shootTimer >=7)
                    shootTimer = 0;
            }
        } 
        else if (preset==2 || preset==4)//dual triple
        {
            if ( shootTimer > 7){
                 EnemyBullet eb = new EnemyBullet(0,0, getStage());EnemyBullet eb4 = new EnemyBullet(0,0, getStage());
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());EnemyBullet eb5 = new EnemyBullet(0,0, getStage());
                EnemyBullet eb3 = new EnemyBullet(0,0, getStage());EnemyBullet eb6 = new EnemyBullet(0,0, getStage());
                eb.centerAt(this);eb2.centerAt(this);eb3.centerAt(this);eb4.centerAt(this);eb5.centerAt(this);eb6.centerAt(this);
                eb.moveBy(24,0);eb4.moveBy(-24,0);
                eb2.moveBy(18,0);eb5.moveBy(-18,0);
                eb3.moveBy(12,0);eb6.moveBy(-12,0);
                Vector2 displacement = player.getPosition().sub( this.getPosition() );float angle = displacement.angle(); 
                eb.physics.setMotionAngle(270);
                eb.setRotation(angle + 90);eb.physics.setMotionAngle(angle);            
                eb2.setRotation(angle + 90);eb2.physics.setMotionAngle(angle);  
                eb3.setRotation(angle + 90);eb3.physics.setMotionAngle(angle);
                eb4.setRotation(angle + 90);eb4.physics.setMotionAngle(angle);            
                eb5.setRotation(angle + 90);eb5.physics.setMotionAngle(angle);  
                eb6.setRotation(angle + 90);eb6.physics.setMotionAngle(angle);
                shootTimer=0;
            }
        } else if (preset==3 || preset==5)//mayhem
        {//first sketch then execute itJOHN DONT JUMP THIS ONE!!!!!!!!!!!!! - past and awake John. SleepyJohn Dont touch this. 
            //Please. Just Draw It. Or play Halo
            if ( shootTimer > 7){
               
            }
        }else if( shootTimer > 5 ){//basic long boi
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

