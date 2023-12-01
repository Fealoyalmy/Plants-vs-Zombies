package Zombies_vs_Plants;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public abstract class Bullet 
{
    int x, y;
    int line;
    public abstract void drawBullet(Graphics g);        
    public abstract void explode(Graphics g);   
}

class PeaBullet extends Bullet
{
    PeaBullet(int l, int x, int y)
    {
        this.line = l;
        this.x = x;
        this.y = y;
    }
    
    public void drawBullet(Graphics g)
    {        
        g.drawImage((new ImageIcon("src/image/Íã¶¹×Óµ¯.png")).getImage(), this.x, this.y, null);
        this.x += 10;
    }
    
    public void explode(Graphics g)
    {       
        g.drawImage(new ImageIcon("src/image/Íã¶¹×Óµ¯»÷ÖÐ.gif").getImage(), this.x, this.y, null);         
    }
}

class IceBullet extends Bullet
{
    IceBullet(int l, int x, int y)
    {
        this.line = l;
        this.x = x;
        this.y = y;
    }
    
    public void drawBullet(Graphics g)
    {
        g.drawImage((new ImageIcon("src/image/±ù¶¹×Óµ¯.png")).getImage(), this.x, this.y, null);
        this.x += 10;
    }
    
    public void explode(Graphics g)
    {       
        g.drawImage(new ImageIcon("src/image/±ù¶¹×Óµ¯»÷ÖÐ.png").getImage(), x - 20, y,null);
    }
}

//Ñô¹âÀà
class Sun
{
    private static int s = 25;
    int x;
    int y = 0;
    int y_max;
    
    Sun()
    {        
        Random random = new Random();
        x = 300 + random.nextInt(600);
        y_max = 350 + random.nextInt(300);
    }
    
    Sun(int x, int y)
    {
        this.x = x;
        this.y = y;
        y_max = y;
    }
    
    public void drawSun(Graphics g)
    {        	
        if(y < y_max)
            this.y += 1;
        g.drawImage((new ImageIcon("src/image/Ñô¹â.gif")).getImage(), this.x, this.y, null);
    }
    
    public static int getS()
    {
        return s;
    }
}
