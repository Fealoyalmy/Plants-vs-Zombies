/* ���ٴ���ҵ����ֲ���ս��ʬ
 * �����ߣ�ë�Ҿ�
 * ѧ�ţ�18115298
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
        this.setTitle("ֲ���ս��ʬ");
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
        //����ͼƬ��Ӧ�����ߴ�
        image = new ImageIcon("src/image/����.jpg");       
        image.setImage(image.getImage().getScaledInstance(BackBoard.Width, BackBoard.Height, Image.SCALE_DEFAULT));
        background.setIcon(image);
        //����ͼƬ
        ImageIcon singlep = new ImageIcon("src/image/����ð��.jpg");
        ImageIcon doublep = new ImageIcon("src/image/˫����Ϸ.jpg");
        ImageIcon exitp = new ImageIcon("src/image/�˳���Ϸ.jpg");
        //���尴ť
        singleplay = new JButton(singlep);
        doubleplay = new JButton(doublep);
        exit = new JButton(exitp);
        //���ð�ť��Сλ��
        singleplay.setBounds(700, 470, 200, 50);
        doubleplay.setBounds(360, 470, 200, 50);
        exit.setBounds(1080, 590, 180, 80);
        //��Ӱ�ť�ͱ�������        
        this.add(singleplay);
        this.add(doubleplay);
        this.add(exit);
        this.add(background);
        //ע�ᰴť����
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
            //˫����������*************************************
        }
        else
        {
            int res=JOptionPane.showConfirmDialog(null, "�Ƿ��˳�", "�˳�", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_NO_OPTION)
                System.exit(0);
        }
    }
}