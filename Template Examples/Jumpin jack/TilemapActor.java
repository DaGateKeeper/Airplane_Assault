import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Intersector;

public class TilemapActor extends BaseActor
{
    public TextureRegion[] tileset;
    public HashMap<String, Integer> table;
    public String[][] layout;
    public int tileWidth;
    public int tileHeight;

    public ArrayList<Polygon> tileBoundaries;

    public int roomNumber;

    // constructor
    public TilemapActor(String tilemapFileName, Stage theStage) throws Exception
    {
        super(0,0, theStage);

        Scanner scanner = new Scanner( new File(tilemapFileName) );

        String imageFileName = null;
        Integer rows = null;
        Integer columns = null;

        table = new HashMap<String, Integer>();
        ArrayList<String> tempLevelData = new ArrayList<String>();

        // parsing the text file
        while ( scanner.hasNextLine() )
        {
            // retrieve next line
            String line = scanner.nextLine();
            // remove black space
            String trimmed = line.trim();

            // if this line is empty (no characters),
            //    skip to next iteration of the loop.
            if ( trimmed.length() == 0 )
                continue;

            String firstLetter = trimmed.substring(0, 1);
            String data = trimmed.substring(2);
            switch (firstLetter)
            {
                case "#":
                break;

                case "I":
                imageFileName = data;
                break;

                case "R":
                rows = Integer.parseInt(data);
                break;

                case "C":
                columns = Integer.parseInt(data);
                break;

                case "T":
                // convert data into array, separated by ","
                String[] dataArray = data.split(",");
                String  key   = dataArray[0];
                Integer value = Integer.parseInt(dataArray[1]);
                // store value in table
                table.put( key, value );
                break;

                case "N":
                roomNumber = Integer.parseInt(data);
                break;

                case "L":
                tempLevelData.add( data );
                break;

                default:
                System.err.println("WARNING: unrecognized starting character " + firstLetter);
                break;
            }
        }

        // table is finished!

        // load the tileset into an array of tile images

        // load original image for tileset
        Texture texture = new Texture(Gdx.files.internal(imageFileName), true);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // calculate how big each tile will be
        tileWidth = texture.getWidth() / columns;
        tileHeight = texture.getHeight() / rows;

        // create a 2D array containing separated tile images
        TextureRegion[][] temp = TextureRegion.split(texture, tileWidth, tileHeight);

        // initialize (1D) array to store tile images
        tileset = new TextureRegion[rows * columns];

        // copy the images from the 2D array into the 1D array
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < columns; c++)
                tileset[ columns * r + c ] = temp[r][c];

        // separate tempLevelData into 2D array
        int layoutRows = tempLevelData.size();
        int layoutColumns = tempLevelData.get(0).split(",").length;

        // now that we know total number of rows and columns,
        //  we can initialize layout array and store data in it.
        layout = new String[layoutRows][layoutColumns];
        
        for (int r = 0; r < layoutRows; r++)
        {
            String rowString = tempLevelData.get(r);
            // convert row data from string to array
            String[] columnData = rowString.split(",");
            layout[r] = columnData;
        }

        // initialize rectangle boundaries for each of the tiles
        int numberRows = layout.length;
        int numberColumns = layout[0].length;
        for (int r = 0; r < numberRows; r++)
        {
            for (int c = 0; c < numberColumns; c++)
            {
                // find the integer corresponding to the String in the layout
                String layoutSymbol = layout[r][c];

                // bottom-left corner of tile
                int x = tileWidth * c;
                int y = (numberRows - 1) * tileHeight - (tileHeight * r);

                if ( table.containsKey(layoutSymbol) )
                {
                    Solid s = new Solid(x,y, theStage);
                    s.setSize(tileWidth, tileHeight);
                    s.setBoundaryRectangle();
                    s.setVisible(false);
                }
                else
                    switch (layoutSymbol)
                    {
                        case "c":
                            new Coin(x,y, theStage);
                            break;
                        case "h":
                            new Heart(x,y, theStage);
                            break;
                        case "p":
                            new Platform(x,y+16, theStage);
                            break;
                        case "s":
                            new Spike(x, y, theStage);
                            break;
                        case "0":
                            new Door(x,y, 0, theStage);
                            break;
                        case "1":
                            new Door(x,y, 1, theStage);
                            break;
                        case "2":
                            new Door(x,y, 2, theStage);
                            break;
                            
                        case "3":
                            new Door(x,y,3,theStage);
                            break;
                            
                        case "Lb":
                            new LockB (x,y,theStage);
                            break;
                        
                        case "Lr":
                            new LockR (x,y,theStage);
                            break;
                        case "Ly":
                            new LockY(x,y,theStage);
                            break;
                            
                        case "Lg":
                            new LockG(x,y,theStage);
                            break;
                            
                        case "F":
                            new Flag(x,y,theStage);
                            break;
                            
                        case "Kb":
                            new KeyB(x,y,theStage);
                            break;
                            
                        case "Kr":
                            new KeyR(x,y,theStage);
                            break;
                            
                        case "Cl":
                            new Clock(x,y,theStage);
                            break;
                        default:
                            break;
                    }
            }
        }

    }


    @Override
    public void draw(Batch batch, float parentAlpha) 
    {
        // iterate over the layout (2D array)
        int numberRows = layout.length;
        int numberColumns = layout[0].length;
        for (int r = 0; r < numberRows; r++)
        {
            for (int c = 0; c < numberColumns; c++)
            {
                // find the integer corresponding to the String in the layout
                String layoutSymbol = layout[r][c];

                // if symbol is not in the table, skip to next symbol
                if ( !table.containsKey(layoutSymbol) )
                    continue; 

                Integer tileIndex = table.get(layoutSymbol);

                TextureRegion tileImage = tileset[tileIndex];

                // draw the corresponding tile image
                int x = tileWidth * c;
                int y = (numberRows - 1) * tileHeight - (tileHeight * r);
                batch.draw(tileImage, x, y);
            }
        }
    }

}