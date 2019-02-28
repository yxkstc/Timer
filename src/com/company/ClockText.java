package com.company;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.DataFormatException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;
/**文本框显示时间控件,未使用
* */
public class ClockText extends JTextField implements ActionListener{
    private Timer t ;
    Calendar c;
    //	private String str;
    public ClockText(){
        super();
        s();
        this.addActionListener(this);
        t.start();
    }
    public ClockText(int i){
        super(i);
        s();
        this.addActionListener(this);
        t.start();
    }
    private void s(){
        t=new Timer(1000,this);
    }
    public void actionPerformed(ActionEvent e) {
        c=Calendar.getInstance();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setText("系统时间:"+df.format(c.getTime()));
    }
    public static void main(String args[]){
        JFrame f=new JFrame();
        f.setSize(500, 200);
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(new ClockText(20),BorderLayout.CENTER);
        f.setVisible(true);
    }
}
