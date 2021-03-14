public class SpaceRocksGame extends BaseGame
{
    public void create() 
    {     
        super.create();
        BaseGame.setActiveScreen( new LevelScreen() );
    }
}