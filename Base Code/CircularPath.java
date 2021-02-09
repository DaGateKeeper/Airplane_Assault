import com.badlogic.gdx.math.Vector2;

public class CircularPath extends Function
{
    public Vector2 evaluate(float time)
    {
        float x, y;
        if (time < Math.PI) // move straight down
        {
            x = 100;
            y = -650/(float)Math.PI * time + 1050;
        }
        else // circular motion
        {
            x = (float)(300 * Math.cos(time) + 400);
            y = (float)(300 * Math.sin(time) + 400);
        }
        return new Vector2( x, y );
    }

    
}