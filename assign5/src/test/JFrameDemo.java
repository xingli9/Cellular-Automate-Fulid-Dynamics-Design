package test;

import javax.swing.*;
import java.awt.event.*;

class JFrameDemo {
	JFrame mainFrame;

	public JFrameDemo() {
		mainFrame = new JFrame("JFrameDemo Title"); // 创建一个JFrame
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭动作
		mainFrame.setSize(300, 300);// 设置窗口大小
		mainFrame.setLocationRelativeTo(null);// 使窗口显示在屏幕中央

		mainFrame.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
				System.out.println("window opened");
			}

			public void windowClosing(WindowEvent e) {
				System.out.println("window closing");
			}

			public void windowClosed(WindowEvent e) {
				System.out.println("window closed");
			}

			public void windowIconified(WindowEvent e) {
				System.out.println("window iconified");
			}

			public void windowDeiconified(WindowEvent e) {
				System.out.println("window deiconified");
			}

			public void windowActivated(WindowEvent e) {
				System.out.println("window activated");
			}

			public void windowDeactivated(WindowEvent e) {
				System.out.println("window deactivated");
			}
		});
		mainFrame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				System.out.println("gained focus");
			}

			public void windowLostFocus(WindowEvent e) {
				System.out.println("lost focus");
			}
		});
		mainFrame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				System.out.println("state changed");
			}
		});

		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JFrameDemo();
	}
}
