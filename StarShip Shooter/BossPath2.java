import com.badlogic.gdx.math.Vector2;

public class BossPath2 extends Function
{
    public Vector2 evaluate(float time)
    {
        float x, y;
        if (time < Math.PI) // move straight down
        {
            x = 600;
            y = -450/(float)Math.PI * (time) + 1050;
        }
        else // circular motion
        {   
            x = (float)(350 * Math.cos(time/3) + 400);
            y = (float)(Math.sin(time/3) + 600);
        }
        return new Vector2( x, y );
    }

    
}
