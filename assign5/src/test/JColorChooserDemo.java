package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

class JColorChooserDemo {
	JFrame mainFrame;
	JColorChooser simpleColorChooser;
	JLabel sampleLabel;

	public JColorChooserDemo() {
		mainFrame = new JFrame("JColorChooserDemo");
		sampleLabel = new JLabel("sample");
		simpleColorChooser = new JColorChooser();
		simpleColorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sampleLabel.setForeground(simpleColorChooser.getColor());
			}
		});
		mainFrame.getContentPane().add(simpleColorChooser, BorderLayout.PAGE_START);
		//mainFrame.getContentPane().add(sampleLabel, BorderLayout.PAGE_END);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JColorChooserDemo();
	}
}
