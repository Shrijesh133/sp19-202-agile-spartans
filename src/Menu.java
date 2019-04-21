import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

public class Menu extends WorldMaster
{
    private Logo logo = new Logo();
    private PlayButton playButton = new PlayButton(false);
    private SoundButton soundButton = new SoundButton();
    private MenuBackground menuBackground= new MenuBackground();
    private HelpButton helpButton = new HelpButton();
    private Highscore highScoreActor;
    private HighscoreShadow highScoreShadowActor;

    public Menu() //Erstellt das aus Modulen bestehende Menü und startet das Spiel u. die Musik automatisch
    {    
        super(480, 600, 1); //Erstellt Welt
        
        addObject(menuBackground,240,360); //Fügt Objekte hinzu
        addObject(logo, 240, 120);
        addObject(playButton, 240, 400);
        addObject(soundButton,420,540);
        addObject(helpButton,60,540);
        
        highScoreShadowActor = new HighscoreShadow(loadHighscore(),false); //Highscore mit Schatten
        addObject(highScoreShadowActor,242,542);
        highScoreActor = new Highscore(loadHighscore(),false);
        addObject(highScoreActor,240,540);
        
        soundButton.playIfSoundOn(); //Spielt Musik
        Greenfoot.start(); //Startet
    }

    public int loadHighscore() //Lädt den highscore aus highscore.txt
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
}
