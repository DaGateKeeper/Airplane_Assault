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

    public Player player;
    public float enemyTimer;
    public int  enemyDestroyed;
    
    int score, upgradeNum;
    Label LivesLabel, HIscoreLabel, ShieldLabel, scoreLabel, ammoLabel, playerLabel, upgradeLabel;
    public void initialize()
    {
        new Ocean(0,0, mainStage);
        new Ocean(0,800, mainStage);
        //the below variable allows for manual changing of the ship it cooresponds to the index of the database or (fast, medium, slow)
        int SELECTED = 2;
        new Island(mainStage);

        
        enemyTimer = 0;

        player = new Player(350, 100, mainStage,SELECTED);
        score = 0; 
        scoreLabel = new Label("Score: " + score, BaseGame.labelStyle);scoreLabel.setFontScale(0.5f);
        playerLabel = new Label("Health:"+Databases.getPlayerCopy(SELECTED).getHealth(), BaseGame.labelStyle);   
        playerLabel.setFontScale(0.5f);
        upgradeLabel = new Label("Upgrades: " + upgradeNum, BaseGame.labelStyle);upgradeLabel.setFontScale(0.5f);
        LivesLabel= new Label("Lives: ", BaseGame.labelStyle);LivesLabel.setFontScale(0.5f);
        HIscoreLabel= new Label("Highscores ", BaseGame.labelStyle);HIscoreLabel.setFontScale(0.5f);
        ShieldLabel= new Label("Shields:"+Databases.getPlayerCopy(SELECTED).getSheilds(), BaseGame.labelStyle);ShieldLabel.setFontScale(0.5f);
        
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
       
    }

    public void update(float deltaTime)
    {
        // user input
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            player.physics.accelerateAtAngle(180);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            player.physics.accelerateAtAngle(0);
        if (Gdx.input.isKeyPressed(Keys.UP))
            player.physics.accelerateAtAngle(90);
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            player.physics.accelerateAtAngle(270);

        if (Gdx.input.isKeyJustPressed(Keys.SPACE))
        {
            PlayerBullet pb = new PlayerBullet(0,0, mainStage);
            pb.centerAt(player);
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

        if (enemyTimer > 2)
        {
            // spawn new enemy off-screen
            //double RAND=Math.random()*4 + 1;
            double RAND=5;
            // spawn new enemy off-screen
            new Enemy((int)RAND, mainStage);
            // reset the timer
            enemyTimer = 0;
        }
        
        for (BaseActor e : BaseActor.getList(mainStage, "Enemy"))
        {
            if (e.overlaps(player))
            {
                Explosion exp = new Explosion(0, 0, mainStage);
                exp.centerAt(e);
                e.remove();
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

                }
            }
        }
    }
}
