package Zombies_vs_Plants;
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class Music 
{  
    public static void main(String args[])
    {

    }
    
    static void play(String s)
    {
        try 
        {    
            URL u = Music.class.getClassLoader().getResource(s);
            AudioInputStream ai = AudioSystem.getAudioInputStream(u);
            SoundPlay.Play(ai);
        }catch (Exception e){e.printStackTrace();}
    }
        
    static AudioInputStream FileIn(String str)
    {
        AudioInputStream ai = null;
        try
        {
            URL u = Music.class.getClassLoader().getResource(str);
            ai = AudioSystem.getAudioInputStream(u);            
        }catch (Exception e){e.printStackTrace();}
        return ai;
    }   
}

//ÓÎÏ·Ê§°ÜÒôÐ§Ïß³Ì
class FailMusic implements Runnable 
{
    public void run()
    {
        Music.play("sounds/Ö÷ÈË¼â½Ð1.wav");
        try
        {
            Thread.sleep(3000); //Ïß³ÌÐÝÃß
        }catch(InterruptedException e){e.printStackTrace();}
        Music.play("sounds/ÓÎÏ·Ê§°Ü1.wav");
        try
        {
            Thread.sleep(2800); //Ïß³ÌÐÝÃß
        }catch(InterruptedException e){e.printStackTrace();}
        Music.play("sounds/½©Ê¬Ê¤Àû1.wav");
    }
}
//ÒôÀÖ²¥·ÅÀà
class SoundPlay 
{
    static class bgmThread implements Runnable
    {
        Thread pt;       
        public void run() 
        {
            while(true)
            {
                pt = new Thread(new playThread());
                pt.start();
                try
                {
                    Thread.sleep(18500);
                }catch(InterruptedException e){e.printStackTrace();}
            }
        }
        class playThread implements Runnable
        {
            public void run() 
            {
                playMusic(Music.FileIn("sounds/±³¾°ÒôÀÖ.wav"));
            }
        }
    }
    
    public static void Play(final AudioInputStream ai)
    {
        new Thread() 
        {
            public void run() 
            {
                playMusic(ai);
            }
        }.start();
    }
    
    public static void playMusic(AudioInputStream ai) 
    {
        byte[] audioData = new byte[1024];
        AudioInputStream ais = null;
        SourceDataLine line = null;
        try 
        {
            ais = ai;
        }catch(Exception e){e.printStackTrace();}
        if(ais != null) 
        {
            AudioFormat baseFormat = ais.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, baseFormat);
            try 
            {
                line = (SourceDataLine)AudioSystem.getLine(info);
                line.open(baseFormat);
            }catch(LineUnavailableException e){e.printStackTrace();}
        }
        if(line == null)
            return;
        line.start();
        int inByte = 0;
        while(inByte != -1) 
        {
            try 
            {
                inByte = ais.read(audioData, 0, 1024);
            }catch(IOException ioe){ioe.printStackTrace();}
            try
            {
                if(inByte > 0)
                    line.write(audioData, 0, inByte);
            }catch(Exception e){e.printStackTrace();}
        }
        line.drain();
        line.stop();
        line.close();
    }
}