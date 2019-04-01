/**
 * 
 */
package edu.neu.csye6200.ww;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.util.StatusBar;
import gov.nasa.worldwindx.examples.LayerPanel;

/**
 * @author xingli
 *
 */
public class WorldWindApp {
	
	private JFrame frame;//application app
	private WorldWindowGLCanvas wwd = null;
	private StatusBar statusBar;
	private LayerPanel layerPanel;
	
	
	public WorldWindApp() {
		frame = new JFrame();//it is a swing app
		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLayout(new BorderLayout());
		
		//add globe
		wwd = new WorldWindowGLCanvas();//our globle canvas
		wwd.setPreferredSize(new Dimension(1000, 800));
		wwd.setModel(new BasicModel());
		frame.getContentPane().add(wwd, BorderLayout.CENTER);
		
		//add status bar
		statusBar = new StatusBar();//create ww status bar
		frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		statusBar.setEventSource(wwd);
		
		layerPanel = new LayerPanel(wwd);
		frame.getContentPane().add(layerPanel, BorderLayout.WEST);
		
		
		frame.pack();
		
		//frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WorldWindApp wwApp = new WorldWindApp();
		//anonymous inner class
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				wwApp.getFrame().setVisible(true);
			}
			
		});
		
	}

}
