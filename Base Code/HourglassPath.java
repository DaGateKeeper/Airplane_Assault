import com.badlogic.gdx.math.Vector2;

public class HourglassPath extends Function
{
    public Vector2 evaluate(float time)
    {
        float x,y;
        if (time < 0)
        {
            x = 700;
            y = 500/2 * time + 400;
        }
        else
        {
            x = (float)(300 * Math.cos(3 * time / 4) + 400);
            y = (float)(300 * Math.sin(2 * time / 4) + 400);
        }
        return new Vector2( x, y );
    }

    
}