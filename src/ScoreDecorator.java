import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreDecorator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreDecorator extends Actor
{
   
    public ScoreDecorator(Score sc)
    {
        System.out.println(sc.score);
        getImage().scale(100,30);
    }
  /*  public Score(int pScore, boolean gameOver) //Zeigt Score in verschiedenen Gräßen an
    {
        if (gameOver)
        {
            text = "Your score: "+pScore;
            printText(text,50);
        }
        else
        {
            text = "Score: "+pScore;
            printText(text,30) ;
        }
    }

    public void printText(String pValue, int pSize) //Setzt Text als Bild
    {
        setImage(new GreenfootImage(pValue, pSize, Color.WHITE,new Color(0,0,0,0)));
    } */
    
    
}
