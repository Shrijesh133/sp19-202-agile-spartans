import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HelpButton extends Actor
{
    public HelpButton() //Setzt die Größe kleiner im Spiel
    {
        getImage().scale(40,40);
    }

    public void act()  //Funktioniert als Button und öffnet die Optionen/Credits
    {
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new Options());
        }
    }       
}
