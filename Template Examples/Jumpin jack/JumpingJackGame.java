import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
public class JumpingJackGame extends BaseGame
{
    public static ArrayList<LevelScreen> roomList;
        public Music LineBGM;
    public void create() 
    {     
        super.create();
        LineBGM =Gdx.audio.newMusic(Gdx.files.internal("assets/audio/LineBGM.mp3"));
        LineBGM.play();
        LineBGM.setVolume(0.6f);
        roomList = new ArrayList<LevelScreen>();
        roomList.add( new LevelScreen("assets/room0.map") );
        roomList.add( new LevelScreen("assets/room1.map") );
        roomList.add( new LevelScreen("assets/room2.map") );
        roomList.add(new LevelScreen("assets/room3.map"));
        setActiveScreen(new StartScreen() );
    }
}