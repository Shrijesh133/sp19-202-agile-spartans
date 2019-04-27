import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SoundButton extends Actor
{
    //Pausiert/ Spielt die Musik | es kann Fehler mit Audiogeräten geben, daher die try-Befehle
    ICommand command;
    static boolean soundOn=true;
    private GreenfootImage soundOnImage= new GreenfootImage("soundOn.png");;
    private GreenfootImage soundOffImage= new GreenfootImage("soundOff.png");
    static GreenfootSound xpMusic=new GreenfootSound("windowsXpInstallationMusic.mp3");
    
    // public SoundButton(GreenfootImage i)
    // {
        // super(i);
    // }
    public SoundButton(){ 
        soundOnImage.scale(40,40);
        soundOffImage.scale(40,40);
        try
            {
                xpMusic.setVolume(70);
            }
            catch (Exception e)
            {
                System.err.print('\f');
                System.err.println("Der Sound kann nicht abgespielt werden. Möglicherweise gibt es ein Problem mit der Soundkarte (Ist bei den Rechnern in der Mediothek der Fall). Tipp: lassen Sie das Terminal offen und minimieren es, dann werden Sie nicht nochmal von dieser Meldung gestört.");
            }
        
    }

    public void playIfSoundOn() //Spielt die Musik, falls soundOn=true
    {
        // if(soundOn) {
            // executeCommand();
        // }
        if(soundOn)
        {
            try
            {
                xpMusic.playLoop();
            }
            catch (Exception e)
            {
                System.err.print('\f');
                System.err.println("Der Sound kann nicht abgespielt werden. Möglicherweise gibt es ein Problem mit der Soundkarte (Ist bei den Rechnern in der Mediothek der Fall). Tipp: lassen Sie das Terminal offen und minimieren es, dann werden Sie nicht nochmal von dieser Meldung gestört.");
            }
            soundOn = true;
        }
    }

    public void act() //Funktioniert als Schalter/Flip Flop und muted/startet die Musik
    {
        playIfSoundOn();
        //super.act();
        
        if (Greenfoot.mouseClicked(this))
        {
            if (soundOn)
            {
                try
                {
                    xpMusic.pause();
                }
                catch (Exception e)
                {
                    System.err.print('\f');
                    System.err.println("Der Sound kann nicht abgespielt werden. Möglicherweise gibt es ein Problem mit der Soundkarte (Ist bei den Rechnern in der Mediothek der Fall). Tipp: lassen Sie das Terminal offen und minimieren es, dann werden Sie nicht nochmal von dieser Meldung gestört.");
                }
                soundOn=false;
            }
            else if (!soundOn)
            {
                try
                {
                    xpMusic.playLoop();
                }
                catch (Exception e)
                {
                    System.err.print('\f');
                    System.err.println("Der Sound kann nicht abgespielt werden. Möglicherweise gibt es ein Problem mit der Soundkarte (Ist bei den Rechnern in der Mediothek der Fall). Tipp: lassen Sie das Terminal offen und minimieren es, dann werden Sie nicht nochmal von dieser Meldung gestört.");
                }
                soundOn=true;
            }
        }
        if (soundOn)
        {
            setImage(soundOnImage);
        }
        if (!soundOn)
        {
            setImage(soundOffImage);
        }
    }
    // public void setCommand(ICommand c) {
        // command = c;
    // }
    // public void executeCommand() {
        // if (command != null) {
            // command.execute();
        // }
    // }
}
