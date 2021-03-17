import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;

public class LevelScreen extends BaseScreen
{ 
    Koala jack;
    Storage storage;
    TilemapActor level;
    Flag winner;
    float TimeLeftint=20;
    float Timespent= 0;

    public Sound Death, CollectC,CollectK, CollectClock, CollectH, DoorLockSFX;
    public Music LineBGM;

    BaseActor keyBlueIcon;
    BaseActor keyRedIcon;
    BaseActor keyGreenIcon;
    BaseActor keyYellowIcon;

    BaseActor loseMsg, winMsg;
    
    public LevelScreen(String mapFileName)
    {
        super(mapFileName);
    }
    Label Coin, HealthLabel, TimeLeft;
    public void initialize(String mapFileName) 
    {
        BaseActor background =  new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/gray-bricks.jpg") );
        storage=new Storage();

        try
        {
            level = new TilemapActor(mapFileName, mainStage);
        }
        catch (Exception error)
        {
            System.err.println("Unable to load tilemap file.");
            error.printStackTrace();
        }
        jack = new Koala(100,500, mainStage);

        Death = Gdx.audio.newSound(Gdx.files.internal("assets/audio/Death SFX.mp3"));
        CollectC=Gdx.audio.newSound(Gdx.files.internal("assets/audio/item-pickup.wav"));
        CollectK=Gdx.audio.newSound(Gdx.files.internal("assets/audio/CollectSFX.mp3"));
        CollectClock = Gdx.audio.newSound(Gdx.files.internal("assets/audio/tick-tock.wav"));
        CollectH = Gdx.audio.newSound(Gdx.files.internal("assets/audio/heartbeat.wav"));
        DoorLockSFX = Gdx.audio.newSound(Gdx.files.internal("assets/audio/unlock.wav"));

        keyYellowIcon = new BaseActor(0, 600, mainStage);
        keyYellowIcon.setAnimator(new Animator("assets/key/key-yellow-icon.png"));
        keyYellowIcon.setVisible(false);
        keyBlueIcon = new BaseActor(keyYellowIcon.getWidth(), 600, mainStage);
        keyBlueIcon.setAnimator(new Animator("assets/key/key-blue-icon.png"));
        keyBlueIcon.setVisible(false);
        keyGreenIcon = new BaseActor(keyYellowIcon.getWidth() * 2, 600, mainStage);
        keyGreenIcon.setAnimator(new Animator("assets/key/key-green-icon.png"));
        keyGreenIcon.setVisible(false);
        keyRedIcon = new BaseActor(keyYellowIcon.getWidth() * 3, 600, mainStage);
        keyRedIcon.setAnimator(new Animator("assets/key/key-red-icon.png"));
        keyRedIcon.setVisible(false);

        winMsg = new BaseActor(0, 0, mainStage);
        winMsg.setAnimator(new Animator("assets/win.png"));
        winMsg.setPosition(400 - (winMsg.getWidth() / 2), 300 - (winMsg.getHeight() / 2));
        winMsg.setVisible(false);
        loseMsg = new BaseActor(0, 0, mainStage);
        loseMsg.setAnimator(new Animator("assets/gameover.png"));
        loseMsg.setPosition(400 - (loseMsg.getWidth() / 2), 300 - (loseMsg.getHeight() / 2));
        loseMsg.setVisible(false);
        
        Coin= new Label("Coins: "+ storage.CoinTotal + "  ", BaseGame.labelStyle);
        Coin.setFontScale(0.5f);
        Coin.setColor(Color.YELLOW);
        HealthLabel = new Label("Health: " + Storage.health + "  ", BaseGame.labelStyle);
        HealthLabel.setFontScale(0.5f);
        HealthLabel.setColor(Color.RED);
        TimeLeft=new Label("Time Remaining: " + Timespent,BaseGame.labelStyle);
        TimeLeft.setFontScale(0.5f);
        TimeLeft.setColor(Color.PURPLE);

        uiTable.add().expandX();
        uiTable.add(Coin);
        uiTable.add(HealthLabel);
        uiTable.add(TimeLeft);
        uiTable.row();
        uiTable.add();
        uiTable.add().expandY();
    }

    public void update(float deltaTime)
    {
        if (!(loseMsg.isVisible() || winMsg.isVisible()))
        {
            if (storage.RedKey >= 1)
                keyRedIcon.setVisible(true);
            if (storage.BlueKey >= 1)
                keyBlueIcon.setVisible(true);
            if (storage.GreenKey >= 1)
                keyGreenIcon.setVisible(true);
            if (storage.YellowKey >= 1)
                keyYellowIcon.setVisible(true);

            storage.TimeSpent += deltaTime;
            storage.TimeLeftint-=deltaTime;
            if(storage.TimeSpent>=1)
            {
                TimeLeft.setText("Time Remaining: " +(int)storage.TimeLeftint);
                storage.TimeSpent=0;
            } 

            if (Gdx.input.isKeyPressed(Keys.LEFT))
                jack.physics.accelerateAtAngle(180);

            if (Gdx.input.isKeyPressed(Keys.RIGHT))
                jack.physics.accelerateAtAngle(0);

            if (Gdx.input.isKeyJustPressed(Keys.SPACE))
                jack.jump();

            if (Gdx.input.isKeyJustPressed(Keys.LEFT))
                jack.setX( jack.getX() - 1 );
            if (Gdx.input.isKeyJustPressed(Keys.RIGHT))
                jack.setX( jack.getX() + 1 );

            // coin collecting
            for (BaseActor coin : BaseActor.getList(mainStage, "Coin"))
            {
                if (jack.overlaps(coin))
                {
                    Sparkle s = new Sparkle(0, 0, mainStage, Sparkle.SparkleColor.YELLOW);
                    s.centerAt(coin);
                    coin.remove();
                    CollectC.play();
                    storage.CoinTotal++;
                    Coin.setText("Coins:" + storage.CoinTotal + "  ");
                }
            }

            if((Storage.CoinTotal >= 30) && (Storage.YellowKey <= 0))
            {
                Storage.YellowKey = 1;
                CollectK.play();
            }
            if((Storage.CoinTotal >= 50) && (Storage.GreenKey <= 0))
            {
                Storage.GreenKey = 1;
                CollectK.play();
            }

            // heart collecting
            for (BaseActor heart : BaseActor.getList(mainStage, "Heart"))
            {
                if (jack.overlaps(heart))
                {
                    Storage.health += 1;
                    Sparkle s = new Sparkle(0, 0, mainStage, Sparkle.SparkleColor.RED);
                    s.centerAt(heart);
                    heart.remove();
                    CollectH.play();
                }
            }
            // Updating every frame because the damage code is in the Koala class and this is just easier at this point in time.
            HealthLabel.setText("Health: " + Storage.health + "  ");

            // clock collecting
            for (BaseActor clock : BaseActor.getList(mainStage, "Clock"))
            {
                if (jack.overlaps(clock))
                {
                    storage.TimeLeftint += 30;
                    TimeLeft.setText("Time Remaining: " + (int)storage.TimeLeftint);
                    Sparkle s = new Sparkle(0, 0, mainStage, Sparkle.SparkleColor.VIOLET);
                    s.centerAt(clock);
                    clock.remove();
                    CollectClock.play();
                }
            }

            // check if trying to enter a door in the level
            for (BaseActor actor : BaseActor.getList(mainStage, "Door"))
            {
                Door door = (Door)actor;
                if (Gdx.input.isKeyJustPressed(Keys.UP) && jack.overlaps(door))
                {
                    jack.physics.velocity.set(0,0);
                    jack.physics.acceleration.set(0,0);

                    LevelScreen nextRoom = JumpingJackGame.roomList.get( door.targetRoom );
                    Koala nextKoala = nextRoom.jack;

                    // find the door that leads back to current room,
                    //   and position the koala there.
                    for (BaseActor nextActor : BaseActor.getList( nextKoala.getStage(), "Door" ) )
                    {
                        Door nextDoor = (Door)nextActor;
                        if ( nextDoor.targetRoom == this.level.roomNumber )
                            nextKoala.setPosition( nextDoor.getX()-2, nextDoor.getY() );
                    }
                    DoorLockSFX.play();
                    // set the active screen to be correct room number
                    LevelScreen nextScreen = JumpingJackGame.roomList.get(door.targetRoom);
                    nextScreen.Coin.setText("Coins: " + storage.CoinTotal + "  ");
                    nextScreen.HealthLabel.setText("Health: " + Storage.health + "  ");
                    nextScreen.TimeLeft.setText("Time Remaining: " + (int)storage.TimeLeftint);
                    BaseGame.setActiveScreen(nextScreen);
                }
            }

            // Koala Events
            for(BaseActor jack:BaseActor.getList(mainStage,"Koala"))
            {
                // Key collecting
                for(BaseActor key:BaseActor.getList(mainStage,"KeyR"))
                {
                    if(jack.overlaps(key)){
                        key.remove();
                        keyRedIcon.setVisible(true);
                        storage.RedKey++;
                        CollectK.play();
                    }
                }
                for(BaseActor key:BaseActor.getList(mainStage,"KeyB"))
                {
                    if(jack.overlaps(key)){
                        key.remove();
                        keyBlueIcon.setVisible(true);
                        storage.BlueKey++;
                        CollectK.play();
                    }
                }
                for(BaseActor key : BaseActor.getList(mainStage, "KeyG"))
                {
                    if (jack.overlaps(key))
                    {
                        key.remove();
                        keyGreenIcon.setVisible(true);
                        storage.GreenKey++;
                        CollectK.play();
                    }
                }
                for (BaseActor key : BaseActor.getList(mainStage, "KeyY"))
                {
                    if (jack.overlaps(key))
                    {
                        key.remove();
                        keyYellowIcon.setVisible(true);
                        storage.YellowKey++;
                        CollectK.play();
                    }
                }

                // Unlock pathways
                for(BaseActor lock:BaseActor.getList(mainStage,"LockY"))
                {
                    if(jack.overlaps(lock)&&storage.YellowKey>=1){
                        lock.remove();
                        DoorLockSFX.play();
                    }
                }
                for(BaseActor lock:BaseActor.getList(mainStage,"LockB"))
                {
                    if(jack.overlaps(lock)&&storage.BlueKey>=1){
                        lock.remove();
                        DoorLockSFX.play();
                    }
                }
                for(BaseActor lock:BaseActor.getList(mainStage,"LockG"))
                {
                    if(jack.overlaps(lock)&&storage.GreenKey>=1){
                        lock.remove();
                        DoorLockSFX.play();
                    }
                }
                for(BaseActor lock:BaseActor.getList(mainStage,"LockR"))
                {
                    if(jack.overlaps(lock)&&storage.RedKey>=1){
                        lock.remove();
                        DoorLockSFX.play();
                    }
                }

                // Win game.
                for(BaseActor winner:BaseActor.getList(mainStage,"Flag"))
                {
                    if(jack.overlaps(winner)){
                        CollectK.play();
                        jack.remove();
                        winMsg.setVisible(true);
                        //System.out.println("Won test New High Score\n But your Princess is in another field.");
                    }
                }
            }
            
            if (Storage.health <= 0)
            {
                Death.play();
                jack.remove();
                loseMsg.setVisible(true);
            }
            if (Storage.TimeLeftint<= 0){
                Death.play();
                jack.remove();
                loseMsg.setVisible(true);
            }
        }   
    }
}
