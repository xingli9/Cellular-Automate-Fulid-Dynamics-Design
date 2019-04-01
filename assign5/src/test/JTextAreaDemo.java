package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class JTextAreaDemo {
	JFrame mainFrame;
	JTextArea simpleTextArea;
	JButton appendButton;

	public JTextAreaDemo() {
		mainFrame = new JFrame("JTextAreaDemo");
		simpleTextArea = new JTextArea(10, 20);// 创建一个显示10行20列的文本域
		simpleTextArea.setLineWrap(true);// 设置它自动换行
		simpleTextArea.setWrapStyleWord(true);// 设置它自动换行时根据单词换行,而不是根据字符
		appendButton = new JButton("append text to the text area");
		mainFrame.getContentPane().add(simpleTextArea, BorderLayout.PAGE_START);
		mainFrame.getContentPane().add(appendButton, BorderLayout.PAGE_END);
		appendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simpleTextArea.append("button appended text here");
				System.out.println(simpleTextArea.getText());
			}
		});
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JTextAreaDemo();
	}
}