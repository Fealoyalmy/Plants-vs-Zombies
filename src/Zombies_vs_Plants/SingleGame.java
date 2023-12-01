package Zombies_vs_Plants;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

public class SingleGame extends JPanel implements ActionListener
{       
    SoundPlay.bgmThread bt = new SoundPlay.bgmThread();
    Thread bgm = new Thread(bt);
    static int commonZ = 0, metalZ = 0, newspaperZ = 0, rugbyZ = 0;
    static int choice = 0;
    boolean gameover = false;
    boolean gamewin = false;
    int pscd = 0;int ipscd = 0;int ncd = 0;int scd = 0; //����ֲ��CD����
    int p_cost = 0; //��ǰѡ�е�ֲ��Ӧ���ѵ�����
    int second; //��Ϸ��ʱ
    int[][] chessboard = new int[10][5]; //�����ƺ���̣���ʼֵ��Ϊ0��
    Random random = new Random(); //�������
    ImageIcon map, shop;
    JPanel shopP = new JPanel();
    JButton pause, mainmenu;
    boolean ifPause = false; //�ж���ͣ��ť״̬
    boolean mouseMov = false; //�ж��Ƿ���ֲ���ͼ׼����ֲ
    boolean ifField = false; //�ж�����Ƿ��ڲ�ƺ��
    ImageIcon pauseI = new ImageIcon("src/image/��ͣ.jpg"); //����ͼƬ
    ImageIcon continueI = new ImageIcon("src/image/����.png");
    ImageIcon mainmenuI = new ImageIcon("src/image/�˵�.jpg");
    ImageIcon peaShooterI = new ImageIcon("src/image/�㶹����.png"); //�̵���ֲ��ͼƬ
    ImageIcon icePeaShooterI = new ImageIcon("src/image/��������.png");
    ImageIcon nutI = new ImageIcon("src/image/���.png");
    ImageIcon sunFlowerI = new ImageIcon("src/image/���տ�.png");
    JLabel backmap = new JLabel();
    JLabel backshop = new JLabel(); 
    JLabel peaShooterL = new JLabel();JLabel icePeaShooterL = new JLabel();JLabel nutL = new JLabel();JLabel sunFlowerL = new JLabel();
    JLabel peaShooterInfo = new JLabel();JLabel icePeaShooterInfo = new JLabel();JLabel nutInfo = new JLabel();JLabel sunFlowerInfo = new JLabel(); 
    int sunnum = 50;  //��ʼ������ֵ
    JLabel sunNum = new JLabel("" + sunnum);    
    JLabel coords = new JLabel(); //�������
    GameThread gamethread = new GameThread(); //������Ϸ�̶߳��� Thread gamethread = new Thread(new GameThread());
    //����
    Font f1 = new Font("TimesRoman", Font.PLAIN, 18);
    Font f2 = new Font("TimesRoman", Font.PLAIN, 12);
    Font f3 = new Font("TimesRoman", Font.PLAIN, 28);
    
    SingleGame()
    {        
        this.setLayout(null);
        this.setSize(BackBoard.Width, BackBoard.Height);	
	       
        map = new ImageIcon("src/image/��ͼ��1.78�ȣ�.jpg"); 
        map.setImage(map.getImage().getScaledInstance(BackBoard.Width, BackBoard.Height, Image.SCALE_DEFAULT));
        backmap.setIcon(map);
        backmap.setSize(BackBoard.Width, BackBoard.Height);
        //�����̵���Panel
        shopP.setLayout(null);
        shop = new ImageIcon("src/image/�̵���.png"); 
        shop.setImage(shop.getImage().getScaledInstance(525, 100, Image.SCALE_DEFAULT));
        backshop.setIcon(shop);
        backshop.setSize(525, 100);
        shopP.setBounds(270, 0, 525, 100);        
        sunNum.setFont(f1);
        sunNum.setBounds(32, 75, 40, 20);         
        //�̵�ֲ���ͼ
        peaShooterI.setImage(peaShooterI.getImage().getScaledInstance(65, 85, Image.SCALE_DEFAULT));
        icePeaShooterI.setImage(icePeaShooterI.getImage().getScaledInstance(65, 85, Image.SCALE_DEFAULT));
        nutI.setImage(nutI.getImage().getScaledInstance(65, 85, Image.SCALE_DEFAULT));
        sunFlowerI.setImage(sunFlowerI.getImage().getScaledInstance(65, 85, Image.SCALE_DEFAULT));
        peaShooterL.setIcon(peaShooterI);
        icePeaShooterL.setIcon(icePeaShooterI);
        nutL.setIcon(nutI);
        sunFlowerL.setIcon(sunFlowerI);
        //ֲ���ͼ��Сλ��
        peaShooterL.setBounds(90, 8, 65, 85);  
        icePeaShooterL.setBounds(160, 8, 65, 85);
        nutL.setBounds(230, 8, 65, 85);
        sunFlowerL.setBounds(300, 8, 65, 85);
        //ֲ���ͼ��ʾ��Ϣ����Ϊ��ɫ��    
        peaShooterInfo.setForeground(Color.red);icePeaShooterInfo.setForeground(Color.red);nutInfo.setForeground(Color.red);sunFlowerInfo.setForeground(Color.red);       
        //���ͼƬ����ʾ
        shopP.add(peaShooterInfo);
        shopP.add(icePeaShooterInfo);
        shopP.add(nutInfo);
        shopP.add(sunFlowerInfo);
        shopP.add(peaShooterL);
        shopP.add(icePeaShooterL);
        shopP.add(nutL);
        shopP.add(sunFlowerL);
        shopP.add(sunNum);  
        shopP.add(backshop);        
        //���ð�ť        
        pauseI.setImage(pauseI.getImage().getScaledInstance(60, 50, Image.SCALE_DEFAULT));
        mainmenuI.setImage(mainmenuI.getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
        pause = new JButton(pauseI);
        mainmenu = new JButton(mainmenuI);       
        pause.setBounds(1120, 0, 50, 50);
        mainmenu.setBounds(1180, 0, 100, 50);
        //������  
        coords.setFont(f1);
        coords.setBounds(15, 50, 200, 30);
        //this.add(coords);
        this.add(shopP);
        this.add(pause);
        this.add(mainmenu);                
        this.add(backmap);                
	this.setVisible(true);
        pause.addActionListener(this);
        mainmenu.addActionListener(this);
	this.addMouseListener(new MouseAdpt()); //��갴ť����
        this.addMouseMotionListener(new MouseMotionAdpt()); //����ƶ�����
        second = 0;
        gamethread.start(); //��Ϸ�߳̿�ʼ
        bgm.start();
    }
    
    static void setChoice()
    {
        switch(choice)
        {
            case 1: setZomb(10, 2, 0, 0);break;
            case 2: setZomb(20, 3, 1, 1);break;
            case 3: setZomb(30, 5, 3, 2);break;
            default: setZomb(0, 0, 0, 0);
        }
    }
    static void setZomb(int cZ, int mZ, int nZ, int rZ)
    {        
        commonZ = cZ; 
        metalZ = mZ;
        newspaperZ = nZ;
        rugbyZ = rZ;
    }
    
    public void paint(Graphics g) 
    {	
	super.paint(g);  //���ø���paint����	
        //˫�����ͼ
        BufferedImage image = new BufferedImage(BackBoard.Width, BackBoard.Height, BufferedImage.TRANSLUCENT);//����һ������,���ô�С��Ϊ͸����
	Graphics gs = image.createGraphics();  //����һ������       
        
        //�ж��Ƿ�ʤ��
        if(commonZ <= 0 && metalZ <= 0 && rugbyZ <= 0 && newspaperZ <= 0 && BackBoard.zombies.size() == 0 && gamewin == false)
        {
            bt.pt.stop();
            bgm.stop();
            gamethread.stop();
            Music.play("sounds/��Ϸʤ��1.wav");
            BackBoard.game.windialog.setModal(true);
            BackBoard.game.windialog.setVisible(true);
            gamewin = true;
        }
        sunNum.setText("" + sunnum);
        //��ͼ�����ˢ�µ�������
        if(second != 0 && second % 300 == 0 && second <= 900)
            BackBoard.sun.add(new Sun());
        else if(second > 900 && second % 600 == 0)
            BackBoard.sun.add(new Sun());
        //����ÿ��ֲ��
        for(int i = 0; i < BackBoard.plants.size(); i++)
        {
            BackBoard.plants.get(i).draw(gs);      
            if(mouseMov == true && BackBoard.plants.get(i).time == 0 && ifField == true)
                BackBoard.plants.get(i).predraw(gs);
        }                  
        //����ÿ����ʬ
        for(int i = 0; i < BackBoard.zombies.size(); i++)
        {
            if(BackBoard.zombies.get(i).x < 100 && !gameover)// ���н�ʬԽ������ʱ��ʾ��Ϸ����
            {
                bt.pt.stop();
                bgm.stop();
                BackBoard.singlegame.gamethread.stop();
                new Thread(new FailMusic()).start();
                BackBoard.game.faildialog.setModal(true);
                BackBoard.game.faildialog.setVisible(true); 
                gameover = true;
            }
            if(BackBoard.zombies.get(i).hp > 0)
                BackBoard.zombies.get(i).draw(gs);
            else //������ֵ<=0����ɾ������
            {
                //System.out.println(BackBoard.zombies.get(i).hp);
                BackBoard.zombies.get(i).dead(i, gs);                
            }
        }
        //����ÿ������
        for(int i = 0; i < BackBoard.sun.size(); i++)
            BackBoard.sun.get(i).drawSun(gs); 
        //��������ӽ����
        g.drawImage(image, 0, 0, this);  
    }
    
    class GameThread extends Thread//implements Runnable //��Ϸ�߳� �ڲ���
    {      
        private final Object lock = new Object();
        private boolean pause = false;
        
	public void run() 
        {
            super.run();
            while(true)
            {      
                if(Thread.currentThread().isInterrupted())
                    break;
                while (pause)
                    onPause();     
                //�жϿ�����ʾֲ���ͼ��ʾ
                if(pscd > 0) //�ж��Ƿ���CD��ȴ
                {            
                    PeaShooter.ifPlant = false;
                    if((second - PeaShooter.Ptime) % 30 == 0) //��ʾʣ��CD
                    {
                        peaShooterInfo.setFont(f3);
                        peaShooterInfo.setBounds(113, 35, 40, 30);
                        peaShooterInfo.setText("" + (pscd - 1));  
                        pscd--;
                    }                    
                }
                else //������CD��ȴʱ
                {                    
                    if(sunnum < PeaShooter.cost) //�ж�����ֵ�Ƿ���������
                    {
                        peaShooterInfo.setFont(f2);
                        peaShooterInfo.setBounds(98, 40, 65, 30);
                        peaShooterInfo.setText("���ⲻ�㣡");
                        PeaShooter.ifPlant = false;
                    }
                    else
                    {
                        peaShooterInfo.setText("");
                        PeaShooter.ifPlant = true;
                    }
                }
                if(ipscd > 0)
                {            
                    IcePeaShooter.ifPlant = false;
                    if((second - IcePeaShooter.Ptime) % 30 == 0)
                    {
                        icePeaShooterInfo.setFont(f3);
                        icePeaShooterInfo.setBounds(183, 35, 40, 30);
                        icePeaShooterInfo.setText("" + (ipscd - 1));  
                        ipscd--;
                    }                    
                }
                else
                {
                    if(sunnum < IcePeaShooter.cost)
                    {
                        icePeaShooterInfo.setFont(f2);
                        icePeaShooterInfo.setBounds(168, 40, 65, 30);
                        icePeaShooterInfo.setText("���ⲻ�㣡");
                        IcePeaShooter.ifPlant = false;
                    }
                    else
                    {
                        icePeaShooterInfo.setText("");
                        IcePeaShooter.ifPlant = true;
                    }
                }
                if(ncd > 0)
                {          
                    Nut.ifPlant = false;
                    if((second - Nut.Ptime) % 30 == 0)
                    {
                        nutInfo.setFont(f3);
                        nutInfo.setBounds(253, 35, 40, 30);
                        nutInfo.setText("" + (ncd - 1));  
                        ncd--;
                    }                    
                }
                else
                {
                    if(sunnum < Nut.cost)
                    {
                        nutInfo.setFont(f2);
                        nutInfo.setBounds(238, 40, 65, 30);
                        nutInfo.setText("���ⲻ�㣡");
                        Nut.ifPlant = false;
                    }
                    else
                    {
                        nutInfo.setText("");
                        Nut.ifPlant = true;
                    }
                }
                if(scd > 0)
                {           
                    SunFlower.ifPlant = false;
                    if((second - SunFlower.Ptime) % 30 == 0)
                    {
                        sunFlowerInfo.setFont(f3);
                        sunFlowerInfo.setBounds(323, 35, 40, 30);
                        sunFlowerInfo.setText("" + (scd - 1));  
                        scd--;
                    }                    
                }
                else
                {
                    if(sunnum < SunFlower.cost)
                    {
                        sunFlowerInfo.setFont(f2);
                        sunFlowerInfo.setBounds(308, 40, 65, 30);
                        sunFlowerInfo.setText("���ⲻ�㣡");
                        SunFlower.ifPlant = false;
                    }
                    else
                    {
                        sunFlowerInfo.setText("");
                        SunFlower.ifPlant = true;
                    }
                }            
                //���ɽ�ʬ�㷨                   
                if(second == 900)
                {
                    Music.play("sounds/��ʬ����1.wav");
                    BackBoard.zombies.add(new CommonZomb());
                }
                
                if(choice == 1 && second > 900 && (second - 900) % 600 == 0)
                {
                    if((commonZ == 5 && metalZ == 2) || (commonZ == 1 && metalZ == 1))
                        BackBoard.zombies.add(new MetalpailZomb());
                    else if(commonZ > 0)
                        BackBoard.zombies.add(new CommonZomb());
                }                   
                else if(choice == 2 && second > 900)
                {
                    if(commonZ >= 10  && (second - 900) % 600 == 0)
                    {
                        if((commonZ == 15 && metalZ == 3) || (commonZ == 10 && metalZ == 2))
                            BackBoard.zombies.add(new MetalpailZomb());
                        else
                            BackBoard.zombies.add(new CommonZomb());
                    }
                    else if(commonZ > 0 && commonZ < 10 && (second - 900) % 400 == 0)
                    {
                        if(commonZ == 8 && newspaperZ == 1)
                            BackBoard.zombies.add(new NewspaperZomb());
                        else if(commonZ == 5 && metalZ == 1)
                            BackBoard.zombies.add(new MetalpailZomb());
                        else if(commonZ == 1 && rugbyZ == 1)
                            BackBoard.zombies.add(new RugbyZomb());
                        else
                            BackBoard.zombies.add(new CommonZomb());
                    }
                }               
                else if(choice == 3 && second > 900)
                {
                    if(commonZ >= 20 && (second - 900) % 600 == 0)
                    {
                        if((commonZ == 25 && metalZ == 5) || (commonZ == 20 && metalZ == 4))
                            BackBoard.zombies.add(new MetalpailZomb());
                        else
                            BackBoard.zombies.add(new CommonZomb());                        
                    }
                    else if(commonZ >= 10 && commonZ < 20 && (second - 900) % 400 == 0)
                    {
                        if((commonZ == 15 && metalZ == 3) || (commonZ == 10 && metalZ == 2))
                            BackBoard.zombies.add(new MetalpailZomb());
                        else if (commonZ == 12 && newspaperZ == 3)
                            BackBoard.zombies.add(new NewspaperZomb());
                        else
                            BackBoard.zombies.add(new CommonZomb());
                    }
                    else if(commonZ > 0 && commonZ < 10 && (second - 900) % 300 == 0)
                    {
                        if(commonZ == 6 && metalZ == 1)
                            BackBoard.zombies.add(new MetalpailZomb());
                        else if((commonZ == 8 && newspaperZ == 2) || (commonZ == 4 && newspaperZ == 1))
                            BackBoard.zombies.add(new NewspaperZomb());
                        else if((commonZ == 2 && rugbyZ == 2) || (commonZ == 0 && rugbyZ == 1))
                            BackBoard.zombies.add(new RugbyZomb());
                        else
                            BackBoard.zombies.add(new CommonZomb());
                    }
                }    
                //System.out.println("second��" + second + ";��ͨ��" + commonZ + ";��Ͱ��" + metalZ + ";������" + newspaperZ + ";�����" + rugbyZ);
                repaint();              
                try{
			Thread.sleep(30); //�߳�����
                    }catch(InterruptedException e){e.printStackTrace();}               
                second++; 
            }
        }
        //���ø÷���ʵ���̵߳���ͣ
        void pauseThread()
        {
            pause = true;
        }
        //���ø÷���ʵ�ָֻ��̵߳�����
        void resumeThread()
        {
            pause =false;
            synchronized (lock)
            {
                lock.notify();
            }
        }
        //�������ֻ����run ������ʵ�֣���Ȼ���������̣߳�����ҳ������Ӧ
        void onPause() 
        {
            synchronized (lock) 
            {
                try 
                {
                    lock.wait();
                }catch(InterruptedException e){e.printStackTrace();}
            }
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == mainmenu)
        {
            bt.pt.suspend();
            bgm.suspend();
            gamethread.pauseThread();           
            pause.setIcon(pauseI);
            ifPause = false;
            BackBoard.game.menudialog.setModal(true);
            BackBoard.game.menudialog.setVisible(true);          
            //��������Ӹ����������add�������ʾ�����ϲ㣩
        }
        else
        {
            if(ifPause == false)
            {       
                bt.pt.suspend();
                bgm.suspend();
                pause.setIcon(continueI);
                ifPause = true;
                gamethread.pauseThread();//��Ϸ�߳�������ͣ
                
                //backmusic.pauseThread();
            }
            else
            {
                pause.setIcon(pauseI);
                ifPause = false;
                gamethread.resumeThread();//��Ϸ�߳�������ͣ
                bt.pt.resume();
                bgm.resume();
                //backmusic.pauseThread();
            }
        }
    }
             
    class MouseAdpt extends MouseAdapter
    {              
        public void mouseClicked(MouseEvent e) //�������
        {            
            for(int i = 0; i < BackBoard.sun.size(); i++) //������������
            {
                //�ж��Ƿ����ջ��������
                if(e.getX() >= BackBoard.sun.get(i).x + 11 && e.getX() <= BackBoard.sun.get(i).x + 60 && e.getY() >= BackBoard.sun.get(i).y + 8 && e.getY() <= BackBoard.sun.get(i).y + 58)
                {    
                    BackBoard.sun.remove(i); //ɾ�������е�����
                    sunnum += Sun.getS(); //��������ֵ 
                    break;
                }
            }
            //�ж��Ƿ��ڲݵ�����ֲ
            if(mouseMov == true)
            {
                //��ƺ���Ͻ���ʼλ��
                int xe = e.getX() - 273;
                int ye = e.getY() - 109; //139
                //ѡ��ĵؿ���
                int xl = xe / 85;
                int yl = ye / 116;
                //System.out.println(xl + ";" + yl);
                //ѡ���������ֲ�ڸ�������ָ��λ��                
                if(xe >= 0 && xl <= 9 && ye >= 0 && yl <= 4 && chessboard[xl][yl] == 0)
                {
                    //���ݵؿ�ȷ����ֲ�����ջ�ͼ����
                    Music.play("sounds/����2.wav");
                    BackBoard.plants.get(BackBoard.plants.size() - 1).time = second;
                    BackBoard.plants.get(BackBoard.plants.size() - 1).line = yl;
                    BackBoard.plants.get(BackBoard.plants.size() - 1).x = 282 + xl*87;
                    BackBoard.plants.get(BackBoard.plants.size() - 1).y = 130 + yl*109; //160
                    chessboard[xl][yl] = 1;
                    ifField = false;
                    //Ԥ����ֲ��CD
                    if(PeaShooter.ifCD)
                        pscd = PeaShooter.cd + 1;
                    else if(IcePeaShooter.ifCD)
                        ipscd = IcePeaShooter.cd + 1;
                    else if(Nut.ifCD)
                        ncd = Nut.cd + 1;
                    else if(SunFlower.ifCD)
                        scd = SunFlower.cd + 1;
                    //����CD״̬
                    PeaShooter.ifCD = false;
                    IcePeaShooter.ifCD = false;
                    Nut.ifCD = false;
                    SunFlower.ifCD = false;
                    BackBoard.plants.get(BackBoard.plants.size() - 1).Ptime = second; //��¼��ֲʱ��ʱ���
                    sunnum -= p_cost; //�۳���ֲ���ѵ�����
                    mouseMov = false; //�رո�������ͼ
                }
                else if(xe >= 0 && xl <= 9 && ye >= 0 && yl <= 4 && chessboard[xl][yl] == 1) //���ò�ƺ����ֲ�����޷�������ֲ
                    ;
                else
                {
                    BackBoard.plants.remove(BackBoard.plants.size() - 1); //�������ƺ����������ɾ����ֲ��
                    PeaShooter.ifCD = false;
                    IcePeaShooter.ifCD = false;
                    Nut.ifCD = false;
                    SunFlower.ifCD = false;
                    mouseMov = false; //�رո�������ͼ
                }
            }
            //�жϵ���ĸ�ֲ���ͼ
            else if(PeaShooter.ifPlant == true && e.getX() >= 365 && e.getX() <= 430 && e.getY() >= 8 && e.getY() <= 100)
            {
                BackBoard.plants.add(new PeaShooter(e.getX() - 33, e.getY() - 60));
                p_cost = PeaShooter.cost;
                PeaShooter.ifCD = true;
                mouseMov = true;
            }
            else if(IcePeaShooter.ifPlant == true && e.getX() >= 435 && e.getX() <= 500 && e.getY() >= 8 && e.getY() <= 100)
            {
                BackBoard.plants.add(new IcePeaShooter(e.getX() - 33, e.getY() - 60));
                p_cost = IcePeaShooter.cost;
                IcePeaShooter.ifCD = true;
                mouseMov = true;
            }
            else if(Nut.ifPlant == true && e.getX() >= 505 && e.getX() <= 570 && e.getY() >= 8 && e.getY() <= 100)
            {
                BackBoard.plants.add(new Nut(e.getX() - 33, e.getY() - 60));
                p_cost = Nut.cost;
                Nut.ifCD = true;
                mouseMov = true;
            }
            else if(SunFlower.ifPlant == true && e.getX() >= 575 && e.getX() <= 640 && e.getY() >= 8 && e.getY() <= 100)
            {
                BackBoard.plants.add(new SunFlower(e.getX() - 33, e.getY() - 60));
                p_cost = SunFlower.cost;
                SunFlower.ifCD = true;
                mouseMov = true;
            }            
        }                     
    }
    
    class MouseMotionAdpt extends MouseMotionAdapter
    {       
        public void mouseMoved(MouseEvent e)
        {
            //coords.setText("X: " + (e.getX()-3) + ",Y: " + (e.getY()-32)); //�ο��������
            if(mouseMov == true) //ѡ��ֲ����������ͼ��Ԥ��ѡ����ֲ
            {
                BackBoard.plants.get(BackBoard.plants.size() - 1).x = e.getX() - 33;
                BackBoard.plants.get(BackBoard.plants.size() - 1).y = e.getY() - 60;               
                //��ƺ���Ͻ���ʼλ��
                int xe = e.getX() - 273;
                int ye = e.getY() - 109; //139
                //ѡ��ĵؿ���
                int xl = xe / 85;
                int yl = ye / 116;
                //System.out.println(xl + ";" + yl);
                //�ƶ��������͸����ʾ�������ڱ�ѡλ��           
                if(xe >= 0 && xl <= 9 && ye >= 0 && yl <= 4 && chessboard[xl][yl] == 0)
                {
                    ifField = true;
                    BackBoard.plants.get(BackBoard.plants.size() - 1).px = 282 + xl*87;
                    BackBoard.plants.get(BackBoard.plants.size() - 1).py = 130 + yl*109;
                }
                else
                    ifField = false;
            }
        }
    }
}

