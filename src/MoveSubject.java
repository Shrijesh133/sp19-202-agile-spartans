import greenfoot.*;// (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;


public abstract class MoveSubject
{
   
    private Field[][] field;
    private List<Observer> observers = new ArrayList<Observer>();
    private int score =0;
 

    public  MoveSubject (Field[][] field) {
        this.field = field;
    }


    abstract boolean moveDirection(Field[][] field,int score);

    public final boolean move() {

        boolean anyFieldsMoved =   moveDirection(field,score);
        
        updateScoreObservers();
        return anyFieldsMoved;

   }
   
   
     public void attach(Observer observer){
      observers.add(observer);      
   }

   public void updateScoreObservers(){
      for (Observer observer : observers) {
         observer.update(score);
      }
   } 








}
