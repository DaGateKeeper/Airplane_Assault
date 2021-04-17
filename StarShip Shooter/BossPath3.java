import com.badlogic.gdx.math.Vector2;

public class BossPath3 extends Function
{
    public Vector2 evaluate(float time)
    {
        float x,y;
        if (time < 0)
        {
            x = 500;
            y = -450/(float)Math.PI * (time) + 1050;
        }
        else
        {
            y = (float)(300 * Math.cos(3 * time / 4) + 400);
            x = (float)(300 * Math.sin(2 * time / 4) + 400);
        }
        return new Vector2( x, y );
    }
}
