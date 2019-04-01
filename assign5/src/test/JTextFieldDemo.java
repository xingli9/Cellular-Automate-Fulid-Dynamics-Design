package test;

import javax.swing.*;
import java.awt.event.*;

class JTextFieldDemo {
	JFrame mainFrame;
	JTextField simpleTextField;

	public JTextFieldDemo() {
		mainFrame = new JFrame("JTextFieldDemo");
		simpleTextField = new JTextField(20);// 构造宽度为20个字符的文本框
		mainFrame.getContentPane().add(simpleTextField);
		simpleTextField.addActionListener(new ActionListener() {// 在输入字符后按回车执行行代码,在标准输出窗口输出它的内容
			public void actionPerformed(ActionEvent e) {
				System.out.println(simpleTextField.getText());
			}
		});
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JTextFieldDemo();
	}
}
