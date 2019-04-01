package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class JScrollPaneDemo {
	JFrame mainFrame;
	JScrollPane simpleScrollPane;
	JTextArea simpleTextArea;
	JButton changeViewport;

	public JScrollPaneDemo() {
		mainFrame = new JFrame("JScrollPaneDemo");
		simpleTextArea = new JTextArea(10, 20);
		simpleScrollPane = new JScrollPane(simpleTextArea);// 创建一个滚动窗格,里面显示的内容是文本区域
		simpleScrollPane.setRowHeaderView(new JLabel("this is a row header"));// 设置行标题
		simpleScrollPane.setColumnHeaderView(new JLabel("this is a column header"));// 设置列标题
		simpleScrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, new JButton("corner"));// 设置右下角
		simpleScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, new JButton("corner"));// 设置左上角
		changeViewport = new JButton("changeViewport");
		changeViewport.addActionListener(new ActionListener() {// 当单击按钮时,滚动窗口显示的内容变为另一个文本区域
			public void actionPerformed(ActionEvent e) {
				simpleScrollPane.getViewport().setView(new JTextArea("changeViewpot"));
			}
		});
		mainFrame.getContentPane().add(simpleScrollPane, BorderLayout.PAGE_START);
		mainFrame.getContentPane().add(changeViewport, BorderLayout.PAGE_END);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JScrollPaneDemo();
	}
}
