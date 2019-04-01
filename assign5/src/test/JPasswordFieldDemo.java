package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class JPasswordFieldDemo {
	JFrame mainFrame;
	JPasswordField simplePasswordField;

	public JPasswordFieldDemo() {
		mainFrame = new JFrame("JPasswordFieldDemo");
		simplePasswordField = new JPasswordField(10);
		simplePasswordField.setEchoChar('*');// 设定要显示的字符
		mainFrame.getContentPane().add(simplePasswordField);
		simplePasswordField.addActionListener(new ActionListener() {// 回车时执行的动作
			public void actionPerformed(ActionEvent e) {
				char[] input = simplePasswordField.getPassword();
				for (char c : input)
					System.out.print(c);
			}
		});
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JPasswordFieldDemo();
	}
}