/*
 * Created by JFormDesigner on Tue Jan 15 15:42:01 CST 2019
 */

package com.company;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class Missionplan extends JFrame {
    public Missionplan() {
        initComponents();
        try{
            initValue();
        }catch (IOException IE){
            IE.printStackTrace();
            Log4jUtil.info("Missionplan.initComponents()-初始化异常！");
        }

    }

    private void saveActionPerformed(ActionEvent e) throws IOException {
        // TODO add your code here
        Configure cf=new Configure();
        String rwn1=renwuName1.getText().trim();
        String rwn2=renwuName2.getText().trim();
        String rwn3=renwuName3.getText().trim();
        String sj1=shijian1.getText().trim();
        String sj2=shijian2.getText().trim();
        String sj3=shijian3.getText().trim();
        String zxt1=zhixingtime1.getText().trim();
        String zxt2=zhixingtime2.getText().trim();
        String zxt3=zhixingtime3.getText().trim();
        String lujing1=renwuluj1.getText().trim();
        String lujing2=renwuluj1.getText().trim();
        String lujing3=renwuluj1.getText().trim();
        //字段验证：任务必须设置1个，任务名称设置后本行必须填写完整，执行时间必须为日期格式，2个以上任务时间段内任务不能冲突。只有未设置任务，
        // 任务时间及执行时间才能为空
        if (cf.isKong(rwn1)){
            if(!cf.isKong(sj1)){
                JOptionPane.showMessageDialog(null, "第一行任务时间必须设置", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!cf.isKong(zxt1)){
                JOptionPane.showMessageDialog(null, "第一行执行时间必须设置", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!cf.isKong(lujing1)){
                JOptionPane.showMessageDialog(null, "第一行任务路径必须设置", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }else if (!cf.isKong(rwn2)&&!cf.isKong(rwn3)){
            JOptionPane.showMessageDialog(null, "任务必须设置一个", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cf.isKong(rwn2)){
            if(!cf.isKong(sj2)){
                JOptionPane.showMessageDialog(null, "第二行任务时间必须设置", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!cf.isKong(zxt2)){
                JOptionPane.showMessageDialog(null, "第二行执行时间必须设置", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!cf.isKong(lujing2)){
                JOptionPane.showMessageDialog(null, "第二行任务路径必须设置", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }else if (!cf.isKong(rwn1)&&!cf.isKong(rwn3)){
            JOptionPane.showMessageDialog(null, "任务必须设置一个", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cf.isKong(rwn3)){
            if(!cf.isKong(sj3)){
                JOptionPane.showMessageDialog(null, "第三行任务时间必须设置", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!cf.isKong(zxt3)){
                JOptionPane.showMessageDialog(null, "第三行执行时间必须设置", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!cf.isKong(lujing3)){
                JOptionPane.showMessageDialog(null, "第三行任务路径必须设置", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }else if (!cf.isKong(rwn1)&&!cf.isKong(rwn2)){
            JOptionPane.showMessageDialog(null, "任务必须设置一个", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!cf.isValidDate(sj1)){
            JOptionPane.showMessageDialog(null, "任务开始时间列第一行请输入日期格式yyyy-mm-dd hh:ss:mm", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!cf.isValidDate(sj2)){
            JOptionPane.showMessageDialog(null, "任务开始时间列第二行请输入日期格式yyyy-mm-dd hh:ss:mm", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!cf.isValidDate(sj3)){
            JOptionPane.showMessageDialog(null, "任务开始时间列第三行请输入日期格式yyyy-mm-dd hh:ss:mm", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cf.isKong(sj1)){
            if (!cf.isNumeric(zxt1)||zxt1.length()==0){
                JOptionPane.showMessageDialog(null, "任务执行时间列第一行请输入大于1的正整数", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (cf.isKong(sj2)){
            if (!cf.isNumeric(zxt2)||zxt2.length()==0){
                JOptionPane.showMessageDialog(null, "任务执行时间列第二行请输入大于1的正整数", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (cf.isKong(sj3)){
            if (!cf.isNumeric(zxt3)||zxt3.length()==0){
                JOptionPane.showMessageDialog(null, "任务执行时间列第三行请输入大于1的正整数", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (cf.isKong(sj1)&&cf.isKong(sj2)){
                if(cf.getDatePoor(sj1,sj2)<=(cf.getDatePoorQH(sj1,sj2)?Long.valueOf(zxt1):Long.valueOf(zxt2))){
                JOptionPane.showMessageDialog(null, "任务持续执行中，不能执行另一个任务，请修改任务开始时间或执行时间", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (cf.isKong(sj1)&&cf.isKong(sj3)){
            if(cf.getDatePoor(sj1,sj3)<=(cf.getDatePoorQH(sj1,sj3)?Long.valueOf(zxt1):Long.valueOf(zxt3))){
                JOptionPane.showMessageDialog(null, "任务持续执行中，不能执行另一个任务，请修改任务开始时间或执行时间", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (cf.isKong(sj2)&&cf.isKong(sj3)){
            if(cf.getDatePoor(sj2,sj3)<=(cf.getDatePoorQH(sj2,sj3)?Long.valueOf(zxt2):Long.valueOf(zxt3))){
                JOptionPane.showMessageDialog(null, "任务持续执行中，不能执行另一个任务，请修改任务开始时间或执行时间", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }




        Configure cfi=new Configure();
        cfi.setGetProperties(renwuName1.getText(),shijian1.getText(),zhixingtime1.getText(),renwuluj1.getText(),renwuName2.getText(),shijian2.getText(),zhixingtime2.getText(),renwuluj2.getText(),renwuName3.getText(),shijian3.getText(),zhixingtime3.getText(),renwuluj3.getText());
        JOptionPane.showMessageDialog(null,"设置成功");
        this.dispose();

    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        initbutton1(1);

    }

    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        initbutton1(2);
    }

    private void button3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        initbutton1(3);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        renwuName1 = new JTextField();
        shijian1 = new JTextField();
        zhixingtime1 = new JTextField();
        renwuluj1 = new JTextField();
        button1 = new JButton();
        renwuName2 = new JTextField();
        shijian2 = new JTextField();
        zhixingtime2 = new JTextField();
        renwuluj2 = new JTextField();
        button2 = new JButton();
        renwuName3 = new JTextField();
        shijian3 = new JTextField();
        zhixingtime3 = new JTextField();
        renwuluj3 = new JTextField();
        button3 = new JButton();
        save = new JButton();
        chooser1=new JFileChooser();
        chooser2=new JFileChooser();
        chooser3=new JFileChooser();
        shijian1.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        shijian2.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        shijian3.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {75, 110, 110, 201, 44, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {31, 0, 0, 0, 27, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        label1.setText("\u4efb\u52a1\u540d\u79f0"); //NON-NLS
        contentPane.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        label2.setText("\u4efb\u52a1\u5f00\u59cb\u65f6\u95f4"); //NON-NLS
        contentPane.add(label2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        label5.setText("执行时间（分钟）"); //NON-NLS
        contentPane.add(label5, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        label3.setText("\u4efb\u52a1\u8def\u5f84"); //NON-NLS
        contentPane.add(label3, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        label4.setText("\u4e0a\u4f20\u6309\u94ae"); //NON-NLS
        contentPane.add(label4, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(renwuName1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(shijian1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(zhixingtime1, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(renwuluj1, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        button1.setText("\u6d4f\u89c8"); //NON-NLS
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(renwuName2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(shijian2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(zhixingtime2, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(renwuluj2, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        button2.setText("\u6d4f\u89c8"); //NON-NLS
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(renwuName3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(shijian3, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(zhixingtime3, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(renwuluj3, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        button3.setText("\u6d4f\u89c8"); //NON-NLS
        button3.addActionListener(e -> button3ActionPerformed(e));
        contentPane.add(button3, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

        save.setText("\u4fdd\u5b58"); //NON-NLS

        save.addActionListener(e -> {
            try {
                saveActionPerformed(e);
            } catch (IOException e1) {
                e1.printStackTrace();
                Log4jUtil.info("Missionplan.save-保存异常");
            }
        });

        contentPane.add(save, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        renwuluj1.setEditable(false);
        renwuluj2.setEditable(false);
        renwuluj3.setEditable(false);
    }
    private void initValue() throws IOException {
        Properties p = new Configure().getGetProperties();
        renwuName1.setText(p.getProperty("renwuName1").trim().length()>0?p.getProperty("renwuName1").trim():"");
        shijian1.setText(p.getProperty("shijian1").trim().length()>0?p.getProperty("shijian1").trim():"");
        zhixingtime1.setText(p.getProperty("zhixingtime1").trim().length()>0?p.getProperty("zhixingtime1").trim():"");
        renwuluj1.setText(p.getProperty("renwuluj1").trim().length()>0?p.getProperty("renwuluj1").trim():"");
        renwuName2.setText(p.getProperty("renwuName2").trim().length()>0?p.getProperty("renwuName2").trim():"");
        shijian2.setText(p.getProperty("shijian2").trim().length()>0?p.getProperty("shijian2").trim():"");
        zhixingtime2.setText(p.getProperty("zhixingtime2").trim().length()>0?p.getProperty("zhixingtime2").trim():"");
        renwuluj2.setText(p.getProperty("renwuluj2").trim().length()>0?p.getProperty("renwuluj2").trim():"");
        renwuName3.setText(p.getProperty("renwuName3").trim().length()>0?p.getProperty("renwuName3").trim():"");
        shijian3.setText(p.getProperty("shijian3").trim().length()>0?p.getProperty("shijian3").trim():"");
        zhixingtime3.setText(p.getProperty("zhixingtime3").trim().length()>0?p.getProperty("zhixingtime3").trim():"");
        renwuluj3.setText(p.getProperty("renwuluj3").trim().length()>0?p.getProperty("renwuluj3").trim():"");
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label5;
    private JLabel label3;
    private JLabel label4;
    private JTextField renwuName1;
    private JTextField shijian1;
    private JTextField zhixingtime1;
    private JTextField renwuluj1;
    private JButton button1;
    private JTextField renwuName2;
    private JTextField shijian2;
    private JTextField zhixingtime2;
    private JTextField renwuluj2;
    private JButton button2;
    private JTextField renwuName3;
    private JTextField shijian3;
    private JTextField zhixingtime3;
    private JTextField renwuluj3;
    private JButton button3;
    private JButton save;
    private JFileChooser chooser1;
    private JFileChooser chooser2;
    private JFileChooser chooser3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    /**
     * @initUploadingfiles 初始化上传事件，获取选择文件路径
     */
    private void  initbutton1(int a) {
        String path=null;
        if(a==1) {
            chooser1.setDialogTitle("请选择要上传的文件...");
            chooser1.setApproveButtonText("确定");
            chooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (JFileChooser.APPROVE_OPTION == chooser1.showOpenDialog(this)) {
                path = chooser1.getSelectedFile( ).getPath( );
                renwuluj1.setText(path);
            }
        }else if(a==2){
            chooser2.setDialogTitle("请选择要上传的文件...");
            chooser2.setApproveButtonText("确定");
            chooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (JFileChooser.APPROVE_OPTION == chooser2.showOpenDialog(this)) {
                path=chooser2.getSelectedFile().getPath();
                renwuluj2.setText(path);
            }
        }else {
            chooser3.setDialogTitle("请选择要上传的文件...");
            chooser3.setApproveButtonText("确定");
            chooser3.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (JFileChooser.APPROVE_OPTION == chooser3.showOpenDialog(this)) {
                path=chooser3.getSelectedFile().getPath();
                renwuluj3.setText(path);
            }
        }
    }
}
