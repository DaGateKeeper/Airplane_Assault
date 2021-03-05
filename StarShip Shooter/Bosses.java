
/**
 * Write a description of class boss here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bosses{
    //----- FIELDS -----
    //to be edited
    //needs
    // String:shipAssetts Double:health Double:sheilds Double:Speed Double:RateofFire

    /** The name of the Bosses ship. */
    private String fileName_;
    
    /** The maximum health the Bosses posses. */
    private double health_;

    /** the Amount of damage the boss does per hit(if fires more than one bullet fraction it between the two. */
    private int BossesDamage_;
    
    //----- CONSTRUCTORS -----
    
    /**
     * Default Constructor:<br />
     * Creates a new Bosses with default values (0) and the name "assets/ships/Bosses1.png"".
     */
    public Bosses()
    {
        this("assets/ships/Bosses1.png",1000, 10);
    }
    
    /**
     * Copy Constructor: <br />
     * Creates a deep copy of the specified Bosses object.
     *
     * @param Bosses The Bosses to copy.
     */
    public Bosses(Bosses Bosses)
    {
        this(Bosses.fileName_, Bosses.health_,Bosses.BossesDamage_);
    }
    
    /**
     * Parameterized Constructor:<br />
     * Creates a new Bosses with the specified parameters.
     *
     * @param fileName name of assets file
     * @param health amount of health
     * @param BossesDamage how much damage each shot does
     */
    public Bosses(String fileName, double health,int BossesDamage)
    {
        fileName_= fileName;
        health_= health;
        BossesDamage_= BossesDamage;
            
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
    
    public float getBossesDamage()
    {
        return BossesDamage_;
    }
    
    public void setBossesDamage(int BossesDamage)
    {
        this.BossesDamage_ = BossesDamage;
    }


}