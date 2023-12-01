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
    int atk; // 每秒伤害
    int speed; // 秒每格
    boolean isSlow;
    boolean move;
    int time, timeA;
    int num;
    
    abstract void draw(Graphics g);
    // 僵尸攻击方法  *******两个僵尸吃植物会重合
    public void attack(int xl)
    {        
        if((BackBoard.singlegame.second - this.timeA) % 30 == 0) //每秒攻击一次
            for(int i = BackBoard.plants.size() - 1; i >= 0; i--) //遍历查找判断攻击面前的植物            
                if(BackBoard.plants.get(i).line == this.line && (BackBoard.plants.get(i).x - 273) / 85 == xl)
                {
                    Music.play("sounds/咀嚼.wav");
                    BackBoard.plants.get(i).getHurt(this.atk); //植物受到伤害
                    //System.out.println(BackBoard.plants.get(i).hp);
                    if(BackBoard.plants.get(i).hp <= 0) //若植物生命值小于0则删除该植物
                    {
                        BackBoard.plants.remove(i);
                        BackBoard.singlegame.chessboard[xl][line] = 0; //虚拟棋盘此格重置为空
                        this.timeA = 0; //重置攻击时间点
                    }                
                    break;
                }       
    }
    // 僵尸移动方法
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
            g.drawImage(new ImageIcon("src/image/僵尸死.png").getImage(), this.x - 40, this.y + 30, null);
            this.hp--;
        }
        else
            BackBoard.zombies.remove(i);
    }
}

class CommonZomb extends Zombies //普通僵尸
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
        //判断僵尸所处坐标是否有植物
        if(xl < 10 && xl >= 0 && BackBoard.singlegame.chessboard[xl][line] == 1) //有植物则开始攻击
        {
            if(this.timeA == 0) //若攻击时间点不为0则表明仍在攻击同一颗植物
                this.timeA = BackBoard.singlegame.second; //记录开始攻击的时间点
            g.drawImage(new ImageIcon("src/image/普通僵尸攻击.gif").getImage(), x, y, null);
            this.attack(xl);
        }
        else //无植物则继续行走
        {
            this.move();
            g.drawImage(new ImageIcon("src/image/普通僵尸行走.gif").getImage(), x, y, null);
        }
    }  
}

class MetalpailZomb extends Zombies //铁桶僵尸
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
        //判断僵尸所处坐标是否有植物
        if(xl < 10 && xl >= 0 && BackBoard.singlegame.chessboard[xl][line] == 1) //有植物则开始攻击
        {
            if(this.timeA == 0) //若攻击时间点不为0则表明仍在攻击同一颗植物
                this.timeA = BackBoard.singlegame.second; //记录开始攻击的时间点
            g.drawImage(new ImageIcon("src/image/铁桶僵尸攻击.gif").getImage(), x, y, null);
            this.attack(xl);
        }
        else //无植物则继续行走
        {
            this.move();
            g.drawImage(new ImageIcon("src/image/铁桶僵尸行走.gif").getImage(), x, y, null);
        }
    }   
    
}

class RugbyZomb extends Zombies //橄榄球僵尸
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
        //判断僵尸所处坐标是否有植物
        if(xl < 10 && xl >= 0 && BackBoard.singlegame.chessboard[xl][line] == 1) //有植物则开始攻击
        {
            if(this.timeA == 0) //若攻击时间点不为0则表明仍在攻击同一颗植物
                this.timeA = BackBoard.singlegame.second; //记录开始攻击的时间点
            g.drawImage(new ImageIcon("src/image/橄榄球僵尸攻击.gif").getImage(), x, y, null);
            this.attack(xl);
        }
        else //无植物则继续行走
        {
            this.move();
            g.drawImage(new ImageIcon("src/image/橄榄球僵尸行走.gif").getImage(), x, y, null);
        }
    }   
    
    public void dead(int i, Graphics g)
    {
        if(this.hp > -60)
        {
            g.drawImage(new ImageIcon("src/image/橄榄球僵尸死亡.gif").getImage(), this.x, this.y, null);
            this.hp--;
        }
        else
            BackBoard.zombies.remove(i);
    }
}

class NewspaperZomb extends Zombies // 读报僵尸
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
        //判断僵尸所处坐标是否有植物
        if(xl < 10 && xl >= 0 && BackBoard.singlegame.chessboard[xl][line] == 1) //有植物则开始攻击
        {
            if(this.timeA == 0) //若攻击时间点不为0则表明仍在攻击同一颗植物
                this.timeA = BackBoard.singlegame.second; //记录开始攻击的时间点
            if(this.hp > 50)
                g.drawImage(new ImageIcon("src/image/读报僵尸头部攻击.gif").getImage(), x, y, null);
            else
                g.drawImage(new ImageIcon("src/image/读报僵尸嘴部攻击.gif").getImage(), x, y, null);
            this.attack(xl);
        }
        else //无植物则继续行走
        {            
            if(this.hp > 50)
            {
                this.speed = 6;
                g.drawImage(new ImageIcon("src/image/读报僵尸行走.gif").getImage(), x, y, null);
            }
            else //生命值低于50即报纸被打掉，开始暴走，移速变为1秒每格
            {
                this.speed = 1;
                g.drawImage(new ImageIcon("src/image/读报僵尸暴走.gif").getImage(), x, y, null);
            }
            this.move();
        }
    }   
}
