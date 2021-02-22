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
            // Initialize all Disease presets here.
          new Players("assets/ships/player1.png", 200, 200, 50, 20, 100),
          new Players("assets/ships/player2.png", 150, 150, 60, 30, 200),
          new Players("assets/ships/player3.png", 300, 230, 70, 10, 50),  
       };
        
        BOSSSTATS_ = new Bosses[]
        {
            // Initialize all precautionary measure presets here.
           new Bosses(),
           new Bosses()
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
     * Returns a copy of the first Disease in the Database's disease array with the specified name.
     *
     * @param name The name of the Disease.
     * @return A copy of the first Disease found with the specified name or null if none is found.
     */
    public static Players getPlayersCopy(String name)
    {
        //for (Disease disease : DISEASES_)
        //{
         //   if (disease.getName() == name)
         //   {
           //     return new Disease(disease);
          //  }
        //}
        
        return null;
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
    public static Bosses getPrecautionCopy(int index) throws IndexOutOfBoundsException
    {
        return null; //new Precaution(PRECAUTIONS_[index]);
    }
    
    /**
     * Returns a copy of the first Precaution in the Database's precautionary measures array with
     * the specified name.
     *
     * @param name The name of the Precaution.
     * @return A copy of the first Precaution found with the specified name or null if none is
     *         found.
     */
    public static Bosses getBossStatsCopy(String name)
    {
        //for (Bosses precaution : PRECAUTIONS_)
        //{//
          //  if (precaution.getName() == name)
          //  {
           //     return new Precaution(precaution);
          //  }
      //  }
    
        return null;
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