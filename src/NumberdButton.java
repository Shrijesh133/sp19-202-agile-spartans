/**
 * Write a description of class NumberdButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class NumberdButton implements Cloneable
{
    // instance variables - replace the example below with your own
    private int number;
    protected String type;
    /**
     * Constructor for objects of class NumberdButton
     */
    public NumberdButton()
    {
    }

   public String getType(){
      return type;
   }
   
   
   public int getNumber() {
      return number;
   }
   
   public void setNumber(int number) {
      this.number = number;
   }
   
   public Object clone() {
      Object clone = null;
      
      try {
         clone = super.clone();
         
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      
      return clone;
   }
}
