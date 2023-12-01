package Zombies_vs_Plants;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public abstract class Plants 
{
    //static boolean ifPlant, ifCD;//是否可种植
    static int Ptime;
    int time;
    int x, y;
    int px, py;
    int line;
    int hp;
    int cd;
    int cost;    
    
    void getHurt(int a)
    {
        this.hp -= a;
    }
  
    abstract void draw(Graphics g);
    abstract void predraw(Graphics g);
}

class SunFlower extends Plants //向日葵
{        
    static int cd = 5;
    static int cost = 25;         
    static boolean ifPlant, ifCD;
    //static int Ptime;
    
    SunFlower(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.hp = 100;
    }
    
    public void predraw(Graphics g)
    {
        g.drawImage(new ImageIcon("src/image/向日葵t.gif").getImage(), px, py, null);
    }
    
    public void draw(Graphics g)
    {
        //绘制种植时的泥土效果
        if(BackBoard.singlegame.second - time > 0 && BackBoard.singlegame.second - time < 4)
            g.drawImage(new ImageIcon("src/image/泥土.png").getImage(), x + 15, y + 50, null);
        g.drawImage(new ImageIcon("src/image/向日葵.gif").getImage(), x, y, null);
        this.createSun();        
    }    
    
    public void createSun() //向日葵每隔15秒产生一个阳光
    {
        if(time > 0 && BackBoard.singlegame.second - time > 749 && (BackBoard.singlegame.second - time) % 750 == 0)
        {
            BackBoard.sun.add(new Sun(x, y));
            Music.play("sounds/生成阳光.wav");
        }
    }
}

class PeaShooter extends Plants //豌豆射手
{
    static int cd = 10;
    static int cost = 100;
    private int atk = 10;
    static boolean ifPlant, ifCD;
    ArrayList<PeaBullet> bullet = new ArrayList();
    
    PeaShooter(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.hp = 100;
    }
    public void predraw(Graphics g)
    {
        g.drawImage(new ImageIcon("src/image/豌豆射手t.gif").getImage(), px, py, null);
    }
    //绘图方法
    public void draw(Graphics g)
    {
        if(BackBoard.singlegame.second - time > 0 && BackBoard.singlegame.second - time < 4)
            g.drawImage(new ImageIcon("src/image/泥土.png").getImage(), x + 15, y + 50, null);
        g.drawImage(new ImageIcon("src/image/豌豆射手.gif").getImage(), x, y, null);
        //判断该植物所在行是否有僵尸
        for(int i = 0; i < BackBoard.zombies.size(); i++)
            if(line == BackBoard.zombies.get(i).line && BackBoard.zombies.get(i).hp > 0) //所在行有僵尸则开始射击
            {
                this.shoot(g);
                break;
            }
    }
    //射击动作
    public void shoot(Graphics g)
    {
        if(time != 0 && (BackBoard.singlegame.second - time) % 60 == 0) //植物已被种下且每隔2秒射击一次
        {
            bullet.add(new PeaBullet(this.line, this.x + 50, this.y + 3));
            Music.play("sounds/射击.wav");
        }
        this.ifAttack(g);
    }
    //判断是否命中僵尸
    public void ifAttack(Graphics g)
    {
        for(int i = 0; i < bullet.size(); i++) //遍历子弹队列
        {
            if(bullet.get(i).x >= 1280) //子弹出界则删除
                bullet.remove(i);
            else
            {
                bullet.get(i).drawBullet(g); //画出子弹
                for(int j = 0; j < BackBoard.zombies.size(); j++) //遍历僵尸队列
                    if(line == BackBoard.zombies.get(j).line && bullet.get(i).x > BackBoard.zombies.get(j).x + 25 && BackBoard.zombies.get(j).hp > 0) //若子弹命中僵尸
                    {
                        Music.play("sounds/击中2.wav");
                        bullet.get(i).explode(g);
                        BackBoard.zombies.get(j).getHurt(atk); //僵尸受到伤害                       
                        bullet.remove(i); //删除该子弹
                        break;
                    } 
            }
        }
    }
}

class IcePeaShooter extends Plants //寒冰射手
{
    static int cd = 15;
    static int cost = 150;
    private int atk = 5;
    static boolean ifPlant, ifCD;
    ArrayList<IceBullet> bullet = new ArrayList();
    
    IcePeaShooter(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.hp = 100;
    }
    public void predraw(Graphics g)
    {
        g.drawImage(new ImageIcon("src/image/寒冰射手t.gif").getImage(), px, py, null);
    }
    public void draw(Graphics g)
    {
        if(BackBoard.singlegame.second - time > 0 && BackBoard.singlegame.second - time < 4)
            g.drawImage(new ImageIcon("src/image/泥土.png").getImage(), x + 15, y + 50, null);
        g.drawImage(new ImageIcon("src/image/寒冰射手.gif").getImage(), x, y, null);
        //判断该植物所在行是否有僵尸
        for(int i = 0; i < BackBoard.zombies.size(); i++)
            if(line == BackBoard.zombies.get(i).line)
            {              
                this.shoot(g);
                break;
            }
    }
    
    public void shoot(Graphics g)
    {
        if(time != 0 && (BackBoard.singlegame.second - time) % 60 == 0)
        {
            bullet.add(new IceBullet(this.line, this.x + 50, this.y + 3));
            Music.play("sounds/射击.wav");
        }
        this.ifAttack(g);
    }
    
    public void ifAttack(Graphics g)
    {
        for(int i = 0; i < bullet.size(); i++)
        {
            if(bullet.get(i).x >= 1280)
                bullet.remove(i);
            else
            {
                bullet.get(i).drawBullet(g);
                for(int j = 0; j < BackBoard.zombies.size(); j++)
                    if(line == BackBoard.zombies.get(j).line && bullet.get(i).x > BackBoard.zombies.get(j).x + 30)
                    {
                        Music.play("sounds/击中2.wav");
                        bullet.get(i).explode(g);
                        BackBoard.zombies.get(j).getHurt(atk);
                        BackBoard.zombies.get(j).isSlow = true;
                        bullet.remove(i);
                        break;
                    } 
            }
        }
    }
}

class Nut extends Plants //坚果
{   
    static int cd = 8;
    static int cost = 50;
    static boolean ifPlant, ifCD;
    
    Nut(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.hp = 200;
    }
    public void predraw(Graphics g)
    {
        g.drawImage(new ImageIcon("src/image/坚果t.gif").getImage(), px, py, null);
    }
    public void draw(Graphics g)
    {
        if(BackBoard.singlegame.second - time > 0 && BackBoard.singlegame.second - time < 4)
            g.drawImage(new ImageIcon("src/image/泥土.png").getImage(), x + 15, y + 50, null);
        g.drawImage(new ImageIcon("src/image/坚果.gif").getImage(), x, y, null);
    }
}
