import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

<<<<<<< HEAD
public class GameOverOverlay extends Actor
{
    public GameOverOverlay(int pTransparency) //Dunkles Overlay im Falle eines Game Overs | Transparenz anpassbar, um Animationen zu ermöglichen
=======
/*
 * GameOverOvelay Actor The Transparent overlay after the game is over
 */
public class GameOverOverlay extends Actor
{
    /*
     * Constructor
     * @param int pTransparency
     */
    public GameOverOverlay(int pTransparency)
>>>>>>> ec1f063547d696e86c4557010f8b0a8cd99e3a43
    {
        getImage().setTransparency(pTransparency);
    }
}
