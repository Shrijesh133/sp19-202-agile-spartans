import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOverOverlay extends Actor
{
    public GameOverOverlay(int pTransparency) //Dunkles Overlay im Falle eines Game Overs | Transparenz anpassbar, um Animationen zu ermöglichen
    {
        getImage().setTransparency(pTransparency);
    }
}
