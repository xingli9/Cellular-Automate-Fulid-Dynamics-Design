package test;

import javax.swing.*;

class JPanelDemo {
	JFrame mainFrame;
	JPanel simplePanel;
	JButton simpleButton;
	JLabel simpleLabel;

	public JPanelDemo() {
		mainFrame = new JFrame("JPanelDemo");
		simplePanel = new JPanel();
		simpleButton = new JButton("button");
		simpleLabel = new JLabel("label");
		simplePanel.add(simpleLabel);
		simplePanel.add(simpleButton);
		mainFrame.getContentPane().add(simplePanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JPanelDemo();
	}
}