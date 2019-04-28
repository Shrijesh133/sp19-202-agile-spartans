import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/*
 * How to Play Screen
 */
public class Options extends WorldMaster
{
    private SoundButton soundButton = new SoundButton();
    private ExitButton exitButton = new ExitButton();
    private HowToPlay howToPlay = new HowToPlay();
    private MusicCopyright musicCopyright = new MusicCopyright();
    
    /*
     * Constructor
     */
    public Options() //Erstellt das aus Modulen bestehende Optionsmenü und startet das Spiel u. die Musik automatisch
    {    
        super(480, 600, 1); //Erstellt die Welt
        
        addObject(soundButton,420,540); //Fügt Objekte hinzu
        addObject(exitButton,60,540);
        addObject(howToPlay,240,180);
        addObject(musicCopyright,240,400);
        
        soundButton.playIfSoundOn(); //Startet Musik
        Greenfoot.start(); //Startet Spiel automatisch
    }
}
