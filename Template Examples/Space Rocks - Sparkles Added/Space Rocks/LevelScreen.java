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
    Spaceship spaceship;

    double laserTimer;
    Label laserStatus;

    int score;
    Label scoreDisplay;

    Shields shields;
    int maxShieldSize;
    // pixels per second
    int shieldRegenerationRate;

    Thruster thruster;

    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/space.png") );

        spaceship = new Spaceship(400,300, mainStage);

        for (int n = 0; n < 20; n++)
        {
            new Rock(1000, (float)Math.random() * 600, mainStage);
        }

        maxShieldSize = 200;

        shields = new Shields(0,0, mainStage);
        shields.setSize( maxShieldSize, maxShieldSize );
        shields.setBoundaryPolygon(8);
        // attach shield object to spaceship object
        shields.centerAt(spaceship);
        shieldRegenerationRate = 10;

        // attach thrusters to spaceship
        thruster = new Thruster(0,0, mainStage);
        spaceship.addActor(thruster);
        thruster.moveBy( -64, 32 );

        // ready to shoot immediately
        laserTimer = 1;

        score = 0;
        scoreDisplay = new Label("Score: " + score, BaseGame.labelStyle);
        // scale font to half (0.5) size
        scoreDisplay.setFontScale(0.5f);

        laserStatus = new Label("Laser ready!", BaseGame.labelStyle);
        laserStatus.setFontScale(0.5f);

        uiTable.add(scoreDisplay);
        uiTable.add().expandX();
        uiTable.add(laserStatus);
        uiTable.row();
        uiTable.add().expandY();
        uiTable.add();
        uiTable.add();

    }

    public void update(float deltaTime)
    {
        // keep track of how much time has passed 
        laserTimer += deltaTime;

        float rotationAmount = 1;

        if ( Gdx.input.isKeyPressed( Keys.LEFT ) )
            spaceship.rotateBy( rotationAmount );

        if ( Gdx.input.isKeyPressed( Keys.RIGHT ) )
            spaceship.rotateBy( -rotationAmount );

        if ( Gdx.input.isKeyPressed( Keys.UP ) )
        {
            spaceship.physics.accelerateAtAngle( spaceship.getRotation() );
            thruster.setVisible(true);
        }
        else
        {
            thruster.setVisible(false);
        }

        // THINGS THAT CAN HAPPEN ONLY WHEN SHIP IS ON STAGE
        if ( spaceship.isOnStage() )
        {
            // you can only shoot if:
            // 1. you just pressed the space key
            // 2. the spaceship is still on the stage (still visible)
            // 3. at least one second has passed since previous shot (laserTimer > 1)
            if ( Gdx.input.isKeyJustPressed( Keys.SPACE ) && laserTimer > 1)
            {
                Laser laser = new Laser(0,0, mainStage);
                laser.centerAt( spaceship );
                laser.setRotation( spaceship.getRotation() );
                laser.physics.setMotionAngle( spaceship.getRotation() );

                // reset timer, so player can not shoot until time goes by
                laserTimer = 0;
            }

            // update laserStatus
            if (laserTimer <= 1)
            {   
                laserStatus.setText("Charging...");
                laserStatus.setColor( Color.YELLOW );
            }
            else // laser is ready
            {
                laserStatus.setText("Laser ready!");
                laserStatus.setColor( Color.GREEN );
            }

            // recenter shields on spaceship 
            shields.centerAt( spaceship );

            // optional: rotate shields along with ship
            // shields.setRotation( spaceship.getRotation() );

            // optional: regenerate shields
            if (shields.getWidth() < maxShieldSize)
            {
                float shieldSize = shields.getWidth();
                shieldSize += shieldRegenerationRate * deltaTime;
                shields.setSize( shieldSize, shieldSize );
                shields.setBoundaryPolygon(16);
            }

            for (BaseActor rock : BaseActor.getList(mainStage, "Rock"))
            {
                // check for spaceship - rock collisions
                if ( rock.overlaps(spaceship) )
                {
                    spaceship.remove();
                    shields.remove();
                    Explosion explosion = new Explosion(0,0, mainStage);
                    explosion.setSize(300,300);
                    explosion.centerAt(spaceship);

                    laserStatus.setText("Ship offline...");
                    laserStatus.setColor( Color.RED );
                }

                // check for rock - shield collisions
                if ( rock.overlaps(shields) )
                {
                    rock.remove();
                    Explosion explosion = new Explosion(0,0, mainStage);
                    explosion.centerAt(rock);

                    float size = shields.getWidth();
                    size -= 50;
                    // shields.setSize( size, size );
                    // gradually shrink size with an action
                    shields.addAction( Actions.sizeTo(size, size, 0.25f) );
                    shields.setBoundaryPolygon(8);
                }
            }

            // collecting powerups
            for (BaseActor powerup : BaseActor.getList(mainStage, "Powerup"))
            {
                if (spaceship.overlaps(powerup))
                {
                    // spawn sparkle effect
                    Sparkle sparkle = new Sparkle(0,0, mainStage);
                    sparkle.centerAt(powerup);
                    
                    powerup.remove();
                    // optional: increase shield size
                    // maxShieldSize += 25;
                    
                    // optional: reset to maximum
                    shields.setSize( maxShieldSize, maxShieldSize );
                    
                    
                    
                    shields.setBoundaryPolygon(8);
                }
            }
        }
        for (BaseActor rock : BaseActor.getList(mainStage, "Rock"))
        {
            for (BaseActor laser : BaseActor.getList(mainStage, "Laser"))
            {
                if (laser.overlaps(rock))
                {
                    laser.remove();
                    rock.remove();
                    Explosion explosion = new Explosion(0,0, mainStage);
                    explosion.centerAt(rock);

                    double powerUpFrequency = 0.99;
                    if ( Math.random() < powerUpFrequency )
                    {
                        Powerup powerup = new Powerup(0,0, mainStage);
                        powerup.centerAt(rock);

                        powerup.addAction(
                            Actions.parallel(
                                Actions.forever(
                                    Actions.sequence(
                                        Actions.scaleTo(1.2f, 1.2f, 0.5f),
                                        Actions.scaleTo(0.8f, 0.8f, 0.5f)
                                    )    
                                ),
                                Actions.sequence(
                                    Actions.delay(5),
                                    Actions.fadeOut(1),
                                    Actions.removeActor()
                                )
                            )
                        );
                    }

                    // if rock is large, then split it into two smaller rocks
                    if (rock.getWidth() > 50)
                    {
                        Rock rock1 = new Rock(0,0, mainStage);
                        Rock rock2 = new Rock(0,0, mainStage);

                        // make rocks even smaller than default constructor values
                        float size = rock.getWidth() / 2;
                        rock1.setSize(size, size);
                        rock2.setSize(size, size);
                        // recenter small rocks at larger rock center
                        rock1.centerAt(rock);
                        rock2.centerAt(rock);

                        rock1.setBoundaryPolygon(8);
                        rock2.setBoundaryPolygon(8);

                    }
                }
            }
        }

    }
}