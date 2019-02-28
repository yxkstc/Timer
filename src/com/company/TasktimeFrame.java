/*
 * Created by JFormDesigner on Tue Jan 15 15:27:35 CST 2019
 */

package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Brainrain
 */
public class TasktimeFrame extends JFrame {
    public TasktimeFrame() {
        initComponents();
        initAddress();

    }
    //任务设置
    private void menuItem1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        Missionplan mp=new Missionplan();
        mp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mp.setLocationRelativeTo(null);
        mp.setResizable(false);
        mp.setVisible(true);
    }
    //开始播放
    private void renwuStarActionPerformed(ActionEvent e) {
        // TODO add your code here
        renwuStar.setEnabled(false);
        renwuEnd.setEnabled(true);
        play();
        bofang.setText("开始播放：单曲循环");
    }
    //停止播放
    private void renwuEndActionPerformed(ActionEvent e) {
        // TODO add your code here
        renwuStar.setEnabled(true);
        renwuEnd.setEnabled(false);
        stop();
        bofang.setText("停止播放");
    }
    //任务开始
    private void menuItem2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        menuItem2.setEnabled(false);
        menuItem5.setEnabled(true);
        renwuStar.setEnabled(false);
        renwuEnd.setEnabled(false);
        Stardingshiqi();
    }
    //任务结束
    private void menuItem5ActionPerformed(ActionEvent e) {
        // TODO add your code here
        menuItem2.setEnabled(true);
        menuItem5.setEnabled(false);
        renwuStar.setEnabled(true);
        renwuEnd.setEnabled(true);
        if (service!=null){
            service.shutdown();
            bofang.setText("定时器停止");
        }else {
            //定时任务未执行，不控制按钮
            menuItem2.setEnabled(true);
            menuItem5.setEnabled(true);
            renwuStar.setEnabled(true);
            renwuEnd.setEnabled(true);
        }

    }
    //软件说明
    private void menuItem3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        shuomingwenj sj=new shuomingwenj();
        sj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        sj.setLocationRelativeTo(null);
        sj.setResizable(false);
        sj.setVisible(true);
    }
    //假期设置
    private void menuItem4ActionPerformed(ActionEvent e) {
        // TODO add your code here
        Setholidays ss=new Setholidays();
        ss.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ss.setLocationRelativeTo(null);
        ss.setResizable(false);
        ss.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menu2 = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menuItem3 = new JMenuItem();
        renwu1 = new JLabel();
        label1 = new JLabel();
        renwu2 = new JLabel();
        label4 = new JLabel();
        renwu3 = new JLabel();
        label6 = new JLabel();
        renwuStar = new JButton();
        bofang = new JLabel();
        renwuEnd = new JButton();

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {53, 0, 0, 152, 140, 87, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {28, 53, 53, 59, 41, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};


        menu1.setText("\u7cfb\u7edf\u8bbe\u7f6e"); //NON-NLS

        menuItem1.setText("\u4efb\u52a1\u8bbe\u7f6e"); //NON-NLS
        menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
        menu1.add(menuItem1);

        menu2.setText("\u5f00\u59cb/\u7ed3\u675f-\u4efb\u52a1"); //NON-NLS

        menuItem2.setText("\u5f00\u59cb\u4efb\u52a1"); //NON-NLS
        menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
        menu2.add(menuItem2);

        menuItem5.setText("\u7ed3\u675f\u4efb\u52a1"); //NON-NLS
        menuItem5.addActionListener(e -> menuItem5ActionPerformed(e));
        menu2.add(menuItem5);
        menu1.add(menu2);

        menuItem4.setText("\u5047\u671f\u8bbe\u7f6e"); //NON-NLS
        menuItem4.addActionListener(e -> menuItem4ActionPerformed(e));
        menu1.add(menuItem4);

        menuItem3.setText("\u8f6f\u4ef6\u8bf4\u660e"); //NON-NLS
        menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
        menu1.add(menuItem3);
        menuBar1.add(menu1);
        setJMenuBar(menuBar1);

        renwu1.setText("\u7b2c\u4e00\u4e2a\u4efb\u52a1\uff1a"); //NON-NLS
        contentPane.add(renwu1, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(label1, new GridBagConstraints(3, 1, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        renwu2.setText("\u7b2c\u4e8c\u4e2a\u4efb\u52a1\uff1a"); //NON-NLS
        contentPane.add(renwu2, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(label4, new GridBagConstraints(3, 2, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        renwu3.setText("\u7b2c\u4e09\u4e2a\u4efb\u52a1\uff1a"); //NON-NLS
        contentPane.add(renwu3, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(label6, new GridBagConstraints(3, 3, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        renwuStar.setText("\u5f00\u59cb\u64ad\u653e"); //NON-NLS
        renwuStar.addActionListener(e -> renwuStarActionPerformed(e));
        contentPane.add(renwuStar, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(bofang, new GridBagConstraints(1, 4, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        renwuEnd.setText("\u505c\u6b62\u64ad\u653e"); //NON-NLS
        renwuEnd.addActionListener(e -> renwuEndActionPerformed(e));
        contentPane.add(renwuEnd, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenu menu2;
    private JMenuItem menuItem2;
    private JMenuItem menuItem5;
    private JMenuItem menuItem4;
    private JMenuItem menuItem3;
    private JLabel renwu1;
    private JLabel label1;
    private JLabel renwu2;
    private JLabel label4;
    private JLabel renwu3;
    private JLabel label6;
    private JButton renwuStar;
    private JLabel bofang;
    private JButton renwuEnd;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private AudioClip aau;
    private File f;
    private URL cb;
    private String duration="";//结束时间
    private String durationhms="";//结束时间，HH:MM:SS
    private ScheduledExecutorService service;//定时器

    public void play() {
        aau.loop();

    }

    public void stop() {
        aau.stop();
    }

    public void Stardingshiqi(){
        Runnable runnable = new Runnable() {
            public void run() {
                // task to run goes here
                Date date=new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowDate=dateFormat.format(date);
                bofang.setText("定时器启动:"+nowDate);//显示时间
                System.out.println(date);
                Configure cf=new Configure();//初始化对象
                boolean isjiari=cf.isVacation();//判断是否假日
                try {
                    String rwsj1=cf.getGetProperties().getProperty("shijian1");//获取任务1的时间
                    String rwsj2=cf.getGetProperties().getProperty("shijian2");//获取任务2的时间
                    String rwsj3=cf.getGetProperties().getProperty("shijian3");//获取任务3的时间
                    String rwmc1=cf.getGetProperties().getProperty("renwuName1");//获取任务1名称
                    String rwmc2=cf.getGetProperties().getProperty("renwuName2");//获取任务2名称
                    String rwmc3=cf.getGetProperties().getProperty("renwuName3");//获取任务3名称
                    String rwcx1=cf.getGetProperties().getProperty("zhixingtime1");//获取任务1持续时间
                    String rwcx2=cf.getGetProperties().getProperty("zhixingtime2");//获取任务2持续时间
                    String rwcx3=cf.getGetProperties().getProperty("zhixingtime3");//获取任务3持续时间
                    String jshsm=nowDate.substring(nowDate.length()-8,nowDate.length());//计算当前时间HH:SS:MM
                    String jsrwsj1="";//初始化
                    if(rwsj1.length()>8){
                        jsrwsj1=rwsj1.substring(rwsj1.length()-8,rwsj1.length());//计算获取任务1的时间 HH:SS:MM
                    }
                    String jsrwsj2="";//初始化
                    if(rwsj2.length()>8){
                        jsrwsj2=rwsj2.substring(rwsj2.length()-8,rwsj2.length());//计算获取任务2的时间 HH:SS:MM
                    }
                    String jsrwsj3="";//初始化
                    if(rwsj3.length()>8){
                        jsrwsj3=rwsj3.substring(rwsj3.length()-8,rwsj3.length());//计算获取任务3的时间 HH:SS:MM
                    }
                    //任务1执行
                    if(isjiari && jsrwsj1.equals(jshsm)){
                        play();
                        label1.setText("正在执行："+rwmc1+" 开始时间："+rwsj1+" 持续："+rwcx1+"分钟");
                        initDurationhms(date,dateFormat,rwcx1);
                        Log4jUtil.info("执行"+rwmc1+"成功 "+nowDate);
                    }
                    //任务2执行
                    else if(isjiari && jsrwsj2.equals(jshsm)){
                        play();
                        label4.setText("正在执行："+rwmc2+" 开始时间："+rwsj2+" 持续："+rwcx2+"分钟");
                        initDurationhms(date,dateFormat,rwcx2);
                        Log4jUtil.info("执行"+rwmc2+"成功 "+nowDate);
                    }
                    //任务3执行
                    else if(isjiari && jsrwsj3.equals(jshsm)){
                        play();
                        label6.setText("正在执行："+rwmc3+" 开始时间："+rwsj3+" 持续："+rwcx3+"分钟");
                        initDurationhms(date,dateFormat,rwcx3);
                        Log4jUtil.info("执行"+rwmc3+"成功 "+nowDate);
                    }
                    //任务1结束
                    else if(aau!=null&&label1.getText().length()>0 && durationhms.equals(jshsm)){
                        stop();
                        label1.setText("");
                        Log4jUtil.info("结束"+rwmc1+"成功 "+nowDate);
                    }
                    //任务2结束
                    else if(aau!=null&&label4.getText().length()>0 && durationhms.equals(jshsm)){
                        stop();
                        label4.setText("");
                        Log4jUtil.info("结束"+rwmc2+"成功 "+jshsm);
                    }
                    //任务3结束
                    else if(aau!=null&&label6.getText().length()>0 && durationhms.equals(jshsm)){
                        stop();
                        label6.setText("");
                        Log4jUtil.info("结束"+rwmc3+"成功 "+nowDate);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log4jUtil.info("定时器异常"+nowDate);
                }
            }
        };
        service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        //间隔时间一天
        //long oneDay = 24 * 60 * 60 * 1000;
        long oneDay = 1000;
        service.scheduleAtFixedRate(runnable, 1, oneDay, TimeUnit.MILLISECONDS);
    }

    private void initAddress(){
        try{
            f=new File(new Configure().getGetProperties().getProperty("renwuluj1"));
            cb = f.toURL( );
            aau = Applet.newAudioClip(cb);
        }catch (MalformedURLException e) {
            e.printStackTrace();
            Log4jUtil.info("音乐播放异常");
        } catch (IOException e) {
            e.printStackTrace();
            Log4jUtil.info("音乐被删除或路径错误");
        }
    }

    private void initDurationhms(Date date,DateFormat dateFormat,String time){
        Date date1=new Date();
        long nowMillisecond=date.getTime();//初始化当前时间，单位秒
        long chixushijian=nowMillisecond+Integer.valueOf(time)*60*1000;//计算结束时间，单位毫秒
        date1.setTime(chixushijian);//结束时间毫秒转为时间类型
        duration=dateFormat.format(date1);//结束时间，时间类型格式化
        durationhms=duration.substring(duration.length()-8,duration.length());//计算获取结束时间 HH:SS:MM
    }

}
