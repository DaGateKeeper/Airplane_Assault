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

    BaseActor background;

    //the initial things we need to get like the sounds and the name of objects
    Sound missile,Ph;//any variation of PH is just a placeholder
    Music PIM;

    EnemySub enemysub;

    Submarine submarine;
    Core mainCore;
    int score;
    Label coreLabel, scoreLabel, ammoLabel;

    Label laserStatus;
    double enemyTimer;

    public Sound itemSFX, itemBombSFX, pierceSFX;
    
    public void initialize()
    {
        background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/images/water.jpg") );
        background.setSize(800,600);

        submarine = new Submarine(190, 25, mainStage);

        mainCore = new Core(0,0, mainStage);
        mainCore.setSize(150, 600);
        mainCore.setBoundaryRectangle();

        enemyTimer = 0;

        score = 0;
        scoreLabel = new Label("Score: " + score, BaseGame.labelStyle);
        scoreLabel.setFontScale(0.5f);

        ammoLabel = new Label("Ammo: " + submarine.normalAmmo, BaseGame.labelStyle);
        ammoLabel.setFontScale(0.5f);

        coreLabel = new Label("Core: " + mainCore.health, BaseGame.labelStyle);
        coreLabel.setFontScale(0.5f);
        uiTable.add().expandX();
        uiTable.add( scoreLabel );
        uiTable.row();
        uiTable.add();
        uiTable.add( ammoLabel );
        uiTable.row();
        uiTable.add();
        uiTable.add().expandY();
        uiTable.add(coreLabel).right().bottom().pad(10);

        PIM = Gdx.audio.newMusic( Gdx.files.internal("assets/audio/bgm/Safe.ogg"));
        missile = Gdx.audio.newSound( Gdx.files.internal("assets/audio/sfx/Missile-Launch.wav"));
        itemSFX = Gdx.audio.newSound(Gdx.files.internal("assets/audio/sfx/Item-Collect.wav"));
        itemBombSFX = Gdx.audio.newSound(Gdx.files.internal("assets/audio/sfx/Get-Rid-Of.ogg"));
        pierceSFX = Gdx.audio.newSound(Gdx.files.internal("assets/audio/sfx/drop-sword.wav"));
        
        PIM.setLooping(true);
        PIM.play();
    }

    public void update(float deltaTime)
    {
        if (!gameOver)
        {
            // move paddle to the left and to the right
            if (Gdx.input.isKeyPressed(Keys.UP))
                submarine.physics.accelerateAtAngle(90);

            if (Gdx.input.isKeyPressed(Keys.DOWN))
                submarine.physics.accelerateAtAngle(-90);

            submarine.shotTimer += deltaTime;
            enemyTimer += deltaTime;

            // you can only shoot if:
            // 1. you just pressed the space key
            // 2. the submarine is still on the stage (still visible)
            // 3. at least one second has passed since previous shot (laserTimer > 1)
            if (Gdx.input.isKeyJustPressed( Keys.SPACE ))
                submarine.fire(this);

            if (enemyTimer >= 1)
            {
                if (Math.random() <= 0.925)
                {
                    EnemySub e = new EnemySub(0, 0, mainStage);
                    e.setPosition(800, (float)(Math.random() * (600 - e.getHeight())));
                }
                else
                {
                    HealSub h = new HealSub(0, 0, mainStage);
                    h.setPosition(800, (float)(Math.random() * (600 - h.getHeight())));
                }
                enemyTimer = 0;
            }

            for (BaseActor h : BaseActor.getList(mainStage, "HealSub"))
            {
                if (h.overlaps(mainCore))
                {
                    h.remove();
                    mainCore.health += 2;
                    if (mainCore.health > 10)
                        mainCore.health = 10;
                    coreLabel.setText("Core: " + mainCore.health);
                }

                for (BaseActor l : BaseActor.getList(mainStage, "Laser"))
                    if (l.overlaps(h))
                    {
                        EnemySub e = new EnemySub(0, 0, mainStage);
                        e.centerAt(h);
                        Explosion exp = new Explosion(0, 0, mainStage);
                        exp.centerAt(h);
                        h.remove();
                        l.remove();
                    }
                for (BaseActor p : BaseActor.getList(mainStage, "Piercing"))
                    if (p.overlaps(h))
                    {
                        Explosion exp = new Explosion(0, 0, mainStage);
                        exp.centerAt(h);
                        h.remove();
                    }
            }

            for (BaseActor e : BaseActor.getList(mainStage, "EnemySub"))
            {
                if (e.overlaps(submarine))
                {
                    Explosion exp = new Explosion(0, 0, mainStage);
                    exp.centerAt(e);
                    score += 5;
                    scoreLabel.setText( "Score: " + score);
                    e.remove();
                }
                
                float itemChance = 0.5f;
                for (BaseActor l : BaseActor.getList(mainStage, "Laser"))
                    if (l.overlaps(e))
                    {
                        Explosion exp = new Explosion(0, 0, mainStage);
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
                    }
                for (BaseActor p : BaseActor.getList(mainStage, "Piercing"))
                    if (p.overlaps(e))
                    {
                        Explosion exp = new Explosion(0, 0, mainStage);
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

                if (e.overlaps(mainCore))
                {
                    Explosion exp = new Explosion(0, 0, mainStage);
                    exp.centerAt(e);
                    e.remove();
                    mainCore.health -= 2;
                    coreLabel.setText("Core: " + mainCore.health);
                    if (mainCore.health <= 0)
                        endGame();
                }
            }

            //ITEMS SPAWN
            for (BaseActor actor : BaseActor.getList(mainStage, "Item"))
            {
                Item item = (Item)actor;
                if ( submarine.overlaps(item) )
                {
                    switch(item.itemName)
                    {
                        // Goes through enemies.
                        case "pierce-shot":
                            submarine.weapon = 1;
                            submarine.specialAmmo = 10;
                            ammoLabel.setText("Piercing Ammo: " + submarine.specialAmmo);
                            pierceSFX.play();
                            break;
                        // Destroys all enemies.
                        case "bomb-shot":
                            submarine.weapon = 2;
                            submarine.specialAmmo = 1;
                            ammoLabel.setText("Bombs: " + submarine.specialAmmo);
                            itemBombSFX.play();
                            break;
                        // Fire shots faster.
                        case "rapid-fire":
                            submarine.specialAmmo = 25;                      
                            submarine.weapon = 3;
                            ammoLabel.setText("Rapid Fire Ammo: " + submarine.specialAmmo);
                            itemSFX.play();
                            break;
                        // Add normal ammo.
                        case "extra-ammo-15": 
                            submarine.normalAmmo += 15;
                            if (submarine.weapon <= 0)
                                ammoLabel.setText("Ammo: " + submarine.normalAmmo);
                            itemSFX.play();
                            break;
                        // Return weapon back to normal.
                        default:
                            submarine.weapon = 0;
                            submarine.specialAmmo = 0;
                            ammoLabel.setText("Ammo: " + submarine.normalAmmo);
                            itemSFX.play();
                            break;
                    }
                    item.remove();
                }
            }
        }
        else
        {
            enemyTimer += deltaTime;
            if (enemyTimer >= 0.1)
            {
                Explosion exp = new Explosion(0, 0, mainStage);
                exp.setScale(2);
                exp.setPosition((float)(Math.random() * (mainCore.getWidth() / 2) + (mainCore.getWidth() / 4)) - (exp.getWidth() / 2),
                    (float)(Math.random() * (mainCore.getHeight() * 0.7) + (mainCore.getHeight() * 0.1)));
                enemyTimer = 0;
            }
        }
    }

    public void endGame()
    {
        gameOver = true;
        enemyTimer = 0;
        GameOver msg = new GameOver(0, 0, mainStage);
        msg.centerAt(background);
        Explosion exp = new Explosion(0, 0, mainStage);
        exp.centerAt(submarine);
        submarine.remove();
        for (BaseActor e : BaseActor.getList(mainStage, "EnemySub"))
        {
            exp = new Explosion(0, 0, mainStage);
            exp.centerAt(e);
            e.remove();
        }
        for (BaseActor h : BaseActor.getList(mainStage, "HealSub"))
        {
            exp = new Explosion(0, 0, mainStage);
            exp.centerAt(h);
            h.remove();
        }
        for (BaseActor l : BaseActor.getList(mainStage, "Laser"))
            l.remove();
        for (BaseActor p : BaseActor.getList(mainStage, "Piercing"))
            p.remove();
        for (BaseActor i : BaseActor.getList(mainStage, "Item"))
            i.remove();
    }
}