

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Game extends WorldMaster{
    
    private int width;
    private int height;
    private Board board = new Board();
    private Number number;
    private SoundButton soundButton = new SoundButton();
    private ExitButton exitButton = new ExitButton();
    
    public Game() //Create the world and start the game automatically
    {    
        super(480, 600, 1); //Creates the world (480 * 600, since each field is 120 * 120 large + 1 row for score etc. | a 4 * 5 world would work too, but the "small scaling" will not align the objects so well)
        
        addObject(soundButton,420,540);
        addObject(exitButton,60,540);
        addObject(board, 60, 540);
        
        Greenfoot.start(); //Starts game automatically
    }
    
    
	 
    
    
}
