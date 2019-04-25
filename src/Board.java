import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
public class Board extends Actor
{
    //Board verwaltet so ziemlich alles, was das Spiel ausmacht (würde man es nur aufs Gameplay reduzieren) | if(getWorld()!=null) ist an vielen Stellen vorhanden, da es sonst in ältereen Greenfoot-Versionen zu Fehlern kommen kann

    private Field[][] field; //Field-Array wird deklariert

    private Number number; //Actor-Referenzen werden deklariert/initialisiert
    private Score scoreActor;
    private Highscore highScoreActor;
    private ScoreShadow scoreShadowActor;
    private HighscoreShadow highScoreShadowActor;
    private GameOverText gameOverText = new GameOverText();
    private GameOverOverlay gameOverOverlay;
    private PlayButton playButton = new PlayButton(true);

    private int highscore=0; //Einfache Variabeln werden deklariert/initialisiert
    private int score=0;
    private boolean debugMode;
    private boolean fieldInitialized=false;
    private boolean up=false;
    private boolean down=false;
    private boolean left=false;
    private boolean right=false;
    private boolean gameOver=false;
    private boolean isOver;
    private Move move ;

    public Board() //Konstruktor macht die "Spielvorbereitungen"
    {
        field = new Field[4][4];
        debugMode=false;
        isOver=false;
       
        highscore=loadHighscore();
        fillField();
        placeRandomField();
        placeRandomField();
        if(getWorld()!=null)
        {
            updateFieldVisuals();
            printScore(false);
        }
    }

    public void act()
    {
        boolean anyfieldMoved = false;
        if(getWorld()!=null&&!fieldInitialized) //Versucht das visuelle interface zu laden bis getWorld() keine Probleme mehr macht (macht es sonst wegen Greenfoot)
        {
            updateFieldVisuals();
            fieldInitialized=true;
        }
        if(checkForMovableFields()&&!gameOver) //Bewegt und addiert nur, solange das Spiel läuft
        {
            if (Greenfoot.isKeyDown("up")&&!up)
            {
               move = new UpMove(field);
               anyfieldMoved  = move.move();
                up=true;
            }
            if (Greenfoot.isKeyDown("down")&&!down)
            {
                move = new DownMove(field);
               anyfieldMoved  = move.move();
                down=true;
            }
            if (Greenfoot.isKeyDown("left")&&!left)
            {
               move = new LeftMove(field);
               anyfieldMoved  = move.move();
                left=true;
            }
            if (Greenfoot.isKeyDown("right")&&!right)
            {
                move = new RightMove(field);
               anyfieldMoved  = move.move();
                right=true;
            }
            
         if (anyfieldMoved)
        {
            updateFieldVisuals();
            placeRandomField(); //Setzt ein neues zufälliges Feld, falls Felder bewegt wurden
        }
            setHighscore();
            printScore(false);
        }
        else //Abbruch des Spiels/Game Over
        {
            gameOver=true;
            if (!isOver)
            {
                showGameOverScreen();
                setHighscore();
                saveHighscore(highscore);
                isOver=true;
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

    public void showGameOverScreen() //Zeigt das Game Over Overlay an
    {
        gameOver=true;
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

    public void addScore(int pAdd) //Addiert Score um pAdd
    {
        score=score+pAdd;
    }

    public int getScore() //Getter wegen private
    {
        return score;
    }

    public void printScore(boolean gameOver) //Gibt den Score/Highscore im Spiel aus | hat zwei Darstellungsmöglichkeiten: Game Over und im Spiel
    {
        if(getWorld()!=null&&!gameOver) //Spiel läuft
        {
            getWorld().removeObject(scoreShadowActor); //Zeigt Schatten (stellt die Strings in schwarz daruntergelegt und versetzt dar, damit die Scores besser zum Design des Spiels passen)
            getWorld().removeObject(highScoreShadowActor);
            scoreShadowActor = new ScoreShadow(getScore(),false);
            getWorld().addObject(scoreShadowActor,242,522);
            highScoreShadowActor = new HighscoreShadow(highscore,false);
            getWorld().addObject(highScoreShadowActor,242,562);

            getWorld().removeObject(scoreActor);
            getWorld().removeObject(highScoreActor);
            scoreActor = new Score(getScore(),false);
            getWorld().addObject(scoreActor,240,520);
            highScoreActor = new Highscore(highscore,false);
            getWorld().addObject(highScoreActor,240,560);
        }
        else if(getWorld()!=null&&gameOver) //Game Over
        {
            getWorld().removeObject(scoreShadowActor); //Zeigt Schatten (stellt die Strings in schwarz daruntergelegt und versetzt dar, damit die Scores besser zum Design des Spiels passen)
            getWorld().removeObject(highScoreShadowActor);
            scoreShadowActor = new ScoreShadow(getScore(),true);
            getWorld().addObject(scoreShadowActor,242,182);
            highScoreShadowActor = new HighscoreShadow(highscore,true);
            getWorld().addObject(highScoreShadowActor,242,302);

            getWorld().removeObject(scoreActor);
            getWorld().removeObject(highScoreActor);
            scoreActor = new Score(getScore(),true);
            getWorld().addObject(scoreActor,240,180);
            highScoreActor = new Highscore(highscore,true);
            getWorld().addObject(highScoreActor,240,300);
        }
    }

    public void setHighscore() //Setzt highscore auf score, falls er gebrochen wird
    {
        if (score>highscore)
        {
            highscore=score;
        }
    }

    public void saveHighscore(int pScore) //Speichert highscore in highscore.txt
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("highscore.txt"));
            bw.write(pScore+"");
            bw.close();
        }
        catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
    }

    public int loadHighscore() //Lädt highscore aus highscore.txt
    {
        int readHighscore = 0;
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("highscore.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            String stringHighscore = r.readLine();
            r.close();
            if(stringHighscore!="")
            {
                readHighscore = Integer.parseInt(stringHighscore);
            }
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
        return readHighscore;
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
        System.out.println("Score: "+getScore());
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

    public void move(int pDirection) //Bewegt und verschmilzt die Felder je nach Richtung
    {
        int x;
        int y;
        boolean anyFieldsMoved=false;
        setAllMovedFalse();
        switch (pDirection)
        {
            case 1: //Oben
            {
                for (y=1; y< field.length; y++) //Verschachtelte for-Schleife, um die jeweiligen Felder des Arrays zu bearbeiten
                {
                    for (x=0; x< field.length; x++)
                    {
                        for (int i=1; i< field.length; i++) //Noch eine for-Schleife, für den Fall dass ein Feld um mehrere Einheiten bewegt werden kann
                        {
                            if (field[x][i].getValue()!=0&&field[x][i-1].getMoved()==false&&field[x][i].getMoved()==false&&(field[x][i-1].getValue()==field[x][i].getValue()||field[x][i-1].getValue()==0))
                            {
                                if(field[x][i-1].getValue()!=0)
                                {
                                    addScore(field[x][i].getValue()*2); //Fügt Punktzahl zum Score hinzu
                                    //field[x][i].setMoved(true);
                                    field[x][i-1].setMoved(true); //moved wird auf true gesetzt, damit keine Felder doppelt addiert werden
                                }
                                field[x][i-1].setValue(field[x][i].getValue()+field[x][i-1].getValue()); //Addiert Werte
                                field[x][i].setValue(0); //Setzt vorherigen Wert auf 0
                                anyFieldsMoved=true; //Es wurden Felder bewegt

                            }
                        }
                    }
                }
                break;
            }
            case 2: //Unten
            {
                for (y=(field.length-2); y>=0; y--) //Verschachtelte for-Schleife, um die jeweiligen Felder des Arrays zu bearbeiten
                {
                    for (x=0; x< field.length; x++)
                    {
                        for (int i=(field.length-2); i>=0; i--) //Noch eine for-Schleife, für den Fall dass ein Feld um mehrere Einheiten bewegt werden kann
                        {
                            if(field[x][i].getValue()!=0&&field[x][i+1].getMoved()==false&&field[x][i].getMoved()==false&&(field[x][i+1].getValue()==field[x][i].getValue()||field[x][i+1].getValue()==0))
                            {
                                if(field[x][i+1].getValue()!=0)
                                {
                                    addScore(field[x][i].getValue()*2); //Fügt Punktzahl zum Score hinzu
                                    //field[x][i].setMoved(true);
                                    field[x][i+1].setMoved(true); //moved wird auf true gesetzt, damit keine Felder doppelt addiert werden
                                }
                                field[x][i+1].setValue(field[x][i].getValue()+field[x][i+1].getValue()); //Addiert Werte
                                field[x][i].setValue(0); //Setzt vorherigen Wert auf 0
                                anyFieldsMoved=true; //Es wurden Felder bewegt

                            }
                        }
                    }
                }
                break;
            }
            case 3: //Links
            {
                for (x=1; x<field.length; x++) //Verschachtelte for-Schleife, um die jeweiligen Felder des Arrays zu bearbeiten
                {
                    for (y=0; y< field.length; y++)
                    {
                        for (int i=1; i<field.length; i++) //Noch eine for-Schleife, für den Fall dass ein Feld um mehrere Einheiten bewegt werden kann
                        {
                            if(field[i][y].getValue()!=0&&field[i-1][y].getMoved()==false&&field[i][y].getMoved()==false&&(field[i-1][y].getValue()==field[i][y].getValue()||field[i-1][y].getValue()==0))
                            {
                                if(field[i-1][y].getValue()!=0)
                                {
                                    addScore(field[i][y].getValue()*2); //Fügt Punktzahl zum Score hinzu
                                    //field[i][y].setMoved(true);
                                    field[i-1][y].setMoved(true); //moved wird auf true gesetzt, damit keine Felder doppelt addiert werden
                                }
                                field[i-1][y].setValue(field[i][y].getValue()+field[i-1][y].getValue()); //Addiert Werte
                                field[i][y].setValue(0); //Setzt vorherigen Wert auf 0
                                anyFieldsMoved=true; //Es wurden Felder bewegt

                            }
                        }
                    }
                }
                break;
            }
            case 4: //Rechts
            {
                for (x=(field.length-2); x>=0; x--) //Verschachtelte for-Schleife, um die jeweiligen Felder des Arrays zu bearbeiten
                {
                    for (y=0; y< field.length; y++)
                    {
                        for (int i=(field.length-2); i>=0; i--) //Noch eine for-Schleife, für den Fall dass ein Feld um mehrere Einheiten bewegt werden kann
                        {
                            if(field[i][y].getValue()!=0&&field[i+1][y].getMoved()==false&&field[i][y].getMoved()==false&&(field[i+1][y].getValue()==field[i][y].getValue()||field[i+1][y].getValue()==0))
                            {
                                if(field[i+1][y].getValue()!=0)
                                {
                                    addScore(field[i][y].getValue()*2); //Fügt Punktzahl zum Score hinzu
                                    //field[i][y].setMoved(true);
                                    field[i+1][y].setMoved(true); //moved wird auf true gesetzt, damit keine Felder doppelt addiert werden
                                }
                                field[i+1][y].setValue(field[i][y].getValue()+field[i+1][y].getValue()); //Addiert Werte
                                field[i][y].setValue(0); //Setzt vorherigen Wert auf 0
                                anyFieldsMoved=true; //Es wurden Felder bewegt

                            }
                        }
                    }
                }
                break;
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
}
