import com.badlogic.gdx.math.Vector2;

public abstract class Function
{
    public abstract Vector2 evaluate(float time);
    
    // calculate the direction in which this function is moving
    public float getDirectionAngle(float time)
    {
        Vector2 currentPosition = evaluate(time);
        Vector2 futurePosition  = evaluate(time + 0.01f);
        
        Vector2 displacement = futurePosition.sub( currentPosition );
        
        return displacement.angle();
    }
    
}