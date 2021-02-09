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
    boolean gameOver;
    public float laserTimer;
    public Player player;
    public float enemyTimer;
    int score, BlueNum;
    Label scoreLabel, ammoLabel, playerLabel, BlueLabel;
    public Music Danger;
    public Sound itemSFX, itemBombSFX, pierceSFX,Dead,Bonus ,SPACE,shot;
    public void initialize()
    {
        new Ocean(0,0, mainStage);
        new Ocean(0,800, mainStage);

        new Island(mainStage);
        BlueNum=0;
        enemyTimer = 0;
        laserTimer = 0;
        player = new Player(350, 100, mainStage);
        score = 0; 
        scoreLabel = new Label("Score: " + score, BaseGame.labelStyle);
        scoreLabel.setFontScale(0.5f);
        playerLabel = new Label("Health: " + player.health, BaseGame.labelStyle);       
        playerLabel.setFontScale(0.5f);
        BlueLabel = new Label("Blueprint Count: " + BlueNum, BaseGame.labelStyle);
        BlueLabel.setFontScale(0.5f);
        uiTable.add( playerLabel ).expandX().expandY().left().top().pad(20);
        uiTable.add( scoreLabel ).expandX().expandY().left().top().pad(20);
        uiTable.add( BlueLabel  ).expandX().expandY().left().top().pad(20);

        uiTable.row();
        uiTable.add();
        shot = Gdx.audio.newSound(Gdx.files.internal("assets/laser.ogg"));
        SPACE = Gdx.audio.newSound(Gdx.files.internal("assets/SPACE.wav"));
        Bonus = Gdx.audio.newSound(Gdx.files.internal("assets/bonus02.wav"));
        Dead = Gdx.audio.newSound(Gdx.files.internal("assets/Dishonor1.wav"));
        Danger = Gdx.audio.newMusic(Gdx.files.internal("assets/DangerZcover.mp3"));
        itemSFX = Gdx.audio.newSound(Gdx.files.internal("assets/Item-Collect.wav"));
        itemBombSFX = Gdx.audio.newSound(Gdx.files.internal("assets/Get-Rid-Of.ogg"));
        pierceSFX = Gdx.audio.newSound(Gdx.files.internal("assets/drop-sword.wav"));
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
        laserTimer += deltaTime;
        if (Gdx.input.isKeyPressed(Keys.SPACE)&& laserTimer > .1f)
        {
            player.fire(this);
            shot.play();
            laserTimer=0;
        }

        int islandCount = BaseActor.getList(mainStage, "Island").size();
        if ( islandCount < 2 && Math.random() < 0.03 )
        {
            Island i = new Island(mainStage);
            i.toBack();
        }
        Danger.play();
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
        if (enemyTimer >2){ 

            double RAND=Math.random()*4 + 1;
            // spawn new enemy off-screen
            new Enemy((int)RAND, mainStage);

            // reset the timer
            enemyTimer = 0;
        }

        for(BaseActor eb :BaseActor.getList(mainStage, "EnemyBullet"))   
        {
            if (eb.overlaps(player)){
                Explosion exp = new Explosion(0, 0, mainStage,0 );
                exp.centerAt(player);
                eb.remove();
                player.health -= 10;
                playerLabel.setText("Health: " + player.health);

            }
        }

        for (BaseActor e : BaseActor.getList(mainStage, "Enemy"))
        {
            if (e.overlaps(player))
            {
                Explosion exp = new Explosion(0, 0, mainStage, 1);
                exp.centerAt(e);
                score += 5;
                scoreLabel.setText( "Score: " + score);
                e.remove();
            }
            float BPChance = 0.25f;
            float itemChance = 0.5f;
            for (BaseActor l : BaseActor.getList(mainStage, "PlayerBullet"))
                if (l.overlaps(e))
                {
                    Explosion exp = new Explosion(0, 0, mainStage, 1);
                    exp.centerAt(e);
                    score += 10;
                    scoreLabel.setText( "Score: " + score);
                    e.remove();
                    l.remove();

                    if (Math.random() < itemChance)
                    {
                        Item item = new Item(0, 0, mainStage);
                        item.centerAt(e);
                    } 
                    if (Math.random() < BPChance)
                    {
                        BluePrint bp = new BluePrint(0, 0, mainStage);
                        bp.centerAt(e);
                    } 
                } 
            for (BaseActor p : BaseActor.getList(mainStage, "Piercing"))
                if (p.overlaps(e))
                {
                    Explosion exp = new Explosion(0, 0, mainStage,1);
                    exp.centerAt(e);
                    score += 10;
                    scoreLabel.setText( "Score: " + score);
                    e.remove();

                    if (Math.random() < itemChance)
                    {
                        Item item = new Item(0, 0, mainStage);
                        item.centerAt(e);
                    }
                }

        }
        //ITEMS SPAWN
        for (BaseActor actor : BaseActor.getList(mainStage, "Item"))
        {
            Item item = (Item)actor;
            if ( player.overlaps(item) )
            {
                switch(item.itemName)
                {
                    // Goes through enemies.
                    case "pierce-shot":
                    player.weapon = 1;
                    player.specialAmmo = 1;

                    pierceSFX.play();
                    break;
                    // Destroys all enemies.
                    case "bomb-shot":
                    player.weapon = 2;
                    player.specialAmmo = 1;

                    itemBombSFX.play();
                    break;

                    // Return weapon back to normal.
                    default:
                    player.weapon = 0;
                    player.specialAmmo = 0;

                    itemSFX.play();
                    break;
                }
                item.remove();
            }
        }
        for (BaseActor actor : BaseActor.getList(mainStage, "BluePrint"))
        {
            BluePrint bp = (BluePrint)actor;
            if ( player.overlaps(bp) )
            {
                BlueNum+=1;
                BlueLabel.setText("Blueprint Count: " + BlueNum);
                bp.remove();
                Bonus.play();
            }

        }
        
        // if(player.health==0){
            // BaseGame.setActiveScreen( new Loss() );
            // Danger.stop();
            // Dead.play();
        // }
    }
}