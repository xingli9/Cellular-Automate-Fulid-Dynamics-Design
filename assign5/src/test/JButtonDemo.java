package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

class JButtonDemo {
	JFrame mainFrame;
	JButton simpleButton;

	public JButtonDemo() {
		mainFrame = new JFrame("JButtonDemo");
		simpleButton = new JButton("百度搜索");
		mainFrame.getContentPane().add(simpleButton);
		simpleButton.addActionListener(new ActionListener() {// 添加动作侦听器,当按钮被按下时执行这里的代码以打开网页
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("terminal /c start http://www.baidu.com");
				} catch (IOException ee) {
					ee.printStackTrace();
				}
			}
		});
		simpleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JButtonDemo();
	}
}
