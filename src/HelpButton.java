import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/*
 * HelpButton Actor Button when clicked, navigate to help(options) screen
 */
public class HelpButton extends Actor
{
    /*
     * Constructor
     */
    public HelpButton() 
    {
        getImage().scale(40,40);
    }

    /*
     * act method of the gereenfoot: navigate to options screen on click
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new Options());
        }
    }       
}
