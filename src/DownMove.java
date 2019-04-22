import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class UpMove extends Move {

    @Override
    int moveOperation(Field[][] field) {

        int score  = 0;

        for (y=(field.length-2); y>=0; y--) //Nested for loop to manipulate the respective fields of the array
        {
            for (x=0; x< field.length; x++)
            {
                for (int i=(field.length-2); i>=0; i--) // Another for loop, in case a field can be moved by several units
                {
                    if(field[x][i].getValue()!=0&&field[x][i+1].getMoved()==false&&field[x][i].getMoved()==false&&(field[x][i+1].getValue()==field[x][i].getValue()||field[x][i+1].getValue()==0))
                    {
                        if(field[x][i+1].getValue()!=0)
                        {
                           score += field[x][i].getValue()*2; //Add score
                            //field[x][i].setMoved(true);
                            field[x][i+1].setMoved(true); //moved is set to true so that no fields are added twice
                        }
                        field[x][i+1].setValue(field[x][i].getValue()+field[x][i+1].getValue()); //Add values
                        field[x][i].setValue(0); //Sets previous value to 0
                        anyFieldsMoved=true; //Fields were moved

                    }
                }
            }
        }
    return  score;
    }

}