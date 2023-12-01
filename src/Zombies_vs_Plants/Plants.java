package Zombies_vs_Plants;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public abstract class Plants 
{
    //static boolean ifPlant, ifCD;//�Ƿ����ֲ
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

class SunFlower extends Plants //���տ�
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
        g.drawImage(new ImageIcon("src/image/���տ�t.gif").getImage(), px, py, null);
    }
    
    public void draw(Graphics g)
    {
        //������ֲʱ������Ч��
        if(BackBoard.singlegame.second - time > 0 && BackBoard.singlegame.second - time < 4)
            g.drawImage(new ImageIcon("src/image/����.png").getImage(), x + 15, y + 50, null);
        g.drawImage(new ImageIcon("src/image/���տ�.gif").getImage(), x, y, null);
        this.createSun();        
    }    
    
    public void createSun() //���տ�ÿ��15�����һ������
    {
        if(time > 0 && BackBoard.singlegame.second - time > 749 && (BackBoard.singlegame.second - time) % 750 == 0)
        {
            BackBoard.sun.add(new Sun(x, y));
            Music.play("sounds/��������.wav");
        }
    }
}

class PeaShooter extends Plants //�㶹����
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
        g.drawImage(new ImageIcon("src/image/�㶹����t.gif").getImage(), px, py, null);
    }
    //��ͼ����
    public void draw(Graphics g)
    {
        if(BackBoard.singlegame.second - time > 0 && BackBoard.singlegame.second - time < 4)
            g.drawImage(new ImageIcon("src/image/����.png").getImage(), x + 15, y + 50, null);
        g.drawImage(new ImageIcon("src/image/�㶹����.gif").getImage(), x, y, null);
        //�жϸ�ֲ���������Ƿ��н�ʬ
        for(int i = 0; i < BackBoard.zombies.size(); i++)
            if(line == BackBoard.zombies.get(i).line && BackBoard.zombies.get(i).hp > 0) //�������н�ʬ��ʼ���
            {
                this.shoot(g);
                break;
            }
    }
    //�������
    public void shoot(Graphics g)
    {
        if(time != 0 && (BackBoard.singlegame.second - time) % 60 == 0) //ֲ���ѱ�������ÿ��2�����һ��
        {
            bullet.add(new PeaBullet(this.line, this.x + 50, this.y + 3));
            Music.play("sounds/���.wav");
        }
        this.ifAttack(g);
    }
    //�ж��Ƿ����н�ʬ
    public void ifAttack(Graphics g)
    {
        for(int i = 0; i < bullet.size(); i++) //�����ӵ�����
        {
            if(bullet.get(i).x >= 1280) //�ӵ�������ɾ��
                bullet.remove(i);
            else
            {
                bullet.get(i).drawBullet(g); //�����ӵ�
                for(int j = 0; j < BackBoard.zombies.size(); j++) //������ʬ����
                    if(line == BackBoard.zombies.get(j).line && bullet.get(i).x > BackBoard.zombies.get(j).x + 25 && BackBoard.zombies.get(j).hp > 0) //���ӵ����н�ʬ
                    {
                        Music.play("sounds/����2.wav");
                        bullet.get(i).explode(g);
                        BackBoard.zombies.get(j).getHurt(atk); //��ʬ�ܵ��˺�                       
                        bullet.remove(i); //ɾ�����ӵ�
                        break;
                    } 
            }
        }
    }
}

class IcePeaShooter extends Plants //��������
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
        g.drawImage(new ImageIcon("src/image/��������t.gif").getImage(), px, py, null);
    }
    public void draw(Graphics g)
    {
        if(BackBoard.singlegame.second - time > 0 && BackBoard.singlegame.second - time < 4)
            g.drawImage(new ImageIcon("src/image/����.png").getImage(), x + 15, y + 50, null);
        g.drawImage(new ImageIcon("src/image/��������.gif").getImage(), x, y, null);
        //�жϸ�ֲ���������Ƿ��н�ʬ
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
            Music.play("sounds/���.wav");
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
                        Music.play("sounds/����2.wav");
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

class Nut extends Plants //���
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
        g.drawImage(new ImageIcon("src/image/���t.gif").getImage(), px, py, null);
    }
    public void draw(Graphics g)
    {
        if(BackBoard.singlegame.second - time > 0 && BackBoard.singlegame.second - time < 4)
            g.drawImage(new ImageIcon("src/image/����.png").getImage(), x + 15, y + 50, null);
        g.drawImage(new ImageIcon("src/image/���.gif").getImage(), x, y, null);
    }
}
