/* 寒假大作业——植物大战僵尸
 * 制作者：毛忠军
 * 学号：18115298
 */

package Zombies_vs_Plants;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BackBoard extends JFrame
{
    static int Width = 1280, Height = 720;
    static ArrayList<Plants> plants = new ArrayList();   
    static ArrayList<Zombies> zombies = new ArrayList();
    static ArrayList<Sun> sun = new ArrayList();
    static BackBoard game;
    static MainMenu menu;
    static SingleGame singlegame;
    ChoiceDialog choicedialog = new ChoiceDialog(this);
    MenuDialog menudialog = new MenuDialog(this);
    FailDialog faildialog = new FailDialog(this);
    WinDialog windialog = new WinDialog(this);
    
    BackBoard()
    {
        this.setLayout(null);
        this.setSize(Width, Height);
        this.setTitle("植物大战僵尸");
        menu = new MainMenu();
        this.add(menu);
        this.setLocation(300, 150);
        this.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);}});
        this.setResizable(false);
        this.setVisible(true);     
        //new Thread(new Music.BackGroundMusic()).start();
    }
    static void ClearAll()
    {
        plants.clear();
        zombies.clear();
        sun.clear();
    }
    
    public static void main(String[] args) 
    {
	game = new BackBoard();           
    }
}

class MainMenu extends JPanel implements ActionListener
{    
    JButton singleplay, doubleplay, exit;
    JLabel background = new JLabel();
    ImageIcon image;

    MainMenu()
    {       
        this.setSize(BackBoard.Width, BackBoard.Height);
        this.setLayout(null); 
        //设置图片适应容器尺寸
        image = new ImageIcon("src/image/背景.jpg");       
        image.setImage(image.getImage().getScaledInstance(BackBoard.Width, BackBoard.Height, Image.SCALE_DEFAULT));
        background.setIcon(image);
        //插入图片
        ImageIcon singlep = new ImageIcon("src/image/单人冒险.jpg");
        ImageIcon doublep = new ImageIcon("src/image/双人游戏.jpg");
        ImageIcon exitp = new ImageIcon("src/image/退出游戏.jpg");
        //定义按钮
        singleplay = new JButton(singlep);
        doubleplay = new JButton(doublep);
        exit = new JButton(exitp);
        //设置按钮大小位置
        singleplay.setBounds(700, 470, 200, 50);
        doubleplay.setBounds(360, 470, 200, 50);
        exit.setBounds(1080, 590, 180, 80);
        //添加按钮和背景容器        
        this.add(singleplay);
        this.add(doubleplay);
        this.add(exit);
        this.add(background);
        //注册按钮监听
        singleplay.addActionListener(this);
        doubleplay.addActionListener(this);
        exit.addActionListener(this);
        
        background.setSize(BackBoard.Width, BackBoard.Height);       
        //this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == singleplay)
        {                       
            BackBoard.game.choicedialog.setVisible(true);
        }
        else if(e.getSource() == doubleplay)
        {
            //close();
            //双人联机界面*************************************
        }
        else
        {
            int res=JOptionPane.showConfirmDialog(null, "是否退出", "退出", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_NO_OPTION)
                System.exit(0);
        }
    }
}