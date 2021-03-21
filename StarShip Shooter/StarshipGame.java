
/**
 * Write a description of class StarshipGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StarshipGame extends BaseGame
{
    public void create()
    {
        super.create();
        //this.setActiveScreen(new LevelScreen());
        //this.setActiveScreen(new MenuScreen());
        //this.setActiveScreen(new CreditsScreen());
       //this.setActiveScreen(new HowToScreen());
       this.setActiveScreen(new ShipInfoScreen());
    }
}
