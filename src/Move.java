import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Move
{
   

    private Field[][] field;


    public  Move (Field[][] field) {
        this.field = field;
    }


    abstract boolean moveDirection(Field[][] field,int score);

    public final boolean move() {
        
        int score = 0;

        boolean anyFieldsMoved =   moveDirection(field,score);

       

        return anyFieldsMoved;

   }







}
