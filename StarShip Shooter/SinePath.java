import com.badlogic.gdx.math.Vector2;

public class SinePath extends Function
{
    public Vector2 evaluate(float time)
    {
        float x = time * 100 - 100;
        float y = 200 * (float)Math.sin(time) + 450;
        
        return new Vector2( x, y );
    }
    
    
    
}
