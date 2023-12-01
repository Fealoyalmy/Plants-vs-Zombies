package Zombies_vs_Plants;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyDialog extends JFrame
{
    public static void main(String args[])
    {
        
    }
}

class ChoiceDialog extends JDialog implements ActionListener
{
    static int Width = 400, Height = 420;
    JButton easy, common, difficult, back;
    JLabel background = new JLabel();
    
    ChoiceDialog(JFrame f)
    {
        super(f);
        this.setLayout(null);
        this.setSize(Width, Height);        
        ImageIcon menubackgroundp = new ImageIcon("src/image/�˵�������.png");
        menubackgroundp.setImage(menubackgroundp.getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT));
        background.setIcon(menubackgroundp);        
        easy = new JButton(new ImageIcon("src/image/��.jpg")); //new ImageIcon("")
        common = new JButton(new ImageIcon("src/image/��ͨ.jpg"));
        difficult = new JButton(new ImageIcon("src/image/����.jpg"));
        back = new JButton(new ImageIcon("src/image/����.jpg"));
        easy.setBounds(100, 40, 200, 60);
        common.setBounds(100, 120, 200, 60);
        difficult.setBounds(100, 200, 200, 60);
        back.setBounds(100, 300, 200, 60);
        this.add(easy);
        this.add(common);
        this.add(difficult);
        this.add(back);
        this.add(background);
        easy.addActionListener(this);
        common.addActionListener(this);
        difficult.addActionListener(this);
        back.addActionListener(this);
        background.setSize(Width, Height);
        this.setBounds(750, 340, Width, Height);
        this.setUndecorated(true); //ȥ���رհ�ť
    }   
            
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == easy)
        {
            addGamePanel();
            SingleGame.choice = 1;                       
        }
        else if(e.getSource() == common)
        {
            addGamePanel();
            SingleGame.choice = 2;  
        }
        else if(e.getSource() == difficult)
        {
            addGamePanel();
            SingleGame.choice = 3;             
        }
        else
        {
            this.setVisible(false);
        }
        SingleGame.setChoice();
    }
    
    void addGamePanel()
    {
        this.setVisible(false);
        BackBoard.singlegame = new SingleGame();
        BackBoard.menu.setVisible(false);
        BackBoard.game.add(BackBoard.singlegame);
    }
}

class MenuDialog extends JDialog implements ActionListener
{
    static int Width = 400, Height = 570;
    JButton continueGame, backToMenu, restart, exit;
    JLabel background = new JLabel();
    
    MenuDialog(JFrame f)
    {
	super(f);
        this.setLayout(null);
        this.setSize(Width, Height);        
        ImageIcon menubackgroundp = new ImageIcon("src/image/�˵�������.png");
        menubackgroundp.setImage(menubackgroundp.getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT));
        background.setIcon(menubackgroundp);       
        ImageIcon continuegamep = new ImageIcon("src/image/������Ϸ.jpg");
        ImageIcon backtomenup = new ImageIcon("src/image/���˵�.jpg");
        ImageIcon restartp = new ImageIcon("src/image/���¿�ʼ.jpg");
        ImageIcon exitp = new ImageIcon("src/image/�˳���Ϸ1.jpg");
        continueGame = new JButton(continuegamep);
        backToMenu = new JButton(backtomenup);
        restart = new JButton(restartp);
        exit = new JButton(exitp);
        continueGame.setBounds(100, 30, 200, 90);
        backToMenu.setBounds(100, 160, 200, 90);
        restart.setBounds(100, 290, 200, 90);
        exit.setBounds(100, 420, 200, 90);        
        this.add(continueGame);
        this.add(backToMenu);
        this.add(restart);
        this.add(exit);
        this.add(background);
        continueGame.addActionListener(this);
        backToMenu.addActionListener(this);
        restart.addActionListener(this);
        exit.addActionListener(this);
        background.setSize(Width, Height);
        this.setBounds(750, 240, Width, Height);
        this.setUndecorated(true); //ȥ���رհ�ť
    }
    
    public void actionPerformed(ActionEvent e)
    {
	if(e.getSource() == continueGame)
        {
            BackBoard.singlegame.bt.pt.resume();
            BackBoard.singlegame.bgm.resume();
            BackBoard.singlegame.gamethread.resumeThread();
            this.setVisible(false);
        }
        else if(e.getSource() == restart) //��ն����ؿ��ռ䣬�߳�����
        {            
            int res=JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ�����¿�ʼ��Ϸ��", "�Ƿ����¿�ʼ", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_NO_OPTION)
            {
                BackBoard.singlegame.bt.pt.stop();
                BackBoard.singlegame.bgm.stop();
                BackBoard.singlegame.gamethread.interrupt();
                BackBoard.ClearAll(); //�����������list
                BackBoard.game.remove(BackBoard.singlegame);
                this.setVisible(false);
                BackBoard.singlegame = new SingleGame();
                SingleGame.setChoice();
                BackBoard.game.add(BackBoard.singlegame);
                BackBoard.singlegame.setVisible(true);
            }            
        }
        else if(e.getSource() == backToMenu)
        {
            int res=JOptionPane.showConfirmDialog(null, "�������˵���������ǰ��Ϸ��", "�Ƿ񷵻����˵�", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_NO_OPTION)
            {
                BackBoard.singlegame.bt.pt.stop();
                BackBoard.singlegame.bgm.stop();
                BackBoard.singlegame.gamethread.interrupt();
                this.setVisible(false);
                BackBoard.singlegame.setVisible(false);
                BackBoard.game.remove(BackBoard.singlegame);
                BackBoard.ClearAll(); //�����������list
                BackBoard.menu.setVisible(true);
            }
        }
        else
        {
                System.exit(0);
        }
    }
}

class WinDialog extends JDialog implements ActionListener
{
    static int Width = 700, Height = 500;
    JLabel background = new JLabel();
    JLabel win = new JLabel();
    JButton btn = new JButton(new ImageIcon("src/image/����.jpg"));
    
    WinDialog(JFrame f)
    {       
        super(f);
        this.setLayout(null);
        this.setSize(Width, Height);       
        ImageIcon menubackgroundp = new ImageIcon("src/image/�˵�������.png");
        menubackgroundp.setImage(menubackgroundp.getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT));
        background.setIcon(menubackgroundp);        
        ImageIcon winp = new ImageIcon("src/image/��Ϸʤ��.png");       
        win.setIcon(winp);
        btn = new JButton(new ImageIcon("src/image/����.jpg"));
        btn.setBounds(250, 400, 200, 70);
        win.setBounds(25, 0, 650, 400);
        this.add(win);
        this.add(btn);
        this.add(background);        
        btn.addActionListener(this);        
        background.setSize(Width, Height);
        this.setBounds(600, 240, Width, Height);
        this.setUndecorated(true); //ȥ���رհ�ť
    }
    
    public void actionPerformed(ActionEvent e)
    {
        this.setVisible(false);
        BackBoard.singlegame.setVisible(false);
        BackBoard.ClearAll(); //�����������list
        BackBoard.game.remove(BackBoard.singlegame);
        BackBoard.menu.setVisible(true);
    }
}

class FailDialog extends JDialog implements ActionListener
{
    static int Width = 560, Height = 550;
    JLabel background = new JLabel();
    JLabel fail = new JLabel();
    JButton btn = new JButton(new ImageIcon("src/image/����.jpg"));
    
    FailDialog(JFrame f)
    {
        super(f);
        this.setLayout(null);
        this.setSize(Width, Height);        
        ImageIcon menubackgroundp = new ImageIcon("src/image/�˵�������.png");
        menubackgroundp.setImage(menubackgroundp.getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT));
        background.setIcon(menubackgroundp);        
        ImageIcon failp = new ImageIcon("src/image/��Ϸʧ��.png");
        fail.setIcon(failp);
        fail.setBounds(31, 0, 497, 437);
        btn.setBounds(180, 450, 200, 70);
        this.add(fail);
        this.add(btn);
        this.add(background);
        btn.addActionListener(this);
        background.setSize(Width, Height);
        this.setBounds(640, 240, Width, Height);
        this.setUndecorated(true); //ȥ���رհ�ť
    }
    
    public void actionPerformed(ActionEvent e)
    {
        this.setVisible(false);
        BackBoard.singlegame.setVisible(false);
        BackBoard.ClearAll(); //�����������list
        BackBoard.game.remove(BackBoard.singlegame);
        BackBoard.menu.setVisible(true);
    }
}