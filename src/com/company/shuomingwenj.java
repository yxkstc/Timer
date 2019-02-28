/*
 * Created by JFormDesigner on Tue Jan 22 15:05:41 CST 2019
 */

package com.company;

import java.awt.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class shuomingwenj extends JFrame {
    public shuomingwenj() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        textArea1.setText("1、使用前请先设置定时计划，参考默认格式：任务名称 [公司上班广播] ，任务开始时间 [2019-01-22 14:15:00] ,执行时间：[20] ,任务路径：请按浏览按钮选择。"+'\n'+"2、定时任务可以设置3个,每个任务的开始结束时间段不能与其他任务重合，否则无法播放"+'\n'+"3、任务开始时间请按默认的格式填写，否则程序出错。"+'\n'+"4、播放音乐路径最好拷贝到本程序Music路径，防止误删除");
        textArea1.setLineWrap(true);

        setName("\u64cd\u4f5c\u8bf4\u660e"); //NON-NLS
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        scrollPane1.setViewportView(textArea1);
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 0, 385, 265);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
