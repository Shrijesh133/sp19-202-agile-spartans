import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ExitButton extends Actor implements ICommandInvoker
{
    ICommand command;
    
    public ExitButton()
    {
        getImage().scale(40,40);
    }
    
    public void act()  //Funktioniert als Button und geht zurück ins Menü
    {
       if (Greenfoot.mouseClicked(this))
        {
            executeCommand();
        }
    }   
    public void setCommand(ICommand c) {
        command = c;
    }
    
    public void executeCommand() {
        if (command != null) {
            command.execute();
        }
    }
}
