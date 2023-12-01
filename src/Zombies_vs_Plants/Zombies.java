package Zombies_vs_Plants;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public abstract class Zombies 
{
    Random random = new Random();
    int x, y;
    int line;
    int hp;
    int atk; // ÿ���˺�
    int speed; // ��ÿ��
    boolean isSlow;
    boolean move;
    int time, timeA;
    int num;
    
    abstract void draw(Graphics g);
    // ��ʬ��������  *******������ʬ��ֲ����غ�
    public void attack(int xl)
    {        
        if((BackBoard.singlegame.second - this.timeA) % 30 == 0) //ÿ�빥��һ��
            for(int i = BackBoard.plants.size() - 1; i >= 0; i--) //���������жϹ�����ǰ��ֲ��            
                if(BackBoard.plants.get(i).line == this.line && (BackBoard.plants.get(i).x - 273) / 85 == xl)
                {
                    Music.play("sounds/�׽�.wav");
                    BackBoard.plants.get(i).getHurt(this.atk); //ֲ���ܵ��˺�
                    //System.out.println(BackBoard.plants.get(i).hp);
                    if(BackBoard.plants.get(i).hp <= 0) //��ֲ������ֵС��0��ɾ����ֲ��
                    {
                        BackBoard.plants.remove(i);
                        BackBoard.singlegame.chessboard[xl][line] = 0; //�������̴˸�����Ϊ��
                        this.timeA = 0; //���ù���ʱ���
                    }                
                    break;
                }       
    }
    // ��ʬ�ƶ�����
    public void move()
    {
        int speed;
        //this.x = 1280 - ((BackBoard.singlegame.second - this.time) * 17) / 30;
        if(this.isSlow == true)
            speed = this.speed * 3;
        else
            speed = this.speed;
        if(85 / speed < 30)
        {
            if(((BackBoard.singlegame.second - this.time) * 85 / speed) % 30 <= 85 / speed)
                this.x--;
        }
        else
        {
            if(((BackBoard.singlegame.second - this.time) * 85 / speed) % 30 <= 85 / speed - 30)
                this.x -= 2;
        }
    }
    
    public void getHurt(int a)
    {
        this.hp -= a;
    }
    
    public void dead(int i, Graphics g)
    {
        if(this.hp > -60)
        {
            g.drawImage(new ImageIcon("src/image/��ʬ��.png").getImage(), this.x - 40, this.y + 30, null);
            this.hp--;
        }
        else
            BackBoard.zombies.remove(i);
    }
}

class CommonZomb extends Zombies //��ͨ��ʬ
{   
    CommonZomb()
    {
        timeA = 0;
        time = BackBoard.singlegame.second;
        hp = 100;
        atk = 20;
        speed = 5; 
        line = random.nextInt(5);
        x = 1280;
        y = 95 + 108 * line;
        BackBoard.singlegame.commonZ--;
    }
    
    public void draw(Graphics g)
    {
        int xl = (x - 258) / 85;//273
        //�жϽ�ʬ���������Ƿ���ֲ��
        if(xl < 10 && xl >= 0 && BackBoard.singlegame.chessboard[xl][line] == 1) //��ֲ����ʼ����
        {
            if(this.timeA == 0) //������ʱ��㲻Ϊ0��������ڹ���ͬһ��ֲ��
                this.timeA = BackBoard.singlegame.second; //��¼��ʼ������ʱ���
            g.drawImage(new ImageIcon("src/image/��ͨ��ʬ����.gif").getImage(), x, y, null);
            this.attack(xl);
        }
        else //��ֲ�����������
        {
            this.move();
            g.drawImage(new ImageIcon("src/image/��ͨ��ʬ����.gif").getImage(), x, y, null);
        }
    }  
}

class MetalpailZomb extends Zombies //��Ͱ��ʬ
{
    MetalpailZomb()
    {    
        timeA = 0;
        time = BackBoard.singlegame.second;
        hp = 200;
        atk = 20;
        speed = 5; 
        line = random.nextInt(5);
        x = 1280;
        y = 70 + 109 * line;
        BackBoard.singlegame.metalZ--;
    }
    
    public void draw(Graphics g)
    {
        int xl = (x - 258) / 85;
        //�жϽ�ʬ���������Ƿ���ֲ��
        if(xl < 10 && xl >= 0 && BackBoard.singlegame.chessboard[xl][line] == 1) //��ֲ����ʼ����
        {
            if(this.timeA == 0) //������ʱ��㲻Ϊ0��������ڹ���ͬһ��ֲ��
                this.timeA = BackBoard.singlegame.second; //��¼��ʼ������ʱ���
            g.drawImage(new ImageIcon("src/image/��Ͱ��ʬ����.gif").getImage(), x, y, null);
            this.attack(xl);
        }
        else //��ֲ�����������
        {
            this.move();
            g.drawImage(new ImageIcon("src/image/��Ͱ��ʬ����.gif").getImage(), x, y, null);
        }
    }   
    
}

class RugbyZomb extends Zombies //�����ʬ
{
    RugbyZomb()
    { 
        timeA = 0;
        time = BackBoard.singlegame.second;
        hp = 100;
        atk = 10;
        speed = 2; 
        line = random.nextInt(5);
        x = 1280;
        y = 70 + 109 * line;
        BackBoard.singlegame.rugbyZ--;
    }
    
    public void draw(Graphics g)
    {
        int xl = (x - 258) / 85;
        //�жϽ�ʬ���������Ƿ���ֲ��
        if(xl < 10 && xl >= 0 && BackBoard.singlegame.chessboard[xl][line] == 1) //��ֲ����ʼ����
        {
            if(this.timeA == 0) //������ʱ��㲻Ϊ0��������ڹ���ͬһ��ֲ��
                this.timeA = BackBoard.singlegame.second; //��¼��ʼ������ʱ���
            g.drawImage(new ImageIcon("src/image/�����ʬ����.gif").getImage(), x, y, null);
            this.attack(xl);
        }
        else //��ֲ�����������
        {
            this.move();
            g.drawImage(new ImageIcon("src/image/�����ʬ����.gif").getImage(), x, y, null);
        }
    }   
    
    public void dead(int i, Graphics g)
    {
        if(this.hp > -60)
        {
            g.drawImage(new ImageIcon("src/image/�����ʬ����.gif").getImage(), this.x, this.y, null);
            this.hp--;
        }
        else
            BackBoard.zombies.remove(i);
    }
}

class NewspaperZomb extends Zombies // ������ʬ
{
    NewspaperZomb()
    { 
        timeA = 0;
        time = BackBoard.singlegame.second;
        hp = 100;
        atk = 50;
        speed = 6; 
        line = random.nextInt(5);
        x = 1280;
        y = 60 + 109 * line;
        BackBoard.singlegame.newspaperZ--;
    }
    
    public void draw(Graphics g)
    {
        int xl = (x - 258) / 85;
        //�жϽ�ʬ���������Ƿ���ֲ��
        if(xl < 10 && xl >= 0 && BackBoard.singlegame.chessboard[xl][line] == 1) //��ֲ����ʼ����
        {
            if(this.timeA == 0) //������ʱ��㲻Ϊ0��������ڹ���ͬһ��ֲ��
                this.timeA = BackBoard.singlegame.second; //��¼��ʼ������ʱ���
            if(this.hp > 50)
                g.drawImage(new ImageIcon("src/image/������ʬͷ������.gif").getImage(), x, y, null);
            else
                g.drawImage(new ImageIcon("src/image/������ʬ�첿����.gif").getImage(), x, y, null);
            this.attack(xl);
        }
        else //��ֲ�����������
        {            
            if(this.hp > 50)
            {
                this.speed = 6;
                g.drawImage(new ImageIcon("src/image/������ʬ����.gif").getImage(), x, y, null);
            }
            else //����ֵ����50����ֽ���������ʼ���ߣ����ٱ�Ϊ1��ÿ��
            {
                this.speed = 1;
                g.drawImage(new ImageIcon("src/image/������ʬ����.gif").getImage(), x, y, null);
            }
            this.move();
        }
    }   
}
