import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SadDecorator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SadDecorator extends Actor implements IDecorator
{
    /**
     * Act - do whatever the SadDecorator wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public SadDecorator() {
    }
    
    
   public void display()
    {
        getImage().scale(200,50);
    }    
}
