
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
        this.setActiveScreen(new LevelScreen());
    }
}
