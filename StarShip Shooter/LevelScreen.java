import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

// for storing and retrieving high scores in a text file
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LevelScreen extends BaseScreen
{
    public float shootTimer;
    public Player player;
    public float enemyTimer, BossT;
    public int  enemyDestroyed;
    int SELECTED, Spawnrate;
    int highScore;
    int maxScore=999999;
    int maxHealth=500;
    int maxShield=500;
    int score, upgradeNum;
    int bomb = 0;
    int enemyMultiplyer = 1;
    int enemyMultiplyer2= 1;
    int enemyMultiplyer3= 1;
    int enemyMultiplyer4= 1;
    int enemyMultiplyerDestroyed=0;
    int enemyMultiplyerDestroyed2=0;
    int enemyMultiplyerDestroyed3=0;
    int enemyMultiplyerDestroyed4=0;
    static Label LivesLabel, HIscoreLabel, ShieldLabel, scoreLabel, ammoLabel, playerLabel, upgradeLabel;
    public boolean BossSummoned;
    Shields shields;
    int maxShieldSize;
    // pixels per second
    int shieldRegenerationRate;
    static double PlayerHealth;static float DamPlay;
    public double PlayerShields;

    public Sound AlarmBoss, explodeSound, EnemyShootA, PlayerShootA, BossSpawn, PlayerHit, itemCollect, PHs;
    public Music MenuTrack, LevelTrack, PHm;
    //again this part here was for testing highscore to see if
    //it was actually saving the score we would get. 
    loseMessage loseMessage; 
    int playerLives= 1; // the amount of lives for the player. 
    // some of the above was changed to static so that other classes can see the variables. perhaps that is how we can get around a few of the issues.
    // can't say. only time will tell. Hopefully I will be able to remedy some of the issues that are inherently bad. 
    public void initialize()
    {
        new Space(0,0, mainStage);
        new Space(0,800, mainStage);
        //the below variable allows for manual changing of the ship it cooresponds to the index of the database or (fast, medium, slow)
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            SELECTED =0;
            Spawnrate=2;
        }
        if (Gdx.input.isKeyJustPressed(Keys.D)){
            SELECTED =2;
            Spawnrate=1;
        }
        if (Gdx.input.isKeyJustPressed(Keys.A)){
            SELECTED =1;
            Spawnrate=2;
        }
        new Planet(mainStage);

        shootTimer = 1;
        enemyTimer = 0;
        playerLives= 3; // the amount of lives for the player. 
        BossSummoned= false;
        //BossT=10000;
        player = new Player(350, 100, mainStage,SELECTED);

        maxShieldSize = Databases.getPlayerCopy(SELECTED).getSheilds();  

        shields = new Shields(0,0, mainStage);
        shields.setSize( maxShieldSize, maxShieldSize );
        shields.setBoundaryPolygon(8);
        // attach shield object to spaceship object
        shields.centerAt(player);
        shieldRegenerationRate = 1;

        PlayerHealth  = Databases.getPlayerCopy(SELECTED).getHealth();
        PlayerShields = Databases.getPlayerCopy(SELECTED).getSheilds();
        DamPlay= Databases.getPlayerCopy(SELECTED).getPlayerDamage();
        //the file needs to go first in order to save the highscore..
        if(SELECTED ==0)
        {
            File f = new File("highScoreShipSpeedy.txt");
            try
            {
                Scanner scan = new Scanner(f);
                highScore = scan.nextInt();
            }
            catch (Exception error)
            {
                // error.printStackTrace();
                highScore = 0;
            }
        }

        else if(SELECTED ==1)
        {
            File f = new File("highScoreShipAverage.txt");
            try
            {
                Scanner scan = new Scanner(f);
                highScore = scan.nextInt();
            }
            catch (Exception error)
            {
                // error.printStackTrace();
                highScore = 0;
            }

        } //note THIS IS NEEDED TO SEPERATE THE CODES. tried to do this before...but it created just the highScoreShipDefense file
        // file instead...so yes this is needed like this..
        else if(SELECTED ==2)
        {
            File f = new File("highScoreShipDefense.txt");
            try
            {
                Scanner scan = new Scanner(f);
                highScore = scan.nextInt();
            }
            catch (Exception error)
            {
                // error.printStackTrace();
                highScore = 0;
            }
        }

        score = 0; 
        scoreLabel = new Label("Score: " + score, BaseGame.labelStyle2);scoreLabel.setFontScale(0.4f);
        playerLabel = new Label("Health:"+PlayerHealth, BaseGame.labelStyle2);   
        playerLabel.setFontScale(0.4f);
        LivesLabel= new Label("Lives:" +playerLives, BaseGame.labelStyle2);LivesLabel.setFontScale(0.4f);
        HIscoreLabel= new Label("Highscores " + highScore, BaseGame.labelStyle2);HIscoreLabel.setFontScale(0.4f);
        ShieldLabel= new Label("Shields:"+PlayerShields, BaseGame.labelStyle2);ShieldLabel.setFontScale(0.4f);

        uiTable.add( playerLabel ).expandX().expandY().left().top().pad(20);
        uiTable.add( ShieldLabel ).expandX().expandY().left().top().pad(20);
        uiTable.add( HIscoreLabel  ).expandX().expandY().left().top().pad(20);
        uiTable.add( scoreLabel  ).expandX().expandY().left().top().pad(20);
        uiTable.row();
        uiTable.add(upgradeLabel).expandX().left().top().pad(20);
        uiTable.add();uiTable.add();
        uiTable.add(LivesLabel).expandX().right().top().pad(20);

        //uiTable.debugCell();
        loseMessage=new loseMessage(200,0,mainStage);
        loseMessage.setVisible(false);

        //explodeSound, EnemyShootA, PlayerShootA, BossSpawn, 
        //PlayerHit, itemCollect, PHs;
        //public Music MenuTrack, LevelTrack, PHm;
        LevelTrack= Gdx.audio.newMusic( Gdx.files.internal("assets/Sounds/bgm/Safe.ogg"));//music
        PlayerShootA= Gdx.audio.newSound( Gdx.files.internal("assets/Sounds/sfx/laser.ogg"));//auido
        itemCollect= Gdx.audio.newSound(Gdx.files.internal("assets/Sounds/sfx/Item-Collect.wav"));//auido
        AlarmBoss= Gdx.audio.newSound(Gdx.files.internal("assets/Sounds/sfx/alarm.wav"));//auido
        //hit
        //long laser fire
        //enemy fire sound
        //an enemy death sound.
        //PHs= Gdx.audio.newSound(Gdx.files.internal("assets/Sounds/sfx/dead.wav"));//auido
        LevelTrack.setLooping(true);//music to make sure it loops
        LevelTrack.play();// use this as a base you need to say it to play
    }

    public void update(float deltaTime)
    {
        shootTimer +=deltaTime;

        if (Gdx.input.isKeyPressed(Keys.LEFT))
            player.physics.accelerateAtAngle(180);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            player.physics.accelerateAtAngle(0);
        if (Gdx.input.isKeyPressed(Keys.UP))
            player.physics.accelerateAtAngle(90);
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            player.physics.accelerateAtAngle(270);

        // recenter shields on spaceship 
        shields.centerAt( player );

        if (shields.getWidth() <0)
        {
            shields.setSize( 0, 0 );
            shields.setBoundaryPolygon(16);
            ShieldLabel.setText("Shields:"+0);
        }

        if(SELECTED ==2)//change this later
        { //TRIPLE SHOT
            if (Gdx.input.isKeyJustPressed(Keys.SPACE) && shootTimer >.5 && player.isOnStage())
            {
                for(int a=1;a<4;a++){
                    int b= 60;
                    PlayerBullet pb = new PlayerBullet(0,0, mainStage);
                    pb.setRotation(b+(15*a));
                    pb.physics.setMotionAngle(b+(15*a)); 
                    pb.centerAt(player);

                }
                PlayerShootA.play();
                shootTimer=0;
            }
        }
        else if(SELECTED==1)
        {
            if(Gdx.input.isKeyJustPressed(Keys.SPACE) && shootTimer >1&& player.isOnStage())
            {
                PlayerBullet pb = new PlayerBullet(0,0, mainStage);PlayerBullet pb2 = new PlayerBullet(0,0, mainStage);
                pb.centerAt(player);pb2.centerAt(player);
                pb.moveBy(-50,0);pb2.moveBy(50,0);
                shootTimer=0;PlayerShootA.play();
            }
        }
        else{
            if(Gdx.input.isKeyJustPressed(Keys.SPACE) && shootTimer >.25&& player.isOnStage())
            {
                PlayerBullet pb = new PlayerBullet(0,0, mainStage);
                pb.centerAt(player);
                shootTimer=0;PlayerShootA.play();
            }}

        int planetCount = BaseActor.getList(mainStage, "Planet").size();
        if ( planetCount < 2 && Math.random() < 0.03 )
        {
            Planet p = new Planet(mainStage);
            p.toBack();
        }

        // prevent islands from overlapping with each other
        for (BaseActor planet1 : BaseActor.getList(mainStage, "Planet"))
        {
            for (BaseActor planet2 : BaseActor.getList(mainStage, "Planet"))
            {
                if (planet1 != planet2)
                    planet1.preventOverlap(planet2);
            }
        }

        for (BaseActor space : BaseActor.getList(mainStage, "Space"))
            space.toBack();

        enemyTimer += deltaTime;

        BossT+= deltaTime;
        //if (enemyTimer>5&&enemyTimer<5.1){new Boss(1,mainStage); }
        if (enemyTimer> Spawnrate && BossSummoned==false && player.isOnStage())
        {
            // spawn new enemy off-screen
            double RAND=Math.random()*8 + 1;
            if (RAND<5){
                new Enemy((int)RAND, mainStage);
                // reset the timer
                enemyTimer = 0;
            }else{
                // spawn new enemy off-screen
                new Enemy((int)RAND, mainStage);
                // reset the timer
                enemyTimer = 0;
            }
        }
        // else {if(BossT>100) {new Boss(1,mainStage); int BossSpawned=1;BossT=0;}
        int cycleKills = 50;
        
        if (enemyDestroyed>=cycleKills && BossSummoned==false)
        {
            double RAND=Math.random()*5;
            
            new Boss((int)RAND,mainStage);
            BossSummoned=true;
            enemyDestroyed=0;
            AlarmBoss.play();
        }
        
    
        for (BaseActor e : BaseActor.getList(mainStage, "Enemy"))
        {
            if ( e.overlaps(shields) && shields.getWidth()>0)
            {
                e.remove();
                Explosion explosion = new Explosion(0,0, mainStage);
                explosion.centerAt(e);

                float size = shields.getWidth();
                size -= 50;
                double sizeVAR= size;
                // shields.setSize( size, size );
                // gradually shrink size with an action
                shields.addAction( Actions.sizeTo(size, size, 0.25f) );
                shields.setBoundaryPolygon(8);
                ShieldLabel.setText("Shields:"+sizeVAR);

            }
            else if (e.overlaps(player))
            {
                Explosion exp = new Explosion(0, 0, mainStage);
                exp.centerAt(e);
                e.remove();

            }

            float itemChance = 0.2f;
            for(BaseActor playerbullet: BaseActor.getList(mainStage,"PlayerBullet"))
            {

                if(playerbullet.overlaps(e))
                {
                    Explosion exp = new Explosion(0,0,mainStage);
                    exp.centerAt(e);
                    playerbullet.remove();
                    e.remove();
                    enemyDestroyed++;
                    score+=100*enemyMultiplyer*enemyMultiplyer2*enemyMultiplyer3*enemyMultiplyer4;

                    if(Math.random() <itemChance)
                    {
                        PowerUps item = new PowerUps(0 , 0, mainStage);
                        item.centerAt(e);
                    }

                    //if(Math.random() <itemChance)
                    //{
                    //    PowerUps item = new PowerUps(0 , 0, mainStage);
                    //    item.centerAt(e);
                }
                scoreLabel.setText("Score: " + score);

            }}

        for(BaseActor powerup : BaseActor.getList (mainStage, "PowerUps"))
        {

            if(player.overlaps(powerup))
            {
                powerup.remove();
                String type = ((PowerUps)powerup).imageName;
                itemCollect.play();
                //will have to work on this at somepoint.. may have to remove the x10 powerup to prevent score overflow...
                //perhaps just keep the x4 instead.

                if ( type.equals("x2") )
                {
                    enemyMultiplyerDestroyed = enemyDestroyed+5;
                    if(enemyMultiplyerDestroyed == enemyDestroyed)
                    {
                        enemyMultiplyer = 1;

                    }
                    enemyMultiplyer = 2;

                }
                else if ( type.equals("x4") )
                {
                    enemyMultiplyerDestroyed2 = enemyDestroyed+5;
                    if(enemyMultiplyerDestroyed2 == enemyDestroyed)
                    {
                        enemyMultiplyer2 = 1;

                    }
                    enemyMultiplyer2 = 4;

                }
                else if ( type.equals("x6") )
                {
                    enemyMultiplyerDestroyed3 = enemyDestroyed+5;
                    if(enemyMultiplyerDestroyed3 == enemyDestroyed)
                    {
                        enemyMultiplyer3 = 1;

                    }
                    enemyMultiplyer3 = 6;
                }

                else if ( type.equals("x8") )
                {
                    enemyMultiplyerDestroyed4 = enemyDestroyed+5;
                    if(enemyMultiplyerDestroyed4 == enemyDestroyed)
                    {
                        enemyMultiplyer4 = 1;

                    }
                    enemyMultiplyer4 = 8;
                }

                else if( type.equals("health") )
                {
                    if (PlayerHealth < maxHealth)
                    {
                        PlayerHealth +=10;
                        playerLabel.setText("Health:"+PlayerHealth);
                    }
                }
                else if(type.equals("shieldrepair") )
                {
                    if(PlayerShields<maxShield)
                    {
                        float size = shields.getWidth();
                        size += 30;
                        double sizeVAR= size;
                        shields.setBoundaryPolygon(8);
                        shields.addAction( Actions.sizeTo(size, size, 0.25f) );
                        shields.setBoundaryPolygon(8);
                        ShieldLabel.setText("Shields:"+sizeVAR);

                    }
                }

                else if(type.equals("bomb"))
                {
                    for (BaseActor e : BaseActor.getList(mainStage, "Enemy"))
                    {
                        Explosion exp = new Explosion(0,0,mainStage);
                        exp.centerAt(e);
                        e.remove();
                        enemyDestroyed++;
                        score+=500;
                    }
                }

            }
        }

        for(BaseActor eb :BaseActor.getList(mainStage, "EnemyBullet"))   
        {
            if ( eb.overlaps(shields) && shields.getWidth()>0)
            {
                eb.remove();
                Explosion explosion = new Explosion(0,0, mainStage);
                explosion.centerAt(eb);

                float size = shields.getWidth();
                size -= 50;
                double sizeVAR= size;
                // shields.setSize( size, size );
                // gradually shrink size with an action
                shields.addAction( Actions.sizeTo(size, size, 0.25f) );
                shields.setBoundaryPolygon(8);
                ShieldLabel.setText("Shields:"+sizeVAR);

            }
        }

        for(BaseActor BossD :BaseActor.getList(mainStage, "Boss"))   
        {
            if ( BossD.overlaps(shields) && shields.getWidth()>0)
            {

                Explosion explosion = new Explosion(0,0, mainStage);
                explosion.centerAt(player);

                float size = shields.getWidth();
                size -= 50;
                double sizeVAR= size;
                // shields.setSize( size, size );
                // gradually shrink size with an action
                shields.addAction( Actions.sizeTo(size, size, 0.25f) );
                shields.setBoundaryPolygon(8);
                ShieldLabel.setText("Shields:"+sizeVAR);

            }

            for(BaseActor playerbullet: BaseActor.getList(mainStage,"PlayerBullet"))
            {
                if(playerbullet.overlaps(BossD))
                {
                    Explosion exp = new Explosion(0,0,mainStage);
                    exp.centerAt(playerbullet);

                }
            }

            if(Boss.Health<=0){
                BossSummoned=false;
                BossD.remove();enemyDestroyed=0;
            }
        }

        //managed to get a working lives system going for now...though there are errors with it
        //the second spawned ship not being able
        //to be controlled.. This is a good bases though

        if(PlayerHealth <= 0 && playerLives<=0)
        {

            player.remove();
            for (BaseActor e : BaseActor.getList(mainStage, "Enemy"))
            {
                e.remove();}
            loseMessage.setVisible(true);
            loseMessage.addAction(
                Actions.moveTo(200,350,1.5f));
            LivesLabel.setText("Press 'X' to go back to menu");
            if (Gdx.input.isKeyPressed(Keys.X))
                BaseGame.setActiveScreen( new MenuScreen());
                
            if(SELECTED==0 && score>highScore)
            {
                highScore = score;
                try
                {
                    File f = new File("highScoreShipSpeedy.txt");
                    PrintWriter pw = new PrintWriter(f);

                    pw.print(highScore);
                    pw.close();
                }
                catch(Exception error)

                {
                    error.printStackTrace();
                }
            }
            else if(SELECTED==1 && score>highScore)
            {
                highScore = score;
                try
                {
                    File f = new File("highScoreShipAverage.txt");
                    PrintWriter pw = new PrintWriter(f);

                    pw.print(highScore);
                    pw.close();

                }
                catch(Exception error)

                {
                    error.printStackTrace();
                }

            }
            //again this is indeed needed...without it it will just created the highscore defensive file without properly distributing
            //each of the ships. 
            else if(SELECTED==2 && score>highScore)
            {
                highScore=score;
                try
                {
                    File f = new File("highScoreShipDefense.txt");
                    PrintWriter pw = new PrintWriter(f);

                    pw.print(highScore);
                    pw.close();

                }
                catch(Exception error)

                {
                    error.printStackTrace();
                }

            }
            
        }else if(PlayerHealth<0)
        {
            Explosion exp = new Explosion(0,0,mainStage);
            exp.centerAt(player);
            player.remove();
            playerLives--;
            LivesLabel.setText("Lives"+playerLives);

            player = new Player(350, 100, mainStage,SELECTED);
            PlayerHealth  = Databases.getPlayerCopy(SELECTED).getHealth();
            playerLabel.setText("Health:"+PlayerHealth);
            PlayerShields = Databases.getPlayerCopy(SELECTED).getSheilds();
            shields = new Shields(0,0, mainStage);
            shields.setSize( maxShieldSize, maxShieldSize );
            shields.setBoundaryPolygon(8);
            // attach shield object to spaceship object
            shields.centerAt(player);
            shieldRegenerationRate = 1;
            update(0);
        }

    }
}
