import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Number extends Actor
{
    //Jedes visuell dargestellte Feld ist ein Number-Objekt | Field-Objekte beziehen sich nur auf die Spiellogik, ist flexibler
    
    private int imageNumber=0;
    private GreenfootImage image;
    
    public Number(int pNumber, int pScale) //Setzt Bild je nach übergebenem Parameter
    {
        setNumberImage(pNumber);
        setScale(pScale);
    }
    
    public void setNumberImage(int pNumber)
    {
        if(pNumber<=16384&&pNumber!=0) //Normale Zahl
        {
            image=new GreenfootImage(pNumber+".png");
            setImage(image);
        }
        else //Für Zahlen über 16384, weitere lassen sich einfach hinzufügen, "aber so weit kommt eh keiner" - I challenge you
        {
            image=new GreenfootImage("noImage.png");
            setImage(image);
        }
    }
    
    public void setScale(int pScale)
    {
        image.scale(pScale,pScale);
        setImage(image);
    }
}
