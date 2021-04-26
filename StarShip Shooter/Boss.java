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

            if (shootTimer>0 && shootTimer<1.4){
                //laser shoots
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(-42,-125);
                eb2.moveBy(42,-125);
                eb.physics.setMotionAngle(270);
                eb2.physics.setMotionAngle(270);
            }
            if (shootTimer>1.5 && shootTimer<1.5){
                //outer 2 shoot at player
                Vector2 displacement = player.getPosition().sub( this.getPosition() );float angle = displacement.angle(); 
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(78,-115);
                eb2.moveBy(-79,-115);
                eb.setRotation(angle + 90);eb.physics.setMotionAngle(angle);
                eb2.setRotation(angle + 90);eb2.physics.setMotionAngle(angle);
            }
            if (shootTimer>1.8 && shootTimer<1.8){
                //innerouter 2 shoot straight
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(90,-97);
                eb2.moveBy(-93,-97);
                eb.physics.setMotionAngle(270);
                eb2.physics.setMotionAngle(270);
            }
            if (shootTimer>2 && shootTimer<2){
                //front first 2
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(8,-158);
                eb2.moveBy(-8,-158);
                eb.physics.setMotionAngle(270);
                eb2.physics.setMotionAngle(270);
            }
            if (shootTimer>2.4 && shootTimer<2.4){
                //front second 2 fire straight
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(16,-132);
                eb2.moveBy(-15,-132);
                eb.physics.setMotionAngle(270);
                eb2.physics.setMotionAngle(270);
            }
            if (shootTimer>2.7 && shootTimer<2.7){
                //front last 2 fire straight
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(21,-118);
                eb2.moveBy(78,-118);
                eb.physics.setMotionAngle(270);
                eb2.physics.setMotionAngle(270);
            }
            if (shootTimer>3 && shootTimer<3.1){
                //mids fire at once to player
                Vector2 displacement = player.getPosition().sub( this.getPosition() );float angle = displacement.angle(); 
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                EnemyBullet eb3 = new EnemyBullet(0,0, getStage());eb3.centerAt(this);
                EnemyBullet eb4 = new EnemyBullet(0,0, getStage());eb4.centerAt(this);
                EnemyBullet eb5 = new EnemyBullet(0,0, getStage());eb5.centerAt(this);
                EnemyBullet eb6 = new EnemyBullet(0,0, getStage());eb6.centerAt(this);
                EnemyBullet eb7 = new EnemyBullet(0,0, getStage());eb7.centerAt(this);
                EnemyBullet eb8 = new EnemyBullet(0,0, getStage());eb8.centerAt(this);
                eb.moveBy(29,-42);eb2.moveBy(11,-6);
                eb3.moveBy(46,-6);eb4.moveBy(29,41);
                eb5.moveBy(-32,-42);eb6.moveBy(-10,-6);
                eb7.moveBy(-46,-6);eb8.moveBy(-30,41);
                eb.setRotation(angle + 90);eb.physics.setMotionAngle(angle);
                eb2.setRotation(angle + 90);eb2.physics.setMotionAngle(angle);
                eb3.setRotation(angle + 90);eb3.physics.setMotionAngle(angle);
                eb4.setRotation(angle + 90);eb4.physics.setMotionAngle(angle);
                eb5.setRotation(angle + 90);eb5.physics.setMotionAngle(angle);
                eb6.setRotation(angle + 90);eb6.physics.setMotionAngle(angle);
                eb7.setRotation(angle + 90);eb7.physics.setMotionAngle(angle);
                eb8.setRotation(angle + 90);eb8.physics.setMotionAngle(angle);

            }
            if (shootTimer>4 && shootTimer<4.1){
                //mids fire again but straight
                                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                EnemyBullet eb3 = new EnemyBullet(0,0, getStage());eb3.centerAt(this);
                EnemyBullet eb4 = new EnemyBullet(0,0, getStage());eb4.centerAt(this);
                EnemyBullet eb5 = new EnemyBullet(0,0, getStage());eb5.centerAt(this);
                EnemyBullet eb6 = new EnemyBullet(0,0, getStage());eb6.centerAt(this);
                EnemyBullet eb7 = new EnemyBullet(0,0, getStage());eb7.centerAt(this);
                EnemyBullet eb8 = new EnemyBullet(0,0, getStage());eb8.centerAt(this);
                eb.moveBy(29,-42);eb2.moveBy(11,-6);
                eb3.moveBy(46,-6);eb4.moveBy(29,41);
                eb5.moveBy(-32,-42);eb6.moveBy(-10,-6);
                eb7.moveBy(-46,-6);eb8.moveBy(-30,41);
                eb.physics.setMotionAngle(270);
                eb2.physics.setMotionAngle(270);
                eb3.physics.setMotionAngle(270);
                eb4.physics.setMotionAngle(270);
                eb5.physics.setMotionAngle(270);
                eb6.physics.setMotionAngle(270);
                eb7.physics.setMotionAngle(270);
                eb8.physics.setMotionAngle(270);
                
            }if (shootTimer>4.5 && shootTimer<4.6){
                //outer 2 shoot straight
                
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(78,-115);
                eb2.moveBy(-79,-115);
                eb.physics.setMotionAngle(270);
                eb2.physics.setMotionAngle(270);
            }
            if (shootTimer>4.8 && shootTimer<4.8){
                //innerouter 2 shoot straight
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(90,-97);
                eb2.moveBy(-93,-97);
                eb.physics.setMotionAngle(270);
                eb2.physics.setMotionAngle(270);
            }
            if (shootTimer>5 && shootTimer<5.1){
                //front first 2 fire 
                Vector2 displacement = player.getPosition().sub( this.getPosition() );float angle = displacement.angle(); 
                 EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(8,-158);
                eb2.moveBy(-8,-158);
                eb.setRotation(angle + 90);eb.physics.setMotionAngle(angle);
                eb2.setRotation(angle + 90);eb2.physics.setMotionAngle(angle);
            }
            if (shootTimer>5.4 && shootTimer<5.5){
                //front second 2 fire 
                Vector2 displacement = player.getPosition().sub( this.getPosition() );float angle = displacement.angle(); 
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(16,-132);
                eb2.moveBy(-15,-132);
                 eb.setRotation(angle + 90);eb.physics.setMotionAngle(angle);
                eb2.setRotation(angle + 90);eb2.physics.setMotionAngle(angle);
            }
            if (shootTimer>5.7 && shootTimer<5.8){
                //front last 2 fire  
                Vector2 displacement = player.getPosition().sub( this.getPosition() );float angle = displacement.angle(); 
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                eb.moveBy(21,-118);
                eb2.moveBy(78,-118);
                eb.setRotation(angle + 90);eb.physics.setMotionAngle(angle);
                eb2.setRotation(angle + 90);eb2.physics.setMotionAngle(angle);
            }
            if (shootTimer>6 && shootTimer<6.1){
                //mids fire at once
                EnemyBullet eb = new EnemyBullet(0,0, getStage());eb.centerAt(this);
                EnemyBullet eb2 = new EnemyBullet(0,0, getStage());eb2.centerAt(this);
                EnemyBullet eb3 = new EnemyBullet(0,0, getStage());eb3.centerAt(this);
                EnemyBullet eb4 = new EnemyBullet(0,0, getStage());eb4.centerAt(this);
                EnemyBullet eb5 = new EnemyBullet(0,0, getStage());eb5.centerAt(this);
                EnemyBullet eb6 = new EnemyBullet(0,0, getStage());eb6.centerAt(this);
                EnemyBullet eb7 = new EnemyBullet(0,0, getStage());eb7.centerAt(this);
                EnemyBullet eb8 = new EnemyBullet(0,0, getStage());eb8.centerAt(this);
                eb.moveBy(29,-42);eb2.moveBy(11,-6);
                eb3.moveBy(46,-6);eb4.moveBy(29,41);
                eb5.moveBy(-32,-42);eb6.moveBy(-10,-6);
                eb7.moveBy(-46,-6);eb8.moveBy(-30,41);
                eb.physics.setMotionAngle(270);
                eb2.physics.setMotionAngle(270);
                eb3.physics.setMotionAngle(270);
                eb4.physics.setMotionAngle(270);
                eb5.physics.setMotionAngle(270);
                eb6.physics.setMotionAngle(270);
                eb7.physics.setMotionAngle(270);
                eb8.physics.setMotionAngle(270);
            }
            if ( shootTimer > 8){
                shootTimer=0;

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

