import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Game extends WorldMaster
{
    private Board board = new Board();
    private Number number;
    private SoundButton soundButton = new SoundButton();
    private ExitButton exitButton = new ExitButton();
    
    public Game() //Erstellt die Welt und startet das Spiel automatisch
    {    
        super(480, 600, 1); //Erstellt die Welt (480*600, da jedes Feld 120*120 groß ist + 1 Reihe für Score etc. | eine 4*5 Welt ginge auch, duch die "kleine Skalierung" lassen sich dann die Objekte aber nicht so gut ausrichten)
        
        addObject(board,60,540); //Fügt Objekte hinzu
        addObject(soundButton,420,540);
        addObject(exitButton,60,540);
        
        Greenfoot.start(); //Startet Spiel automatisch
    }
}
