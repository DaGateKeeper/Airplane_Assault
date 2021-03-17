import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class TemplateScreen extends BaseScreen
{
    public TemplateScreen(String mapFileName)
    {
        super(mapFileName);
        
    }
            Label Coin,TimeLeft;
               int coinamount=0;
     float TimeLeftint=20;
      float Timespent= 0;
    public void initialize(String mapFileName)
    {
        Coin= new Label("Coin amount:", BaseGame.labelStyle);
          Coin.setFontScale(0.5f);
        TimeLeft=new Label("Time Remaining:"+Timespent ,BaseGame.labelStyle);
          TimeLeft.setFontScale(0.5f);
        uiTable.add().expandX();
        uiTable.add(Coin); uiTable.add(TimeLeft);
        uiTable.row();
        uiTable.add();
        uiTable.add().expandY();
    }

    public void update(float deltaTime)
    {
 Timespent += deltaTime;
         TimeLeftint-=deltaTime;   
        if(Timespent>=1)
        {
            
            TimeLeft.setText("Time Remaining: " +(int) TimeLeftint);
            Timespent=0;
        }
        
    }
}