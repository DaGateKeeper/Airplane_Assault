
/**
 * Write a description of class players here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Players 
{
    //----- FIELDS -----
    //to be edited
    //needs
    //string:name  Double:health  String:shipAssetts Double:sheilds Double:Speed Double:RateofFire
    /** The number of the indexed ship. */
    private int index_;
    
    /** The name of the Players ship. */
    private String fileName_;
    
    /** The maximum health the player posses. */
    private double health_;
    
    /** The maximum Sheilds the player posses. */
    private double sheilds_;
    
    /** How fast the player can move. */
    private double speed_;
    
    /** How fast the player can shoot. */
    private float rateofFire_;
    
    /** the Amount of damage the player does per hit(if fires more than one bullet fraction it between the two. */
    private int PlayerDamage_;
    
    //----- CONSTRUCTORS -----
    
    /**
     * Default Constructor:<br />
     * Creates a new Players with default values (0) and the name "assets/ships/player1.png"".
     */
    public Players()
    {
        this("assets/ships/player1.png", 200, 200, 50, 20, 100);
    }
    
    /**
     * Copy Constructor: <br />
     * Creates a deep copy of the specified Players object.
     *
     * @param Players The Players to copy.
     */
    public Players(Players Players)
    {
        this(Players.fileName_, Players.health_, Players.sheilds_, Players.speed_, Players.rateofFire_,Players.PlayerDamage_);
    }
    
    /**
     * Parameterized Constructor:<br />
     * Creates a new Players with the specified parameters.
     *
     * @param fileName name of assets file
     * @param health amount of health
     * @param sheilds amount of sheilds
     * 
     * @param speed how fast it moves
     * @param RateofFire how fast it shoots
     * @param PlayerDamage how much damage each shot does
     */
    public Players(String fileName, double health,double sheilds, double speed,float rateofFire,int PlayerDamage)
    {
        fileName_= fileName;
        health_= health;
        sheilds_= sheilds;
        speed_= speed;
        rateofFire_= rateofFire;
        PlayerDamage_= PlayerDamage;
            
    }
    
    //----- METHODS -----
    // Get & Set
    public String getfileName()
    {
        return fileName_;
    }
    
    public void setfileName(String fileName)
    {
        this.fileName_ = fileName;
    }
    
    public double getHealth()
    {
        return health_;
    }
    
    public void setHealth(double health)
    {
        this.health_ = health;
    }
        
    public double getSheilds()
    {
        return sheilds_;
    }
    
    public void setSheilds(double sheilds)
    {
        this.sheilds_ = sheilds;
    }
        
    public double getSpeed()
    {
        return speed_;
    }
    
    public void setSpeed(double speed)
    {
        this.speed_ = speed;
    }
        
    public float getrateofFire()
    {
        return rateofFire_;
    }
    
    public void setrateofFire(float rateofFire)
    {
        this.rateofFire_ = rateofFire;
    }
        
    public float getPlayerDamage()
    {
        return PlayerDamage_;
    }
    
    public void setPlayerDamage(int PlayerDamage)
    {
        this.PlayerDamage_ = PlayerDamage;
    }


}