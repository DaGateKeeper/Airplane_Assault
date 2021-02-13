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

    public void initialize()
    {
        new Ocean(0,0, mainStage);
        new Ocean(0,800, mainStage);

        new Island(mainStage);

        enemyTimer = 0;

        player = new Player(350, 100, mainStage);

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
            double RAND=Math.random()*4 + 1;
            // spawn new enemy off-screen
            new Enemy((int)RAND, mainStage);
            // reset the timer
            enemyTimer = 0;
        }
    }
}
