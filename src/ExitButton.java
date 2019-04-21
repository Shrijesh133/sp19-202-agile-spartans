import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ExitButton extends Actor
{
    public ExitButton()
    {
        getImage().scale(40,40);
    }
    
    public void act()  //Funktioniert als Button und geht zurück ins Menü
    {
       if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new Menu());
        }
    }   
}
