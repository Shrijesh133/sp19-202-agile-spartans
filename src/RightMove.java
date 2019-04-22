import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class UpMove extends Move {

    @Override
    int moveOperation(Field[][] field) {

        int score  = 0;

        for (x=1; x<field.length; x++) //Nested for loop to manipulate the respective fields of the array
        {
            for (y=0; y< field.length; y++)
            {
                for (int i=1; i<field.length; i++) // Another for loop, in case a field can be moved by several units
                {
                    if(field[i][y].getValue()!=0&&field[i-1][y].getMoved()==false&&field[i][y].getMoved()==false&&(field[i-1][y].getValue()==field[i][y].getValue()||field[i-1][y].getValue()==0))
                    {
                        if(field[i-1][y].getValue()!=0)
                        {
                            score += field[i][y].getValue()*2; //Add score
                            //field[i][y].setMoved(true);
                            field[i-1][y].setMoved(true); //moved is set to true so that no fields are added twice
                        }
                        field[i-1][y].setValue(field[i][y].getValue()+field[i-1][y].getValue()); //Add values
                        field[i][y].setValue(0); //Sets previous value to 0
                        this.anyFieldsMoved=true; //Fields were moved
                    }
                }
            }
        }
    return  score;
    }

}