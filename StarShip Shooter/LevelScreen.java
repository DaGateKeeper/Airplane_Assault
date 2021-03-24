import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class LevelScreen extends BaseScreen
{
    public float shootTimer;
    public Player player;
    public float enemyTimer, BossT;
    public int  enemyDestroyed;
    int SELECTED;
    int score,playerLives, upgradeNum;
    static Label LivesLabel, Debug, HIscoreLabel, ShieldLabel, scoreLabel, ammoLabel, playerLabel, upgradeLabel;
    public boolean BossSummoned;
    Shields shields;
    int maxShieldSize;
    // pixels per second
    int shieldRegenerationRate;
    static double PlayerHealth, debugh;
    public double PlayerShields;
    // some of the above was changed to static so that other classes can see the variables. perhaps that is how we can get around a few of the issues.
    // can't say. only time will tell. Hopefully I will be able to remedy some of the issues that are inherently bad. 
    public void initialize()
    {
        new Ocean(0,0, mainStage);
        new Ocean(0,800, mainStage);
        //the below variable allows for manual changing of the ship it cooresponds to the index of the database or (fast, medium, slow)
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            SELECTED =0;
        }
        if (Gdx.input.isKeyJustPressed(Keys.D)){
            SELECTED =2;
        }
        if (Gdx.input.isKeyJustPressed(Keys.A)){
            SELECTED =1;
        }
        new Island(mainStage);

        shootTimer = 1;
        enemyTimer = 0;
        playerLives= 3; // the amount of lives for the player. 
        BossSummoned= false;
        //BossT=10000;
        player = new Player(350, 100, mainStage,SELECTED);

        debugh=Databases.getBossStats(0).getHealth();
        //Debug= new Label("Health:"+debugh, BaseGame.labelStyle);
        Debug= new Label("Destroyed:"+enemyDestroyed, BaseGame.labelStyle);

        Debug.setFontScale(0.5f);
        maxShieldSize = Databases.getPlayerCopy(SELECTED).getSheilds();  

        shields = new Shields(0,0, mainStage);
        shields.setSize( maxShieldSize, maxShieldSize );
        shields.setBoundaryPolygon(8);
        // attach shield object to spaceship object
        shields.centerAt(player);
        shieldRegenerationRate = 1;

        PlayerHealth  = Databases.getPlayerCopy(SELECTED).getHealth();
        PlayerShields = Databases.getPlayerCopy(SELECTED).getSheilds();

        score = 0; 
        scoreLabel = new Label("Score: " + score, BaseGame.labelStyle);scoreLabel.setFontScale(0.5f);
        playerLabel = new Label("Health:"+PlayerHealth, BaseGame.labelStyle);   
        playerLabel.setFontScale(0.5f);
        upgradeLabel = new Label("Upgrades: " + upgradeNum, BaseGame.labelStyle);upgradeLabel.setFontScale(0.5f);
        LivesLabel= new Label("Lives:" +playerLives, BaseGame.labelStyle);LivesLabel.setFontScale(0.5f);
        HIscoreLabel= new Label("Highscores ", BaseGame.labelStyle);HIscoreLabel.setFontScale(0.5f);
        ShieldLabel= new Label("Shields:"+PlayerShields, BaseGame.labelStyle);ShieldLabel.setFontScale(0.5f);

        uiTable.add( playerLabel ).expandX().expandY().left().top().pad(20);
        uiTable.add( ShieldLabel ).expandX().expandY().left().top().pad(20);
        uiTable.add( HIscoreLabel  ).expandX().expandY().left().top().pad(20);
        uiTable.add( scoreLabel  ).expandX().expandY().left().top().pad(20);
        uiTable.row();
        uiTable.add(upgradeLabel).expandX().left().top().pad(20);
        uiTable.add();uiTable.add();
        uiTable.add(LivesLabel).expandX().right().top().pad(20);

        //uiTable.debugCell();
        uiTable.row();
        uiTable.add();
        uiTable.add(Debug).expandX().right().top().pad(20);
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

        if(SELECTED ==0)
        {

            if (Gdx.input.isKeyPressed(Keys.SPACE) && shootTimer >.25)
            {
                PlayerBullet pb = new PlayerBullet(0,0, mainStage);
                pb.centerAt(player);
                shootTimer=0;
            }

        }
        else if(SELECTED == 1)
        {

            if (Gdx.input.isKeyJustPressed(Keys.SPACE) && shootTimer >1)
            {
                PlayerBullet pb = new PlayerBullet(0,10, mainStage);
                pb.centerAt(player);

                pb.centerAt(player);
                shootTimer=0;
            }

        }
        else if(SELECTED==2)
        {

            if(Gdx.input.isKeyJustPressed(Keys.SPACE) && shootTimer >1)
            {
                PlayerBullet pb = new PlayerBullet(0,0, mainStage);

                pb.centerAt(player);
                shootTimer=0;
            }

        }

        int islandCount = BaseActor.getList(mainStage, "Island").size();
        if ( islandCount < 2 && Math.random() < 0.03 )
        {
            Island i = new Island(mainStage);
            i.toBack();
        }

        // prevent islands from overlapping with each other
        for (BaseActor island1 : BaseActor.getList(mainStage, "Island"))
        {
            for (BaseActor island2 : BaseActor.getList(mainStage, "Island"))
            {
                if (island1 != island2)
                    island1.preventOverlap(island2);
            }
        }

        for (BaseActor ocean : BaseActor.getList(mainStage, "Ocean"))
            ocean.toBack();

        enemyTimer += deltaTime;

        BossT+= deltaTime;

        if (enemyTimer> 2 && BossSummoned==false)
        {
            // spawn new enemy off-screen
            double RAND=Math.random()*4 + 1;

            // spawn new enemy off-screen
            new Enemy((int)RAND, mainStage);
            // reset the timer
            enemyTimer = 0;
        }
        // else {
        //if(BossT>100)
        //{new Boss(1,mainStage);
        // int BossSpawned=1;
        //   BossT=0;
        // }
        if (enemyDestroyed==5)
        {
            new Boss(1,mainStage);
            BossSummoned=true;
            enemyDestroyed=0;
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
                PlayerHealth-=10;
                playerLabel.setText("Health:"+PlayerHealth);
            }

            for(BaseActor playerbullet: BaseActor.getList(mainStage,"PlayerBullet"))
            {
                if(playerbullet.overlaps(e))
                {
                    Explosion exp = new Explosion(0,0,mainStage);
                    exp.centerAt(e);
                    playerbullet.remove();
                    e.remove();
                    enemyDestroyed++;
                    score+=100;
                    scoreLabel.setText("Score: " + score);
                    Debug.setText("Destroyed" +enemyDestroyed);
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
        //including the issue of not tracking the other ships except for speedy.. or having the second spawned ship not being able
        //to be controlled.. This is a good bases though
        for(BaseActor player: BaseActor.getList(mainStage,"Player"))
        {
            if(PlayerHealth==0){
                Explosion exp = new Explosion(0,0,mainStage);
                exp.centerAt(player);

                player.remove();
                playerLives--;

                player = new Player(350, 100, mainStage,SELECTED);
                PlayerHealth  = Databases.getPlayerCopy(SELECTED).getHealth();
                PlayerShields = Databases.getPlayerCopy(SELECTED).getSheilds();

            }
        }
    }
}
