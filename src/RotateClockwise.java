/**
 * RotateClockwise class define starttegy to rotate fields block clockwise by 90 degree.
 * 
 * @author (Vaubhav Gupta) 
 * @version (04-29-2019)
 */
public class RotateClockwise implements RotationStrategy
{
   
    /**
     * Constructor for objects of class RotateClockwise
     */
    public RotateClockwise()
    {
    }

    /**
     * @param  field parameter that to be rotated
     * @return  an array of Fields after rotation 
     */
     public Field[][] doRotation(Field[][] field)
     {
     return field;
     }
    
}
