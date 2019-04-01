package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

class JLabelDemo {
	JFrame mainFrame;
	JLabel simpleLabel;

	public JLabelDemo() {
		mainFrame = new JFrame("JLabelDemo");
		simpleLabel = new JLabel("<html><a href=aaa>百度搜索</a></html>");// 嵌入了html标签
		simpleLabel.addMouseListener(new MouseAdapter() {// 添加鼠标事件侦听器,当单击标签时,打开网页
			public void mouseClicked(MouseEvent e) {
				try {
					Runtime.getRuntime().exec("cmd /c start http://www.baidu.com");
				} catch (IOException ee) {
					ee.printStackTrace();
				}
			}
		});
		simpleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));// 设置手形鼠标
		mainFrame.getContentPane().add(simpleLabel);// 将标签添加到窗口
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();// 使窗口自动根据添加了的组件调整大小
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JLabelDemo();
	}
}
