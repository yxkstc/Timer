package com.company;


public class Main {
    public static void main(String[] args) {
	// write your code here
        TasktimeFrame tf=new TasktimeFrame();
        tf.setVisible(true);
        tf.setSize(600,300);
        tf.setLocationRelativeTo(null);
        tf.setResizable(false);
        tf.setDefaultCloseOperation(3);
        Log4jUtil.info("程序启动");
    }
}
