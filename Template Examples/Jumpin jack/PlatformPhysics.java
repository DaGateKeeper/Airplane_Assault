public class PlatformPhysics extends Physics
{
    public float jumpStrength;
    public float gravity;
    public float terminalVelocity;
    
    public PlatformPhysics(float walkAcceleration, float maxWalkSpeed, 
    float walkDeceleration, float jumpStrength,
    float gravity, float terminalVelocity)
    {
        super( walkAcceleration, maxWalkSpeed, walkDeceleration );

        this.jumpStrength = jumpStrength;
        this.gravity = gravity;
        this.terminalVelocity = terminalVelocity;
    }

    public void jump()
    {
        velocity.y = jumpStrength;
    }
    
    @Override
    public void update(float dt)
    {
        // if not accelerating in x-direction, then decelerate
        if ( Math.abs(acceleration.x) < 0.001 )
        {
            double decelerationAmount = decelerationValue * dt;
            
            // separate x-velocity into speed and direction
            double walkSpeed, walkDirection;
            
            walkSpeed = Math.abs(velocity.x);
            
            if (velocity.x > 0)
                walkDirection = +1;
            else
                walkDirection = -1;
           
            // reduce walk speed
            walkSpeed -= decelerationAmount;
             
            // speed can not be negative
            if (walkSpeed < 0)
                walkSpeed = 0;
            
            velocity.x = (float)(walkSpeed * walkDirection);
        }
        
        // add force of gravity to acceleration
        acceleration.y -= gravity;
        
        // update velocity based on acceleration
        velocity.add( acceleration.x * dt, acceleration.y * dt );
        
        // always make sure velocity is within given bounds
        if (velocity.x < -maximumSpeed)
            velocity.x = -maximumSpeed;
        if (velocity.x >  maximumSpeed)
            velocity.x =  maximumSpeed;
                
        if (velocity.y < -terminalVelocity)
            velocity.y = -terminalVelocity;
        if (velocity.y >  terminalVelocity)
            velocity.y =  terminalVelocity;
            
        // update position based on velocity
        position.add( velocity.x * dt, velocity.y * dt );
        
        // reset accumulated acceleration (it has been "used up" by velocity)
        acceleration.set(0,0);
    }
}