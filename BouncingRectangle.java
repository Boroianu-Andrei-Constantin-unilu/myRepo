import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
class BouncingRectangle
{
    public static void main(String []args)
    {
        myframe m=new myframe();
        m.setTitle("Bouncing Rectangle");
        m.setSize(500,500);
        m.setVisible(true);
        m.setLocation(300,100);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class myframe extends JFrame
{
    mypanel p;
    myframe()
    {
        p=new mypanel();
        Container c=getContentPane();
        c.add(p);
    }
}
class mypanel extends JPanel implements ActionListener,Runnable
{
    Thread animation;
    JButton btn_fast,btn_slow;
    int x=150,y=50,dx=11,dy=7,r=50,slp=500;
    mypanel()
    {
        animation=new Thread(this,"Bounce rect");
        animation.start();
        
        btn_fast=new JButton("Fast");
        btn_fast.addActionListener(this);
        add(btn_fast);
        
        btn_slow=new JButton("Slow");
        btn_slow.addActionListener(this);
        add(btn_slow);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btn_fast)
        {
            slp=slp-50;
        }
        if(ae.getSource()==btn_slow)
        {
            slp=slp+50;
        }
    }
    public void run()
    {
        try
        {
            while(true)
            {
                Rectangle bounds=getBounds();
                if((x-r+dx<0)||(x+r+dx>bounds.width))
                    dx=-dx;
                if((y-r+dy<0)||(y+r+dy>bounds.height))
                    dy=-dy;
                    
                x+=dx;
                y+=dy;
                
                repaint();
                animation.sleep(slp);
            }                
        }
        catch(Exception e)
        {
        }
    }
    public void paint(Graphics g)
    {
            super.paint(g);
        Graphics2D g2=(Graphics2D)g;
        
        Rectangle2D.Double r1=new Rectangle2D.Double(x-r,y-r,r*2,r*2);
        g2.draw(r1);
        g2.setPaint(Color.red);
        g2.fill(r1);
    }
}