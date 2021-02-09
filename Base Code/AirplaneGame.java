public class AirplaneGame extends BaseGame
{
    public void create() 
    {     
        super.create();
        this.setActiveScreen(new MenuScreen());
    }
}