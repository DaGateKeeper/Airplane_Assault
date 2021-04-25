import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
public class PlayerSelect extends BaseScreen
{
    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.setAnimator( new Animator("assets/space.jpg") );
        background.setSize(800,800);

        BaseActor ship1 = new BaseActor(0,0,mainStage);
        ship1.setAnimator(new Animator("assets/ships/player1a.png"));

        BaseActor ship2 = new BaseActor(0,0,mainStage);
        ship2.setAnimator(new Animator("assets/ships/player2.png"));

        BaseActor ship3 = new BaseActor(0,0,mainStage);
        ship3.setAnimator(new Animator("assets/ships/player3.png"));

        Label title = new Label("Ship Selection!", BaseGame.labelStyle);
        title.setFontScale(0.5f);

        Label SpeedyInfo = new Label("the Fastest ship avaliable \n  has the lowest health of the three \n  can only fire one shot at a time. \n ",BaseGame.labelStyle2);
        SpeedyInfo.setFontScale(0.5f);

        Label SelectSpeedy = new Label("Press S for the Speedy Ship!",BaseGame.labelStyle2);
        SelectSpeedy.setColor(Color.RED);
        SelectSpeedy.setFontScale(0.5f);

        Label AverageInfo = new Label("Average Class Fighter of the Game \n standard amount of health and shields. \n can fire two shots at a time \n ", BaseGame.labelStyle2);
        AverageInfo.setFontScale(0.5f);

        Label SelectAverage = new Label("Press A for Average!",BaseGame.labelStyle2);
        SelectAverage.setColor(Color.RED);
        SelectAverage.setFontScale(0.5f);

        Label DefenseInfo = new Label("The Slowest Ship in the Game \n has the Highest Health \n Three shot spread fire. \n" ,BaseGame.labelStyle2);
        DefenseInfo.setFontScale(0.5f);

        Label SelectDefensive= new Label("Press D for Defensive!",BaseGame.labelStyle2);
        SelectDefensive.setColor(Color.RED);
        SelectDefensive.setFontScale(0.5f);

        Label Blank = new Label(" ", BaseGame.labelStyle2);

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(ship1,SpeedyInfo);
        uiTable.row();
        uiTable.add(Blank  ,SelectSpeedy);
        uiTable.row();
        uiTable.add(ship2,AverageInfo);
        uiTable.row();
        uiTable.add(Blank ,SelectAverage);
        uiTable.row();
        uiTable.add(ship3,DefenseInfo);
        uiTable.row();
        uiTable.add(Blank ,SelectDefensive);
        uiTable.row();

    }
    // this code under here is supposed to take the key and depending on what they choose 
    //it will go to the next screen with that ship as the main one.
    public void update(float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.S)){
            BaseGame.setActiveScreen( new LevelScreen() );      
        }
        if (Gdx.input.isKeyJustPressed(Keys.D)){
            BaseGame.setActiveScreen( new LevelScreen() );  
        }
        if (Gdx.input.isKeyJustPressed(Keys.A)){
            BaseGame.setActiveScreen( new LevelScreen() );  
        }
    } 
}