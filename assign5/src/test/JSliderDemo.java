package test;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

class JSliderDemo {
	JFrame mainFrame;
	JSlider simpleSlider;

	public JSliderDemo() {
		mainFrame = new JFrame("JSliderDemo");
		simpleSlider = new JSlider(SwingConstants.VERTICAL);

		Hashtable sliderLabelHashTable = simpleSlider.createStandardLabels(10);
		for (int i = 0; i < sliderLabelHashTable.size() * 10; i += 10) {
			sliderLabelHashTable.put(new Integer(i), new JLabel("label " + i));
		}
		simpleSlider.setLabelTable(sliderLabelHashTable);
		simpleSlider.setPaintLabels(true);
		simpleSlider.setMinorTickSpacing(5);
		simpleSlider.setMajorTickSpacing(10);
		simpleSlider.setPaintTicks(true);

		mainFrame.getContentPane().add(simpleSlider);
		simpleSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println(simpleSlider.getValue());
			}
		});
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JSliderDemo();
	}
}