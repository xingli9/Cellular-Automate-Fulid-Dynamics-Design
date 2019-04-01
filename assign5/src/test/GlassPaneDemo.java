package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class GlassPaneDemo {
	JFrame mainFrame;
	JPanel mainPanel;
	JButton button;

	public GlassPaneDemo() {
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		button = new JButton("button");
		// mainFrame.setGlassPane( mainPanel );
		mainPanel.add(button);
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setGlassPane(new MyGlassPane());
		mainFrame.getGlassPane().setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(300, 400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	private class MyGlassPane extends JComponent {
		Point point = new Point(10, 10);

		public MyGlassPane() {
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					point = new Point(e.getX(), e.getY());
					repaint();
				}
			});
		}

		public void paintComponent(Graphics g) {
			g.setColor(Color.red);
			g.fillOval(point.x, point.y, 20, 20);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GlassPaneDemo();
			}
		});
	}
}