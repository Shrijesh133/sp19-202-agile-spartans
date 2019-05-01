import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class HighscoreShadow extends Actor
{
    //Eigentlich das gleiche wie Highscore, nur in dunkel | dient als Schatten, um in das Spieldesign zu passen
    
    private Board board=new Board();
    private String text ="Score: 0";
    private GreenfootImage image;

    public HighscoreShadow(int pScore, boolean gameOver)
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

    public void printText(String pValue,int pSize)
    {
        setImage(new GreenfootImage(pValue, pSize,new Color(0,0,0,120),new Color(0,0,0,0)));
    }
}
