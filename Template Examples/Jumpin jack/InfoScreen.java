import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class InfoScreen extends BaseScreen
{
    public InfoScreen()
    {
        super("");
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/background.png") );
        background.setSize(800,640);
        
        BaseActor koala = new BaseActor(0,0, mainStage);
        koala.setAnimator( new Animator("assets/koala/stand.png") );
        koala.setSize(36,52);
        
        BaseActor spike = new BaseActor(0,0, mainStage);
        spike.setAnimator( new Animator("assets/spikesNew.png") );
        spike.setSize(36,52);
        
        BaseActor coin = new BaseActor(0,0, mainStage);
        coin.setAnimator( new Animator("assets/coin-single.png") );
        coin.setSize(36,52);
        
        BaseActor heart = new BaseActor(0,0, mainStage);
        heart.setAnimator( new Animator("assets/heart.png") );
        heart.setSize(36,52);
        
        BaseActor clock = new BaseActor(0,0, mainStage);
        clock.setAnimator( new Animator("assets/item-clock.png") );
        clock.setSize(36,52);
        
        BaseActor key = new BaseActor(0,0, mainStage);
        key.setAnimator( new Animator("assets/key/key-green-icon.png") );
        key.setSize(36,52);
        
        BaseActor door = new BaseActor(0,0, mainStage);
        door.setAnimator( new Animator("assets/door.png") );
        door.setSize(36,52);
        
        BaseActor sign = new BaseActor(0,0, mainStage);
        sign.setAnimator( new Animator("assets/sign.png") );
        sign.setSize(36,52);
        
        Label title = new Label(" Jumping Jack", BaseGame.labelStyle);
        title.setFontScale(1.5f);
        title.setColor( Color.GREEN );
        
         
        Label controls = new Label("Use arrow keys to move Koala, space to jump.", BaseGame.labelStyle);
        controls.setFontScale(0.5f);
        controls.setColor( Color.BLUE );
        
         
        Label danger = new Label("Avoid Spikes, you will lose health.", BaseGame.labelStyle);
        danger.setFontScale(0.5f);
        danger.setColor( Color.BLUE );
        
         
        Label coins = new Label("Collect Coins around the Levels", BaseGame.labelStyle);
        coins.setFontScale(0.5f);
        coins.setColor( Color.BLUE );
        
         
        Label hearts = new Label("Collect Hearts to regain Health", BaseGame.labelStyle);
        hearts.setFontScale(0.5f);
        hearts.setColor( Color.BLUE );
        
         
        Label time = new Label("Collect Clocks to increase Timer", BaseGame.labelStyle);
        time.setFontScale(0.5f);
        time.setColor( Color.BLUE );
        
         
        Label keys = new Label("Collect Keys to Open gate\n 30 Coins: Yellow Key\n 50 Coins: Green Key", BaseGame.labelStyle);
        keys.setFontScale(0.5f);
        keys.setColor( Color.BLUE );
        
        Label doors = new Label("Press Up close to doors to open them", BaseGame.labelStyle);
        doors.setFontScale(0.5f);
        doors.setColor( Color.BLUE );
        
        BaseActor ret = new BaseActor(0,0, mainStage);
        ret.setAnimator( new Animator("assets/button_press-s-to-return.png"));
        ret.setSize(452, 88);
        
        
        
        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(koala);
        uiTable.add(controls);
        uiTable.row();
        uiTable.add(spike);
        uiTable.add(danger);
        uiTable.row();
        uiTable.add(coin);
        uiTable.add(coins);
        uiTable.row();
        uiTable.add(heart);
        uiTable.add(hearts);
        uiTable.row();
        uiTable.add(clock);
        uiTable.add(time);
        uiTable.row();
        uiTable.add(key);
        uiTable.add(keys);
        uiTable.row();
        uiTable.add(door);
        uiTable.add(doors);
        uiTable.row();
        uiTable.add(sign);
        uiTable.add(ret);
        
    }
    
    @Override
    public void initialize(String mapFileName){}

    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S))
            BaseGame.setActiveScreen( new MenuScreen() ); 
    
    }
}