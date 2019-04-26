import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ExitButton extends Actor 
{
    public ExitButton()
    {
        getImage().scale(40,40);
    }
    
    public void act()  //Works as a button and goes back to the menu
    {
       if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new Menu());
        }
    } 
    
    
    
}
