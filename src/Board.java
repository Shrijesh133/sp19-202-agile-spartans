import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.ArrayList;
public class Board extends Actor 
{
    // Board manages pretty much everything that makes up the game (would you just reduce it to gameplay) | if (getWorld ()! = null) exists in many places, because otherwise older versions of Greenfoot may fail
    private Field[][] field; // Field array is declared
    private LastState[][] lastState; //For Undo

    private Number number; //Actor references are declared / initialized
    private Score scoreActor;
    private Highscore highScoreActor;
    private ScoreShadow scoreShadowActor;
    private HighscoreShadow highScoreShadowActor;
    private GameOverText gameOverText = new GameOverText();
    private GameOverOverlay gameOverOverlay;
    private PlayButton playButton = new PlayButton(true);
    
   
    private int width;
    private int height;

    private int highscore=0; //Simple variables are declared / initialized
    private int score=0;
    private boolean debugMode;
    private boolean fieldInitialized=false;
    private boolean up=false;
    private boolean down=false;
    private boolean left=false;
    private boolean right=false;
    private boolean gameOver=false;
    private boolean isOver;

    private MoveSubject move ;

    private IGameState gameNotStartedState;
    private IGameState gamePausedState;
    private IGameState gameOverState;
    private IGameState gameRunningState;
    private IGameState currentState;
    
    


    public Board() //Constructor makes the "game preparations"
    {
        
        field = new Field[4][4];
        lastState = new LastState[4][4];
        
        debugMode=false;

        isOver=false;
       
        //isOver=false;
        highscore=HighscoreObserver.getHighScore();
        
   
        fillField();
        placeRandomField();
        placeRandomField();
        if(getWorld()!=null)
        {
            updateFieldVisuals();
            printScore(false);
        }
        
        gameRunningState = new GameRunningState(this);
        gameNotStartedState = new GameNotStartedState(this);
        gamePausedState = new GamePausedState(this);
        gameOverState = new GameOverState(this);
        currentState = gameNotStartedState;
    }

    public void act()
    {

        boolean anyfieldMoved = false;
         
     

        if(getWorld()!=null&&!fieldInitialized) // try to load the visual interface until getWorld () does not make any more problems (otherwise it does because of Greenfoot)

        {
            updateFieldVisuals();
            fieldInitialized=true;
        }
        if(checkForMovableFields()&&!(currentState == gameOverState || currentState == gamePausedState)) // Move and add only while the game is running
        {
            
            setAllMovedFalse();
            if (Greenfoot.isKeyDown("up")&&!up)
            {

                currentState.play();
                storeState();
                move = new UpMove(field);
                anyfieldMoved  = move.move();
                up=true;
            }
            
            if (Greenfoot.isKeyDown("down")&&!down)
            {

                currentState.play();
                storeState();                
                move = new DownMove(field);
                anyfieldMoved  = move.move();
                down=true;
            }
            if (Greenfoot.isKeyDown("left")&&!left)
            {

                currentState.play();
                storeState();             

               move = new LeftMove(field);
               anyfieldMoved  = move.move();

                left=true;
            }
            if (Greenfoot.isKeyDown("right")&&!right)
            {

                currentState.play();
                storeState();               

                move = new RightMove(field);
               anyfieldMoved  = move.move();

                right=true;
            }
            
         if (anyfieldMoved)
        {
            updateFieldVisuals();
            placeRandomField(); //Setzt ein neues zufälliges Feld, falls Felder bewegt wurden
        }
            
            printScore(false);
        }
        else // Cancel the Game / Game Over
        {
            currentState = gameOverState;
            if (currentState == gameOverState)
            {
                showGameOverScreen();
              
               // isOver=true;
            }
            printScore(true);
        }
        //Vorkehrungen, damit pro Tastendruck nur ein Input genommen wird und nicht jeden Tick
        if (!Greenfoot.isKeyDown("up"))
        {
            up=false;
        }
        if (!Greenfoot.isKeyDown("down"))
        {
            down=false;
        }
        if (!Greenfoot.isKeyDown("left"))
        {
            left=false;
        }
        if (!Greenfoot.isKeyDown("right"))
        {
            right=false;
        }
    }
    
    public void setState(String gameState) {
        if(gameState == "Playing" || gameState == "Resumed"){
        currentState = gameRunningState;
    }  else if(gameState == "Restarted") {
        currentState = gameNotStartedState;
    } else if(gameState == "Paused") {
        currentState = gamePausedState;
    }
    }

    public void showGameOverScreen() //Zeigt das Game Over Overlay an
    {
       // gameOver=true;
        if(getWorld()!=null)
        {
            for (int i=0; i<127;i=i+2)
            {
                gameOverOverlay = new GameOverOverlay(i);
                getWorld().addObject(gameOverOverlay,0,0);
                Greenfoot.delay(1);
                getWorld().removeObject(gameOverOverlay);
            }
            getWorld().addObject(gameOverOverlay,0,0);
            Greenfoot.delay(10);
            printScore(true);
            getWorld().addObject(gameOverText,240,60);
            getWorld().addObject(playButton, 240, 420);
        }
    }

    public void updateFieldVisuals() //Updated die visuelle Darstellung des Spiels
    {
        getWorld().removeObjects(getWorld().getObjects(Number.class));
        for (int x=0; x< field.length; x++)
        {
            for (int y=0; y< field.length; y++)
            {
                int currentValue = field[x][y].getValue();
                if (currentValue>0)
                {
                    number = new Number(currentValue,120);
                    getWorld().addObject(number,x*120+60,y*120+60);
                }
            }
        }
    }

    public void playPlaceAnimation(int pX, int pY, int pValue) //"Ploppt" ein neues zufälliges Feld auf 
    {
        if(getWorld()!=null)
        {
            for (int i=1; i<120;i=i+16)
            {
                number = new Number(pValue,i);
                getWorld().addObject(number,pX*120+60,pY*120+60);
                Greenfoot.delay(1);
                getWorld().removeObject(number);
            }
            updateFieldVisuals();
        }
    }

    /*public void addScore(int pAdd) //Addiert Score um pAdd
    {
       score=score+pAdd;
    } 

    public int getScore() //Getter wegen private
    {
        return score;
    } */

    public void printScore(boolean gameOver) //Gibt den Score/Highscore im Spiel aus | hat zwei Darstellungsmöglichkeiten: Game Over und im Spiel
    {
        if(getWorld()!=null&&!gameOver) //Spiel läuft
        {
            getWorld().removeObject(scoreShadowActor); //Zeigt Schatten (stellt die Strings in schwarz daruntergelegt und versetzt dar, damit die Scores besser zum Design des Spiels passen)
            getWorld().removeObject(highScoreShadowActor);
            scoreShadowActor = new ScoreShadow(ScoreObserver.getScore(),false);
            getWorld().addObject(scoreShadowActor,242,522);
            highScoreShadowActor = new HighscoreShadow(HighscoreObserver.getHighScore(),false);
            getWorld().addObject(highScoreShadowActor,242,562);

            getWorld().removeObject(scoreActor);
            getWorld().removeObject(highScoreActor);
            scoreActor = new Score(ScoreObserver.getScore(),false);
            getWorld().addObject(scoreActor,240,520);
            highScoreActor = new Highscore(HighscoreObserver.getHighScore(),false);
            getWorld().addObject(highScoreActor,240,560);
        }
        else if(getWorld()!=null&&gameOver) //Game Over
        {
            getWorld().removeObject(scoreShadowActor); //Zeigt Schatten (stellt die Strings in schwarz daruntergelegt und versetzt dar, damit die Scores besser zum Design des Spiels passen)
            getWorld().removeObject(highScoreShadowActor);
            scoreShadowActor = new ScoreShadow(ScoreObserver.getScore(),true);
            getWorld().addObject(scoreShadowActor,242,182);
            highScoreShadowActor = new HighscoreShadow(HighscoreObserver.getHighScore(),true);
            getWorld().addObject(highScoreShadowActor,242,302);

            getWorld().removeObject(scoreActor);
            getWorld().removeObject(highScoreActor);
            scoreActor = new Score(ScoreObserver.getScore(),true);
            getWorld().addObject(scoreActor,240,180);
            highScoreActor = new Highscore(HighscoreObserver.getHighScore(),true);
            getWorld().addObject(highScoreActor,240,300);
        }
    }


    public void switchDebugMode() //Nur auf Konsole | Fürs Testen, de- /aktiviert DebugMode, welcher jeden Schritt auf der Konsole anzeigt
    {
        if (debugMode)
        {
            debugMode=false;
        }
        else
        {
            debugMode=true;
        }
    }

    public void setInitialBoardForTesting(int pNumberOfFields) //Erstellt ein Board mit "pNumberOfFields" Feldern
    {
        for (int i=0; i<pNumberOfFields; i++)
        {
            placeRandomField();
        }
    }

    public void fillField() //Füllt das Array mit Field-Objekten
    {
        int x;
        int y;
        for (x=0; x< field.length; x++)
        {
            for (y=0; y< field.length; y++)
            {
                field[x][y]= new Field();
                lastState[x][y] = new LastState();
            }
        }
    }

    public void printField(boolean dontClearConsole) //Zeigt das "Spielfeld" auf der Konsole (DebugMode zeigt jeden einzelnen Schritt)
    {
        if (dontClearConsole)
        {
            System.out.println("_______");
            System.out.println();
        }
        else
        {
            System.out.print('\f');
        }
        for (int y=0; y< field.length; y++)
        {
            for (int x=0; x< field.length; x++)
            {
                if (field[x][y].getValue()==0)
                {
                    System.out.print("- ");
                }
                else
                {
                    System.out.print(field[x][y].getValue()+" ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Score: "+ScoreObserver.getScore());
        if(!checkForMovableFields())
        {
            System.out.println("Game Over!");
        }
    }

    public void placeRandomField() //Plaziert an einer zufälligen leeren Stelle ein Feld, falls vorhanden
    {
        boolean numberPlaced=false;
        boolean emptyFields=false;
        int xRandom = Greenfoot.getRandomNumber(4);
        int yRandom = Greenfoot.getRandomNumber(4);
        int x=0;
        int y=0;
        int placedValue=2;
        for (x=0; x< field.length; x++) //Prüft, ob leere Felder vorhanden sind, damit keine Endlosschleife entsteht
        {
            for (y=0; y< field.length; y++)
            {
                if (field[x][y].getValue()==0)
                {
                    emptyFields=true;
                }
            }
        }
        if (emptyFields) //Plaziert an einer zufälligen leeren Stelle ein Feld, falls vorhanden
        {
            while (numberPlaced==false)
            {
                if (field[xRandom][yRandom].getValue()==0)
                {
                    field[xRandom][yRandom].setRandomInitialValue();
                    placedValue=field[xRandom][yRandom].getValue();
                    numberPlaced=true;
                }
                else
                {
                    xRandom = Greenfoot.getRandomNumber(4);
                    yRandom = Greenfoot.getRandomNumber(4);
                }
            }
        }
        if (numberPlaced) //Spielt die (noch nicht implementierte) separate Animation ab
        {
            playPlaceAnimation(xRandom,yRandom,placedValue);
        }
    }

    public void setAllMovedFalse() //Setzt moved aller Felder auf false | vorbereitung für "neuen move"
    {
        for (int x=0; x< field.length; x++)
        {
            for (int y=0; y< field.length; y++)
            {
                field[x][y].setMoved(false);
            }
        }
    }

    

    public boolean checkForMovableFields() //Prüft, ob noch bewegbare Felder existieren
    {
        int y;
        int x;
        boolean anyFieldsMovable=false;
        //UP
        for (y=1; y< field.length; y++)
        {
            for (x=0; x< field.length; x++)
            {
                if (field[x][y].getValue()!=0&&field[x][y-1].getMoved()==false&&(field[x][y-1].getValue()==field[x][y].getValue()||field[x][y-1].getValue()==0))
                {
                    anyFieldsMovable=true;
                }
                else if (field[x][y].getValue()==0)
                {
                    anyFieldsMovable=true;
                }
            }
        }
        //DOWN
        for (y=(field.length-2); y>=0; y--)
        {
            for (x=0; x< field.length; x++)
            {
                if(field[x][y].getValue()!=0&&field[x][y+1].getMoved()==false&&(field[x][y+1].getValue()==field[x][y].getValue()||field[x][y+1].getValue()==0))
                {
                    anyFieldsMovable=true;
                }
                else if (field[x][y].getValue()==0)
                {
                    anyFieldsMovable=true;
                }
            }
        }
        //LEFT
        for (x=1; x<field.length; x++)
        {
            for (y=0; y< field.length; y++)
            {
                for (int xIn=1; xIn<field.length; xIn++)
                {
                    if(field[x][y].getValue()!=0&&field[x-1][y].getMoved()==false&&(field[x-1][y].getValue()==field[x][y].getValue()||field[x-1][y].getValue()==0))
                    {
                        anyFieldsMovable=true;
                    }
                    else if (field[x][y].getValue()==0)
                    {
                        anyFieldsMovable=true;
                    }
                }
            }
        }
        //RIGHT
        for (x=(field.length-2); x>=0; x--)
        {
            for (y=0; y< field.length; y++)
            {
                for (int xIn=(field.length-2); xIn>=0; xIn--)
                {
                    if(field[x][y].getValue()!=0&&field[x+1][y].getMoved()==false&&(field[x+1][y].getValue()==field[x][y].getValue()||field[x+1][y].getValue()==0))
                    {
                        anyFieldsMovable=true;
                    }
                    else if (field[x][y].getValue()==0)
                    {
                        anyFieldsMovable=true;
                    }
                }
            }
        }
        return anyFieldsMovable;
    }

    public void placeSpecificField(int pX, int pY,int  pValue) //Plaziert ein Feld mit einem bestimmten Wert an einer bestimmten Stelle (fürs Testen)
    {
        field[pX][pY].setValue(pValue);
        updateFieldVisuals();
    }
   
    public void storeState() {
        for (int i = 0; i < field.length; i++) {
                System.out.println("i:" + i);
                for (int j = 0; j < field.length; j++) {
                    System.out.println(field[i][j].getValue());
                }
            }
            for (int i = 0; i < field.length; i++) {
                System.out.println("i:" + i);
                for (int j = 0; j < field.length; j++) {
                    System.out.println("j:" +  field[i][j].getValue());
                    if(field[i][j].getValue() == 0){
                        lastState[i][j].setValue(0);
                    } else {
                        lastState[i][j].setValue(field[i][j].getValue());
                    } 
                }
            }
     }
        
    public void undo() {
        //Get Memento here and store it in Field Array
        //Set Memento to false, so player can undo only once
        for (int i = 0; i < field.length; i++) {
            System.out.println("i:" + i);
            for (int j = 0; j < field.length; j++) {
		field[i][j].setValue(lastState[i][j].getValue());
            }
	}
	updateFieldVisuals();
    }
}
