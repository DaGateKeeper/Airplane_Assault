import com.badlogic.gdx.math.Vector2;

public class LinearPath extends Function
{
    float constant;
    
    public LinearPath()
    {
        constant = (float)(400 * Math.random() + 200);
    }
    
    public Vector2 evaluate(float time)
    {
        float x = constant;
        float y = -100 * time + 900;
        
        return new Vector2( x, y );
    }
    
    
    
}