import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Field extends Actor
{
    private int value; //Integer value wird deklariert | Nummer des Feldes
    private boolean moved; //Boolean wird deklariert | Feld wurde schon bewegt/addiert
    
    public int getValue() //Getter wegen private
    {
        return value;
    }
    
    public void setValue(int pValue) //Setter wegen private
    {
        value=pValue;
    }
    
    public boolean getMoved() //Getter wegen private
    {
        return moved;
    }
    
    public void setMoved(boolean pMoved) //Setter wegen private
    {
        moved=pMoved;
    }
    
    public void setRandomInitialValue() //Setzt value zufällig auf 2 oder 4
    {
        int fourOrTwo = Greenfoot.getRandomNumber(2);
        if (fourOrTwo==1)
        {
            value=2;
        }
        else
        {
            value=4;
        }
    }
}
