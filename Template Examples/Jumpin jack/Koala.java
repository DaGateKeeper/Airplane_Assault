import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Koala extends BaseActor
{ 
    public Animator stand;
    public Animator walk;
    public Animator jump;

    public Sound jumpSFX;
    public Sound damageSFX;
    
    // Tracks how long the Koala has been invincible for after taking damage.
    public float invTimer;

    // How long the Koala can stay invincible.
    public float maxInvTime;

    public Koala(float x, float y, Stage stage)
    {
        super(x,y,stage);

        stand = new Animator("assets/koala/stand.png");

        String[] walkImages = {"assets/koala/walk-1.png", "assets/koala/walk-2.png",
                "assets/koala/walk-3.png", "assets/koala/walk-2.png"};
        walk = new Animator(walkImages, 0.2f, true);

        jump = new Animator("assets/koala/jump.png");
        jumpSFX = Gdx.audio.newSound(Gdx.files.internal("assets/audio/woosh.ogg"));
        
        damageSFX = Gdx.audio.newSound(Gdx.files.internal("assets/audio/squish.wav"));
        
        setAnimator(stand);

        // walkAcc, maxWalkSpeed, walkDec, jumpStrength, gravity, terminalVelocity
        setPhysics( new PlatformPhysics(800, 200, 800, 500, 900, 1000) );
        setScale(0.8f);
        
        maxInvTime = 2;
        invTimer = maxInvTime;
    }

    public boolean isOnSolid()
    {
        moveBy(0, -2);
        for (BaseActor actor : BaseActor.getList( getStage(), "Solid" ) )
        {
            Solid solid = (Solid)actor;
            if ( solid.isEnabled() && overlaps(solid) )
            {
                moveBy(0, 2);
                return true;
            }
        }
        moveBy(0, 2);
        return false;
    }

    public void jump()
    {
        if (isOnSolid())
        {
            ((PlatformPhysics)physics).jump();
            jumpSFX.play();
        }
    }

    public void takeDamage()
    {
        if (invTimer >= maxInvTime)
        {
            invTimer = 0;
            Storage.health -= 1;
            damageSFX.play();
        }
    }

    public void act(float dt)
    {
        // update animation and physics
        super.act(dt);
        
        // Causes the Koala to flash every 2 seconds while invincible.
        invTimer += dt;
        boolean blinkBool = false;
        if (invTimer < maxInvTime)
        {
            float blinkTimer = invTimer;
            blinkBool = true;
            while (blinkTimer >= 0.075f)
            {
                blinkTimer -= 0.075f;
                blinkBool = !blinkBool;
            }
        }
        if (blinkBool)
            getColor().a = 0.25f;
        else
            getColor().a = 1;

        // if jumping thru a platform object from below,
        //   disable the solid property
        for (BaseActor actor : BaseActor.getList(getStage(), "Platform"))
        {
            Platform platform = (Platform)actor;

            // if jumping from below, can pass thru platform
            if ( this.overlaps(platform) && this.physics.velocity.y > 0 )
                platform.setEnabled(false);

            // if overlapping and pressing down, can pass thru (falling thru)
            if ( this.overlaps(platform) && Gdx.input.isKeyPressed(Keys.DOWN))
                platform.setEnabled(false);

            // once this has completely passed thru, then it can become solid again
            if ( !this.overlaps(platform) )
                platform.setEnabled(true);
        }

        for (BaseActor spike : BaseActor.getList(getStage(), "Spike"))
        {
            if (overlaps(spike))
                takeDamage();
        }
        
        for (BaseActor actor : BaseActor.getList(getStage(), "Solid"))
        {
            Solid solid = (Solid)actor;

            // if solid is not enabled, skip collision detection
            if (!solid.isEnabled())
                continue;

            // stop koala from overlapping with solids
            Vector2 displacement = preventOverlap(solid);

            // if overlapping in a particular direction, stop all movement in that direction
            if ( displacement != null )
            {
                if (Math.abs(displacement.x) > 0)
                {
                    physics.velocity.x = 0;
                    physics.acceleration.x = 0;
                }
                if (Math.abs(displacement.y) > 0)
                {
                    physics.velocity.y = 0;
                    physics.acceleration.y = 0;
                }
            }
        }

        // set correct animation 
        if (isOnSolid())
        {
            if ( Math.abs(physics.velocity.x) > 0.001 )
                setAnimator(walk);
            else
                setAnimator(stand);
        }
        else
            setAnimator(jump);

        // reflect animation according to velocity direction
        if (physics.velocity.x < 0)
            setMirrored(true);
        else if (physics.velocity.x > 0)
            setMirrored(false);
    }

}