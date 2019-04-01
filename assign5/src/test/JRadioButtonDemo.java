package test;

import javax.swing.*;
import java.awt.event.*;

class JRadioButtonDemo implements ItemListener {
	JFrame mainFrame;
	JPanel mainPanel;
	ButtonGroup buttonGroup;
	JRadioButton simpleRadioButton1;
	JRadioButton simpleRadioButton2;

	public JRadioButtonDemo() {
		mainFrame = new JFrame("JRadioButtonDemo");
		mainPanel = new JPanel();
		simpleRadioButton1 = new JRadioButton("RadioButton1");
		simpleRadioButton1.setMnemonic('1');
		simpleRadioButton1.addItemListener(this);
		simpleRadioButton2 = new JRadioButton("RadioButton2");
		simpleRadioButton2.setMnemonic('2');
		simpleRadioButton2.addItemListener(this);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(simpleRadioButton1);
		buttonGroup.add(simpleRadioButton2);
		mainPanel.add(simpleRadioButton1);
		mainPanel.add(simpleRadioButton2);
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public void itemStateChanged(ItemEvent e) {
		JRadioButton cb = (JRadioButton) e.getSource();
		if (cb == simpleRadioButton1)
			System.out.println("simpleRadioButton1");
		else
			System.out.println("simpleRadioButton2");
	}

	public static void main(String[] args) {
		new JRadioButtonDemo();
	}
}