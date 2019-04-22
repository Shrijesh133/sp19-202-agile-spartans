import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Move extends Actor
{
    protected anyFieldsMoved;

    private Field[][] field;


    public move (Field[][] field, boolean anyFieldsMoved) {
        this.field = field;
        this.anyFieldsMoved = anyFieldsMoved;
    }


    abstract int moveDirection(field);

    public final int move() {

        int score =   moveDirection();

       if(anyFieldMoved) {

        updateFieldVisuals();
        placeRandomField();

       }

        return score;

   }



    public void playPlaceAnimation(int pX, int pY, int pValue) //"Ploppt" ein neues zufälliges Feld auf
    {
        if(getWorld()!=null)
        {
            for (int i=1; i<120;i=i+16)
            {
                number = new Number(pValue,i);
                getWorld().addObject(number,pX*120+60,pY*120+60);
                Greenfoot.delay(1);
                getWorld().removeObject(number);
            }
            updateFieldVisuals();
        }
    }

    public void updateFieldVisuals() //Updated die visuelle Darstellung des Spiels
    {
        getWorld().removeObjects(getWorld().getObjects(Number.class));
        for (int x=0; x< field.length; x++)
        {
            for (int y=0; y< field.length; y++)
            {
                int currentValue = field[x][y].getValue();
                if (currentValue>0)
                {
                    number = new Number(currentValue,120);
                    getWorld().addObject(number,x*120+60,y*120+60);
                }
            }
        }
    }


    public void placeRandomField() //Plaziert an einer zufälligen leeren Stelle ein Feld, falls vorhanden
    {
        boolean numberPlaced=false;
        boolean emptyFields=false;
        int xRandom = Greenfoot.getRandomNumber(4);
        int yRandom = Greenfoot.getRandomNumber(4);
        int x=0;
        int y=0;
        int placedValue=2;
        for (x=0; x< field.length; x++) //Prüft, ob leere Felder vorhanden sind, damit keine Endlosschleife entsteht
        {
            for (y=0; y< field.length; y++)
            {
                if (field[x][y].getValue()==0)
                {
                    emptyFields=true;
                }
            }
        }
        if (emptyFields) //Plaziert an einer zufälligen leeren Stelle ein Feld, falls vorhanden
        {
            while (numberPlaced==false)
            {
                if (field[xRandom][yRandom].getValue()==0)
                {
                    field[xRandom][yRandom].setRandomInitialValue();
                    placedValue=field[xRandom][yRandom].getValue();
                    numberPlaced=true;
                }
                else
                {
                    xRandom = Greenfoot.getRandomNumber(4);
                    yRandom = Greenfoot.getRandomNumber(4);
                }
            }
        }
        if (numberPlaced) //Spielt die (noch nicht implementierte) separate Animation ab
        {
            playPlaceAnimation(xRandom,yRandom,placedValue);
        }
    }





}
