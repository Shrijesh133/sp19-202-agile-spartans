/**
 * RotateAnticlockwise class define starttegy to rotate fields block anti clockwise by 90 degree.
 * 
 * @author (Vaubhav Gupta) 
 * @version (04-29-2019)
 */
public class RotateAnticlockwise implements RotationStrategy
{
   
    /**
     * Constructor for objects of class RotateAntiClockwise
     */
    public RotateAnticlockwise()
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
