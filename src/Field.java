import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

<<<<<<< HEAD
public class Field extends Actor
{
    private int value; //Integer value wird deklariert | Nummer des Feldes
    private boolean moved; //Boolean wird deklariert | Feld wurde schon bewegt/addiert
    
    public int getValue() //Getter wegen private
=======
/*
 * Field Actor: which specify which field has which value
 */
public class Field extends Actor
{
    private int value; 
    private boolean moved; 
    
    /*
     * Get the value of the field
     * @return int value of the field
     */
    public int getValue()
>>>>>>> ec1f063547d696e86c4557010f8b0a8cd99e3a43
    {
        return value;
    }
    
<<<<<<< HEAD
    public void setValue(int pValue) //Setter wegen private
=======
    /*
     * Set the value of the field
     * @param int pValue value to be set for the field
     */
    public void setValue(int pValue)
>>>>>>> ec1f063547d696e86c4557010f8b0a8cd99e3a43
    {
        value=pValue;
    }
    
<<<<<<< HEAD
    public boolean getMoved() //Getter wegen private
=======
    /*
     * Check whether the field is moved or not
     * @return boolean whether moved or not
     */
    public boolean getMoved()
>>>>>>> ec1f063547d696e86c4557010f8b0a8cd99e3a43
    {
        return moved;
    }
    
<<<<<<< HEAD
    public void setMoved(boolean pMoved) //Setter wegen private
=======
    /*
     * Set moved true or false to field
     * @param boolean pMoved moved or not
     */
    public void setMoved(boolean pMoved)
>>>>>>> ec1f063547d696e86c4557010f8b0a8cd99e3a43
    {
        moved=pMoved;
    }
    
<<<<<<< HEAD
    public void setRandomInitialValue() //Setzt value zufällig auf 2 oder 4
=======
    /*
     * Set random value from 2 or 4 to the field
     */
    public void setRandomInitialValue()
>>>>>>> ec1f063547d696e86c4557010f8b0a8cd99e3a43
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
