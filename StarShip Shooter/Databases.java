public final class Databases
{
    //----- FIELDS -----
    /** Contains all PLAYER SHIP STATS preset information. */
    private static final Players[] PLAYERSHIPS_;
    
    /** Contains all BOSS preset information. */
    private static final Bosses[] BOSSSTATS_;

    //----- CONSTRUCTORS -----
    // Initializes static fields.
    static
    {
        PLAYERSHIPS_ = new Players[]
       {
            // Initialize all player presets here.
          new Players("assets/ships/player1a.png", 200, 200, 550, 20, 100),
          new Players("assets/ships/player2.png", 150, 150, 450, 30, 100),
          new Players("assets/ships/player3.png", 300, 230, 350, 10, 50),  
       };
        
        BOSSSTATS_ = new Bosses[]
        {
            // Initialize all precautionary measure presets here.
           new Bosses("assets/ships/Boss.png", 500, 10),
           new Bosses("assets/ships/BossLasers.png", 1000, 10),
          new Bosses("assets/ships/Boss2.png", 1000, 10),
          new Bosses("assets/ships/Boss3.png", 1000, 10)
        };
    }
    
    /**
     * Sole Private Constructor:<br />
     * Prevents the static class from being instantiated.
     */
    private Databases() { }
    
    //----- METHODS -----
    // Get
    /**
     * Returns a copy of the Disease found at the specified index of the Database's disease array.
     *
     * @param index The index of the playerships.
     * @return A copy of the ships found at the specified index.
     * @throws IndexOutOfBoundsException Thrown if index is less than 0 or greater than the length
     *                                   of the ship array minus 1.
     */
    public static Players getPlayerCopy(int index) throws IndexOutOfBoundsException
    {
        return new Players(PLAYERSHIPS_[index]);
    }
    
    
    /**
     * Returns the number of SHIPS objects stored in the Database.
     *
     * @return The number of SHIPS objects stored.
     */
    public static int getPlayersLength()
    {
        return PLAYERSHIPS_.length;
    }
    
    /**
     * Returns a copy of the Precaution found at the specified index of the Database's
     * precautionary measure array.
     *
     * @param index The index of the Precaution.
     * @return A copy of the Precaution found at the specified index.
     * @throws IndexOutOfBoundsException Thrown if index is less than 0 or greater than the length
     *                                   of the precautionary measure array minus 1.
     */
    public static Bosses getBossStats(int index) throws IndexOutOfBoundsException
    {
        return new Bosses(BOSSSTATS_[index]);
    }
    
    /**
     * Returns the number of Precaution objects stored in the Database.
     *
     * @return The number of Precaution objects stored.
     */
    public static int getBossStatsLength()
    {
        return BOSSSTATS_.length;
    }
}