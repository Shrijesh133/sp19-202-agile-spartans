import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Highscore extends Actor
{
    private Board board=new Board();
    private String text ="Score: 0";
    private GreenfootImage image;

    public Highscore(int pScore, boolean gameOver) //Zeigt den Highscore in verschiedenen Größen an, je nach Spielstatus (Game Over oder nicht)
    {
        text = "Highscore: "+pScore;
        if (gameOver)
        {
            printText(text,50);
        }
        else
        {
            printText(text,30);
        }
    }

    public void printText(String pValue,int pSize) //Setzt den Text asl Bild
    {
        setImage(new GreenfootImage(pValue, pSize, Color.WHITE,new Color(0,0,0,0)));
    }
}
