package test;

import javax.swing.*;
import java.awt.event.*;

class jcheckbox implements ItemListener {
	JFrame mainFrame;
	JPanel mainPanel;
	JCheckBox simpleCheckBox1;
	JCheckBox simpleCheckBox2;

	public jcheckbox() {
		mainFrame = new JFrame("JCheckBoxDemo");
		mainPanel = new JPanel();
		simpleCheckBox1 = new JCheckBox("checkbox1");
		simpleCheckBox1.setMnemonic('1');
		simpleCheckBox1.addItemListener(this);
		simpleCheckBox2 = new JCheckBox("checkbox2");
		simpleCheckBox2.setMnemonic('2');
		simpleCheckBox2.addItemListener(this);
		mainPanel.add(simpleCheckBox1);
		mainPanel.add(simpleCheckBox2);
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public void itemStateChanged(ItemEvent e) {
		JCheckBox cb = (JCheckBox) e.getSource();
		if (cb == simpleCheckBox1)
			System.out.println("simpleCheckBox1");
		else
			System.out.println("simpleCheckBox2");
	}

	public static void main(String[] args) {
		new jcheckbox();
	}
}