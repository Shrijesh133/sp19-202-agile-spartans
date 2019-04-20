import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MusicCopyright extends Actor
{
    private String link= "http://newagemusic.guide/new-age-music-history/the-amazing-windows-xp-installation-songs/";
    
    public void act() //Funktioniert als Button und öffnet die Quellseite der Musik im Browser
    {
        if (Greenfoot.mouseClicked(this))
        {
            ProcessBuilder pb = new ProcessBuilder();
            String[] cmd = { "cmd", "/C", "start", link };
            pb.command(cmd);
            Process p;
            try
            {
                p = pb.start();
            }
            catch (Exception e)
            {
            }
        }
    }    
}
