package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.util.Observer;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


/**
 * A Test application for the Wolfram Biological Growth application
 * 
 * @author MMUNSON
 */
public class WolfApp extends FDApp{

	private static Logger log = Logger.getLogger(WolfApp.class.getName());

	protected static JPanel mainPanel = null;
	protected JPanel northPanel = null;
	protected JButton startBtn;
	protected JButton stopBtn;
	protected JButton pauseBtn;
	private BGCanvas bgPanel;
	private JComboBox<String> faceCombo;
	private TextField lineSz,maxStp; 
	
	FrameFluidSet sim = null;
	Thread thread = null;

	/**
	 * Sample app constructor
	 */
	public WolfApp() {
		
		System.out.println("wolfapp");
		frame.setSize(900, 300); // initial Frame size
		frame.setTitle("WolfApp");

		menuMgr.createDefaultActions(); // Set up default menu items
		//sim = new FrameFluidSet();
		//sim.addObserver(bgPanel);

		//startSim();
		showUI(); // Cause the Swing Dispatch thread to display the JFrame
	}

	/**
	 * Create a main panel that will hold the bulk of our application display
	 */
	@Override
	public JPanel getMainPanel() {
		
		System.out.println("getmainpanel");

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.NORTH, getNorthPanel());

		// set canvas
		bgPanel = new BGCanvas();
		mainPanel.add(BorderLayout.CENTER, bgPanel);



		return mainPanel;
	}

	/**
	 * Create a top panel that will hold control buttons
	 * 
	 * @return
	 */
	public JPanel getNorthPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		northPanel.setBackground(Color.GREEN);

		startBtn = new JButton("Start");
		northPanel.add(startBtn);
		startBtn.addActionListener(this); // Allow the app to hear about button pushes
		
		System.out.println(startBtn);

		stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
		stopBtn.addActionListener(this);
		northPanel.add(stopBtn);

		pauseBtn = new JButton("Pause"); // Allow the app to hear about button pushes
		pauseBtn.addActionListener(this);
		northPanel.add(pauseBtn);
		
		// set rule comboBox
		faceCombo = new JComboBox<String>();
		//faceCombo.setEditable(true);
		// faceCombo.addActionListener(actionListener);
		// faceCombo.addPopupMenuListener(popupMenuListener);
		faceCombo.addItemListener(this);
		faceCombo.setEnabled(true);
		faceCombo.addItem("Select a rule");
		faceCombo.addItem("Arrow cell");
		faceCombo.addItem("rule2");
		faceCombo.addItem("rule3");
        //actionListener
        faceCombo.addActionListener(this);
		northPanel.add(faceCombo, BorderLayout.SOUTH);
		
		//set textfield
		lineSz=new TextField(5); 
		maxStp=new TextField(5); 
		northPanel.add(lineSz, BorderLayout.SOUTH);
		northPanel.add(maxStp, BorderLayout.SOUTH);
		
		return northPanel;
	}
	
	private void startSim() {
		System.out.println("startsim");
		if (sim == null) createSim();
		if (sim.isRunning()) return;

		if (thread == null)
			thread = new Thread(sim);
		thread.start();
		sim.setRunning(true);
		sim.setPaused(false);
		//resetButtons(true);
		startBtn.setEnabled(false);//变灰
		stopBtn.setEnabled(true);
		pauseBtn.setEnabled(true);
		notify();
	}
	
	
	private void resetButtons(boolean bl) {
		
	}
	
	private void pauseSim() {
		if (!sim.isRunning())
			return;
		while (!sim.isPaused()) {
			startBtn.setEnabled(true);//enable start
			sim.setPaused(true);
			pauseBtn.setEnabled(false);//变灰
			try {
				thread.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void stopSim() {
		
	}
	
	private void createSim() {
		
		//bgPanel.rule = 2;
		//mainPanel.add(BorderLayout.CENTER, bgPanel);
		//frame.add(mainPanel, BorderLayout.CENTER);
		sim = new FrameFluidSet();
		sim.addObserver(bgPanel);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("We received an ActionEvent " + e);
		//System.out.println(ae.getSource());
		//System.out.println(startBtn);
		if (e.getSource() == startBtn) {
			System.out.println("Start pressed");
			log.info("We received an ActionEvent " + e);
			startSim();
			
		} else if (e.getSource() == stopBtn) {
			System.out.println("stopbtn");
			stopSim();
			log.info("We received an ActionEvent " + e);
		} else if (e.getSource() == pauseBtn) {
			pauseSim();
			log.info("We received an ActionEvent " + e);
		}
	}
	
	@Override
    public void itemStateChanged(ItemEvent e){
        if(e.getSource() == faceCombo){
            if (e.getItem().toString() == "Arrow cell") {
             	bgPanel.rule = 2;
             	System.out.println("change rull to 2");
            } else if (e.getItem().toString() == "rule2") {
            		bgPanel.rule = 1;
            		System.out.println("change rull to 1");
            }
        }
    }

	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Window opened");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		log.info("Window closing");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		log.info("Window closed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		log.info("Window iconified");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		log.info("Window deiconified");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		log.info("Window activated");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		log.info("Window deactivated");
	}

	/**
	 * Sample Wolf application starting point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		WolfApp wapp = new WolfApp();
		log.info("WolfApp started");
	}

	private void delay(long duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	}
